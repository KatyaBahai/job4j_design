package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Percentage;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void vertices0ForSphere() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isZero();
    }

    @Test
    void vertices8ForCube() {
        Box box = new Box(8, 20);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero().isEqualTo(8);
    }

    @Test
    void figureNotExists() {
        Box box = new Box(7, 5);
        boolean figure = box.isExist();
        assertThat(figure).isFalse();
    }

    @Test
    void figureExists() {
        Box box = new Box(4, 20);
        boolean figure = box.isExist();
        assertThat(figure).isTrue();
    }

    @Test
    void areaNotExists() {
        Box box = new Box(2, 60);
        double area = box.getArea();
        assertThat(area).isEqualTo(0);
    }

    @Test
    void areaOfArea() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isCloseTo(314.159, Percentage.withPercentage(1.0d));
    }
}