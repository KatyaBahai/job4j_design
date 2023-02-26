package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int deleted = 0;
        int changed = 0;
        Map<Integer, User> prevMap = new HashMap<>();
        Map<Integer, User> currMap = new HashMap<>();

        for (User prevUser : previous) {
                prevMap.put(prevUser.getId(), prevUser);
        }
        for (User currUser : current) {
                currMap.put(currUser.getId(), currUser);
                if (!prevMap.containsKey(currUser.getId())) {
                    added++;
                }
        }
        for (Map.Entry<Integer, User> entry : prevMap.entrySet()) {
            Integer id = entry.getKey();
            if (!currMap.containsKey(id)) {
                deleted++;
            } else if (!entry.getValue().equals(currMap.get(id))) {
                changed++;
            }
        }
        return new Info(added, changed, deleted);
    }

}