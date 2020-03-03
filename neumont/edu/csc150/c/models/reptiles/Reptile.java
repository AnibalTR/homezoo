package neumont.edu.csc150.c.models.reptiles;

import neumont.edu.csc150.c.models.AnimalTypes;
import neumont.edu.csc150.c.models.Pet;

public class Reptile extends Pet {
    public Reptile() {
    }

    public Reptile(String name, AnimalTypes.AllAnimals animalType, AnimalTypes.Colors color, int hunger, int attention, int messiness, int stars, long lastFeedingTime, long lastSleepTime, long lastCleaning, long lastPlayTime, boolean isAsleep) {
        super(name, animalType, color, hunger, attention, messiness, stars, lastFeedingTime, lastSleepTime, lastCleaning, lastPlayTime, isAsleep);
    }
}
