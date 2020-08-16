import java.util.Scanner;

/**
 * A read-eval-print loop (REPL) that reads in a command from the user, executes it, and prints out the result.
 */
public class Repl {
    // Formatting
    /** Number of spaces to prefix each line with. */
    private static final int LEFT_PADDING_SIZE = 4;
    /** Number of underscores each divider should be made up of. */
    private static final int DIVIDER_LENGTH = 60;

    // Strings
    /** Message to be displayed on start-up. */
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    /** Message to be displayed on exit. */
    private static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
    /** Error to be displayed when marking a {@code Task} as done fails. */
    private static final String MARK_AS_DONE_ERROR = "Please input a valid task index.";
    /** Error to be displayed when an invalid command is inputted. */
    private static final String INVALID_COMMAND_ERROR = "Please input a valid command.";

    /** {@code Scanner} object which reads in user input. */
    private static final Scanner scanner = new Scanner(System.in);
    /** {@code PrettyPrinter} object for formatting the REPL output. */
    private static final PrettyPrinter prettyPrinter = new PrettyPrinter(LEFT_PADDING_SIZE, DIVIDER_LENGTH);
    /** {@code TaskManager} object to keep track of tasks */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(WELCOME_MESSAGE);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String command = line.split(" ")[0];
            String[] args;
            switch (command) {
                case "bye":
                    prettyPrinter.print(FAREWELL_MESSAGE);
                    return;
                case "deadline":
                    line = line.replaceFirst("^deadline\\s*", "");
                    args = line.split("/by");
                    String deadlineName = args[0];
                    String dueDate = args[1];
                    prettyPrinter.print(taskManager.addDeadline(deadlineName, dueDate));
                    break;
                case "done":
                    try {
                        line = line.replaceFirst("^done\\s*", "");
                        args = line.split("");
                        int listIndex = Integer.parseInt(args[0]) - 1;
                        prettyPrinter.print(taskManager.markAsDone(listIndex));
                    } catch (Exception e) {
                        prettyPrinter.print(MARK_AS_DONE_ERROR);
                    }
                    break;
                case "event":
                    line = line.replaceFirst("^deadline\\s*", "");
                    args = line.split("/by");
                    String eventName = args[0];
                    String dateTime = args[1];
                    prettyPrinter.print(taskManager.addEvent(eventName, dateTime));
                    break;
                case "list":
                    prettyPrinter.print(taskManager.toString());
                    break;
                case "todo":
                    String toDoName = line.replaceFirst("^todo\\s*", "");
                    prettyPrinter.print(taskManager.addToDo(toDoName));
                    break;
                default:
                    prettyPrinter.print(INVALID_COMMAND_ERROR);
                    break;
            }
        }
    }
}
