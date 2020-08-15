/**
 * A specialised {@code Task} that does not have any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a new uncompleted {@code ToDo} object.
     *
     * @param name the name of the {@code ToDo}.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructs a new {@code ToDo} object.
     *
     * @param name the name of the {@code ToDo}.
     */
    private ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Marks the {@code ToDo} as done.
     *
     * @return a new completed {@code ToDo} object with the same {@code name} as this {@code ToDo}.
     */
    @Override
    public Task markAsDone() {
        return new ToDo(name, true);
    }
}
