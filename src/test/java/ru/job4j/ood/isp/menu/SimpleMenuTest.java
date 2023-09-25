package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static Menu menu = new SimpleMenu();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeAll
    public static void init() {
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }

    @Test
    public void whenAddThenReturnSame() {
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectThenSelected() {
        String item = "Покормить собаку";
        Optional<Menu.MenuItemInfo> menuItemInfoOpt = menu.select(item);
        Optional<Menu.MenuItemInfo> expected = Optional.of(new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."));
        assertThat(expected).isEqualTo(menuItemInfoOpt);
    }

    @Test
    public void whenPrinted() {
        System.setOut(new PrintStream(outputStreamCaptor));
        String expected = """
  ---1.Сходить в магазин
  ------1.1.Купить продукты
  ---------1.1.1.Купить хлеб
  ---------1.1.2.Купить молоко
  ---2.Покормить собаку""";
        MenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expected);
        System.setOut(standardOut);

    }

    @Test
    public void test() {
        System.out.println(menu.select("Сходить в магазин").isEmpty());
    }
}