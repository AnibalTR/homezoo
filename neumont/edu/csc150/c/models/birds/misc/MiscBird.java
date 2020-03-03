package neumont.edu.csc150.c.models.birds.misc;

import neumont.edu.csc150.c.models.AnimalTypes;
import neumont.edu.csc150.c.models.birds.Bird;

public class MiscBird extends Bird {
    public MiscBird() {
    }

    public MiscBird(String name, AnimalTypes.AllAnimals animalType, AnimalTypes.Colors color, int hunger, int attention, int messiness, int stars, long lastFeedingTime, long lastSleepTime, long lastCleaning, long lastPlayTime, boolean isAsleep) {
        super(name, animalType, color, hunger, attention, messiness, stars, lastFeedingTime, lastSleepTime, lastCleaning, lastPlayTime, isAsleep);
    }
}
