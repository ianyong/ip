package enums;

import exceptions.DukeException;
import utils.ResourceHandler;

import java.text.MessageFormat;
import java.util.regex.Pattern;

/**
 * Commands that can be used in <i>Duke</i>.
 */
public enum Command {
    /**
     * Terminates the running of <i>Duke</i>.
     */
    BYE {
        @Override
        public void validate(String input) {

        }
    },

    /**
     * Adds a {@code Deadline} task to the {@code TaskManager}.
     */
    DEADLINE {
        @Override
        public void validate(String input) {

        }
    },

    /**
     * Marks a {@code Task} as done.
     */
    DONE {
        /**
         * Validates whether the user input is of the correct format for the 'done' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)done\\s+\\d+\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.doneFormat"));
                String message = MessageFormat.format(template, "done");
                throw new DukeException(message);
            }
        }
    },

    /**
     * Adds an {@code Event} task to the {@code TaskManager}.
     */
    EVENT {
        @Override
        public void validate(String input) {

        }
    },

    /**
     * Lists all {@code Task}s in the {@code TaskManager}.
     */
    LIST {
        @Override
        public void validate(String input) {

        }
    },

    /**
     * Adds a {@code ToDo} task to the {@code TaskManager}.
     */
    TODO {
        @Override
        public void validate(String input) {

        }
    };

    /**
     * Validates whether the user input is of the correct format.
     *
     * @param input the user input.
     * @throws DukeException if the user input is invalid.
     */
    public abstract void validate(String input) throws DukeException;
}
