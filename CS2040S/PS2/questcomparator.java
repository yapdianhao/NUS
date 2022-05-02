import java.util.*;

class QuestComparator implements Comparator<Quest> {

    public int compare(Quest quest1, Quest quest2) {
        if (quest1.energy > quest2.energy) {
            return -1;
        } else if (quest1.energy < quest2.energy) {
            return 1;
        } else {
            if (quest1.gold > quest2.gold) {
                return -1;
            } else if (quest1.gold < quest2.gold) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}