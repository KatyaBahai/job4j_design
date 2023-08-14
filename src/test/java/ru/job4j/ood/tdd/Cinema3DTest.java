package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new CinemaAccount();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new CinemaAccount();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyInvalidColumnThenGetException() {
        Account account = new CinemaAccount();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyInvalidDateThenGetException() {
        Account account = new CinemaAccount();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Session session = new Session3D();
        session.setTheDate(date);
        cinema.add(session);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, Calendar.getInstance())).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTheOccupiedSeatThenGetException() {
        Account account = new CinemaAccount();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenFindFilmsByDateThenGetTheListOfFilmsByDate() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Calendar sessionsDate = Calendar.getInstance();
        session.setTheDate(sessionsDate);
        Session session2 = new Session3D();
        session2.setTheDate(sessionsDate);
        cinema.add(session);
        cinema.add(session2);
        assertThat(cinema.find(date -> date.equals(sessionsDate))).isEqualTo(List.of(session, session2));
    }

    @Test
    public void whenNoFilmsFoundByFilterThenReturnEmptyList() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertThat(cinema.find(ses -> false)).isEmpty();
    }

}