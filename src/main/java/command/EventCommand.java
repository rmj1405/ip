package command;

import task.Event;
import task.TaskManager;

import util.DukeException;

/**
 * Executes add event task command.
 */
public class EventCommand extends Command {
    private final TaskManager taskManager;
    private final String description;
    public EventCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }

    /**
     * Adds an event type task to the list.
     *
     * String input is parsed to extract start and end
     * dates and timings of the event.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand() throws DukeException {
        try {
            String[] arr = this.description.split(" /from ");
            String[] time = arr[1].split(" /to ");
            String start = time[0];
            String end = time[1];
            Event event = new Event(arr[0], false, start, end);
            taskManager.addTaskToList(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please add a description, date and time e.g. party /from 12/2/23 1800 /to 12/2/23 2200");
        }
    }
}
