package seedu.travelr.model.trip;

import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.list.Itineraries;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Identity fields
    private final Title title;
    private final Description description;
    private final Location location;
    private boolean done;

    // Data fields
    private final Itineraries events = new Itineraries();

    /**
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events) {
        requireAllNonNull(title, description, events);
        this.title = title;
        this.description = description;
        this.events.setInternalList(events);
        this.location = Location.getDefaultLocation();
        this.done = false;
    }

    /**
     * Used in JsonAdaptedTrips
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events, boolean markedAsDone, Location location) {
        requireAllNonNull(title, description, events);
        this.title = title;
        this.description = description;
        this.events.setInternalList(events);
        this.location = location;
        this.done = markedAsDone;
    }

    /**
     * Main constructor
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events, Location location) {
        requireAllNonNull(title, description, events, location);
        this.title = title;
        this.description = description;
        this.events.setInternalList(events);
        this.location = location;
        this.done = false;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void markDone() {
        this.done = true;
    }

    public void markNotDone() {
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public Event getEvent(Event event) {
        return events.getEvent(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public boolean contains(Event event) {
        return events.contains(event);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(events.getList());
    }

    /**
     * Returns the itinerary of this trip.
     */
    public Itineraries getItinerary() {
        return this.events;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getTitle().equals(getTitle());
        //        && otherTrip.getDescription().equals(getDescription())
        //        && otherTrip.getEvents().equals(getEvents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, events);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        Set<Event> tags = getEvents();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        
        if (getLocation() != Location.getDefaultLocation()) {
            builder.append("; Location: ");
            builder.append(getLocation());    
        }
        
        return builder.toString();
    }

    public int compareTitle(Trip trip) {
        return title.compareTo(trip.title);
    }

}
