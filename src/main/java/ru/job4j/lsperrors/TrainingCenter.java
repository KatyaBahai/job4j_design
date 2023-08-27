package ru.job4j.lsperrors;

class Sportsman {
}

class Boxer extends Sportsman {
}


class TrainingCenter {
    public void train(Sportsman sportsman) {
        System.out.printf("%s: 1 hour of practice is completed!", sportsman);
    }
}

class BoxingTrainingCenter extends TrainingCenter {
    public void train(Boxer boxer) {
        System.out.printf("%s: 1 hour of practice is completed!", boxer);
    }
}
