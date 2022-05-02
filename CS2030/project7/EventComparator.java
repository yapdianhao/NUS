import java.util.Comparator;

/**
 * A comparator that compares two events based on the customer's time.
 * If both customers have the same time, compares their ID instead.
 */
class EventComparator implements Comparator<Event> {
    public int compare(Event e1, Event e2) {
        double time1 = e1.getTime();
        double time2 = e2.getTime();
        int id1 = e1.getID();
        int id2 = e2.getID();
        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        } else {
            if (id1 > id2) {
                return 1;
            } else if (id1 < id2) {
                return -1;
            } else {
                if (e1.getState().equals("arrives")) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
