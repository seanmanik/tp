package seedu.travelr.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.UniqueEventList;
import seedu.travelr.model.eventList.BucketList;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.trip.UniqueTripList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    public static BucketList bucketList;
    public static UniqueTripList trips;
    private final UniqueEventList events;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        trips = new UniqueTripList();
        events = new UniqueEventList();
        bucketList = new BucketList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTrips(List<Trip> trips) {
        this.trips.setTrips(trips);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        assert !newData.getTripList().isEmpty();

        setTrips(newData.getTripList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasTrip(Trip trip) {
        requireNonNull(trip);
        return trips.contains(trip);
    }

    /**
     * Returns true if this event already exists in Travelr.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addTrip(Trip p) {
        trips.add(p);
    }

    /**
     * Adds an event to Travelr.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setTrip(Trip target, Trip editedTrip) {
        requireNonNull(editedTrip);

        trips.setTrip(target, editedTrip);
    }

    /**
     * Replaces the given {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in Travelr.
     * The new event must not be the same as another existing event in Travelr.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTrip(Trip key) {
        trips.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in Travelr.
     * @return
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return trips.asUnmodifiableObservableList().size() + " trips";
        // TODO: refine later
    }

    @Override
    public ObservableList<Trip> getTripList() {
        return trips.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && trips.equals(((AddressBook) other).trips) && events.equals(((AddressBook) other).events));
    }

    @Override
    public int hashCode() {
        return trips.hashCode();
    }
}
