package ru.job4j.ood.tdd;

import java.util.List;

public interface Account {

    Integer getTheAccountNumber();

    List<Ticket> showAllTickets();
}
