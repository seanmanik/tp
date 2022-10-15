package seedu.travelr.logic.commands;

import seedu.travelr.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_TRIPS;

public class ViewAllCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all trips and events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTripList(PREDICATE_SHOW_ALL_TRIPS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
