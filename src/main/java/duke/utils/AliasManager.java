package duke.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.gson.reflect.TypeToken;

import duke.enums.Command;

/**
 * Keeps track of aliases.
 */
public class AliasManager {
    /** Map of aliases. */
    private final Map<String, Command> aliases = new PersistentMap<>("./data/aliases.txt",
            new TypeToken<HashMap<String, Command>>(){}.getType());

    /** Constructs a {@code AliasManager} object. */
    public AliasManager() {}

    /**
     * Associates an alias with a command.
     *
     * @param alias the alias for the command.
     * @param commandString the command to be aliased in string representation.
     * @return a string representation of the action of adding an alias.
     */
    public String addAlias(String alias, String commandString) {
        String response;
        try {
            Command command = Command.valueOf(commandString.toUpperCase());
            Command previousCommand = aliases.putIfAbsent(alias, command);
            if (previousCommand == null) {
                response = ResourceHandler.getString("aliasManager.addAlias");
            } else {
                response = ResourceHandler.getString("aliasManager.aliasInUse");
            }
        } catch (IllegalArgumentException e) {
            response = MessageFormat.format(ResourceHandler.getString("aliasManager.invalidCommand"),
                    commandString);
        }

        return response;
    }

    /**
     * Returns the {@code Command} that corresponds to the alias. Note that every command is an alias for
     * itself.
     *
     * @param alias the alias that is being looked up.
     * @return the corresponding {@code Command}.
     */
    public Command getCommand(String alias) {
        Command command;
        try {
            command = Command.valueOf(alias.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = aliases.get(alias);
        }
        // Throw an `IllegalArgumentException` to signify that no commands match.
        if (command == null) {
            throw new IllegalArgumentException();
        }
        return command;
    }

    /**
     * Returns a list of aliases under the {@code AliasManager}.
     *
     * @return a list of aliases under the {@code AliasManager}.
     */
    @Override
    public String toString() {
        StringBuilder formattedList =
                new StringBuilder(ResourceHandler.getString("aliasManager.listAliasesPrefix") + "\n");
        List<String> keys = new ArrayList<>(aliases.keySet());
        IntStream.range(0, aliases.size())
                .mapToObj(i -> String.format("%d. %s -> %s\n", i + 1,
                        keys.get(i).toLowerCase(), aliases.get(keys.get(i)).toString().toLowerCase()))
                .forEach(formattedList::append);
        return formattedList.toString();
    }
}
