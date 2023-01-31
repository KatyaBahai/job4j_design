package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndSearchThenUsernameIsMaid() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Maid"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Maid");
    }

    @Test
    void whenAddAndSearchThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        store.add(new Role("1", "Maid"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Janitor");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        store.replace("1", new Role("1", "Maid"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Maid");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        store.replace("10", new Role("10", "Maid"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Janitor");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Janitor");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        boolean rsl = store.replace("1", new Role("1", "Maid"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        boolean rsl = store.replace("10", new Role("10", "Janitor"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Janitor"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}