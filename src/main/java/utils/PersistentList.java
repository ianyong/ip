package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@code ArrayList} wrapper that syncs the state of the list to a file whenever it's updated.
 */
public class PersistentList<E> extends ArrayList<E> {
    /** Internal {@code ArrayList} object. */
    private final List<E> list;
    /** {@code StorageManager} object for writing the state of the list to the specified file. */
    private final StorageManager storageManager;
    /** {@code Gson} object for converting objects to JSON. */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructs a {@code PersistentList} object with the specified file.
     *
     * @param filePath the path of the file to read from and write to.
     */
    public PersistentList(String filePath) {
        list = new ArrayList<>();
        storageManager = new StorageManager(filePath);
    }

    /**
     * Writes the current state of the list to the specified file.
     */
    private void syncStateToFile() {
        try {
            storageManager.saveToFile(gson.toJson(list));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Appends the specified element to the end of the list. Then, syncs the state of the list to the specified file.
     *
     * @param e the element to be appended to the list.
     * @return true (as specified by {@code Collection.add(E)}).
     */
    @Override
    public boolean add(E e) {
        list.add(e);
        syncStateToFile();
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices). Then, syncs the state of
     * the list to the specified file.
     *
     * @param index the index at which the specified element is to be inserted.
     * @param e the element to be inserted.
     */
    @Override
    public void add(int index, E e) {
        list.add(index, e);
        syncStateToFile();
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left
     * (subtracts one from their indices). Then, syncs the state of the list to the specified file.
     *
     * @param index the index of the element to be removed.
     * @return the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        syncStateToFile();
        return removedElement;
    }

    // TODO: Override all `ArrayList` functions that modify the state of the list
}
