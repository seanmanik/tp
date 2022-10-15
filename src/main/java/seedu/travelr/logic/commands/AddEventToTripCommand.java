package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TRIP;

import java.util.HashSet;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Represents the AddEventToTripCommand. Extends the Command class.
 */
public class AddEventToTripCommand extends Command {

    public static final String COMMAND_WORD = "add-et";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to specified trip. "
            + "Parameters: "
            + PREFIX_TITLE + "Event TITLE "
            + PREFIX_TRIP + "TRIP "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Swim "
            + PREFIX_TRIP + "Honeymoon ";

    public static final String MESSAGE_SUCCESS = "Event added to trip: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This event already exists in the specified trip";

    private final Title eventToAdd;
    private final Title tripToAddInto;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddEventToTripCommand(Title event, Title trip) {
        requireNonNull(event);
        requireNonNull(trip);
        eventToAdd = event;
        tripToAddInto = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEvent(new Event(eventToAdd))) {
            throw new CommandException("Please enter a valid event");
        }

        if (!model.hasTrip(new Trip(tripToAddInto, new Description("random"), new HashSet<>()))) {
            throw new CommandException("Please enter a valid Trip");
        }

        Event event = model.getEvent(new Event(eventToAdd));
        Trip toAddInto = model.getTrip(new Trip(tripToAddInto, new Description("random"), new HashSet<>()));
        model.deleteEvent(event);
        toAddInto.addEvent(event);
        return new CommandResult(String.format(MESSAGE_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventToTripCommand // instanceof handles nulls
                && eventToAdd.equals(((AddEventToTripCommand) other).eventToAdd)
                && tripToAddInto.equals(((AddEventToTripCommand) other).tripToAddInto));
    }
}
