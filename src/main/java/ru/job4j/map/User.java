package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        birthday.set(1990, 03, 23);
        User userFirst = new User("Dorian", 3, birthday);
        int hashCode1 = userFirst.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User userSecond = new User("Dorian", 3, birthday);
        int hashCode2 = userSecond.hashCode();
        int hash2 = hashCode2 ^ (hashCode1 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        System.out.println(map);
        System.out.printf("userFirst hashcode: %s, hash: %s, bucket: %s", hash1, hashCode1, bucket1);
        System.out.println();
        System.out.printf("userSecond hashcode: %s, hash: %s, bucket: %s", hash2, hashCode2, bucket2);

    }




}
