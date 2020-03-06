package neumont.edu.csc150.c.models;

public class Pet implements PetInterface {

    private String name;
    private AnimalTypes.AllAnimals animalType;
    private AnimalTypes.Colors color;
    private int hunger;
    private int attention;
    private int messiness;
    private int stars;
    private long lastFeedingTime;
    private long lastPlayTime;
    private long lastCleaning;
    private long lastSleepTime;
    private boolean isAsleep;

    public Pet() {
    }

    public Pet(String name, AnimalTypes.AllAnimals animalType, AnimalTypes.Colors color, int hunger, int attention, int messiness, int stars, long lastFeedingTime, long lastSleepTime, long lastCleaning, long lastPlayTime, boolean isAsleep) {
        this.name = name;
        this.animalType = animalType;
        this.color = color;
        this.hunger = hunger;
        this.attention = attention;
        this.messiness = messiness;
        this.stars = stars;
        this.lastFeedingTime = lastFeedingTime;
        this.lastPlayTime = lastPlayTime;
        this.lastCleaning = lastCleaning;
        this.lastSleepTime = lastSleepTime;
        this.isAsleep = isAsleep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalTypes.AllAnimals getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalTypes.AllAnimals animalType) {
        this.animalType = animalType;
    }

    public AnimalTypes.Colors getColor() {
        return color;
    }

    public void setColor(AnimalTypes.Colors color) {
        this.color = color;
    }

    public int getHunger() {

        return hunger;
    }

    public void setHunger(int hunger) {
        if(hunger > 10){
            hunger = 10;
        }
        this.hunger = hunger;
    }

    public int getAttention() {

        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public int getMessiness() {
        return messiness;
    }

    public void setMessiness(int messiness) {
        this.messiness = messiness;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public long getLastFeedingTime() {
        return lastFeedingTime;
    }

    public void setLastFeedingTime(long lastFeedingTime) {
        this.lastFeedingTime = lastFeedingTime;
    }

    public long getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(long lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public long getLastCleaning() {
        return lastCleaning;
    }

    public void setLastCleaning(long lastCleaning) {
        this.lastCleaning = lastCleaning;
    }

    public long getLastSleepTime() {
        return lastSleepTime;
    }

    public void setLastSleepTime(long lastSleepTime) {
        this.lastSleepTime = lastSleepTime;
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    public void setAsleep(boolean asleep) {
        isAsleep = asleep;
    }

    @Override
    public void eat(Food food) {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void wakeUp() {

    }

    public String serialize(){
        return String.format("%s<%s<%s<%d<%d<%d<%d<%d<%d<%d<%d<%s",
                this.getName(),
                this.getAnimalType(),
                this.getColor(),
                this.getHunger(),
                this.getAttention(),
                this.getMessiness(),
                this.getStars(),
                this.getLastFeedingTime(),
                this.getLastPlayTime(),
                this.getLastCleaning(),
                this.getLastSleepTime(),
                this.isAsleep());
    }

    @Override
    public String toString() {
        return String.format("Pet Name: %s\r\nAnimal Type: %s\r\nColor: %s\r\nHunger level - %d/10\r\nAttention Level - %d/10\r\nMessiness Level - %d/10\r\nOver All Comfort: %d/5\r\nIs Asleep: %s\r\n",
                this.getName(),
                this.getAnimalType(),
                this.getColor(),
                this.getHunger(),
                this.getAttention(),
                this.getMessiness(),
                this.getStars(),
                this.isAsleep());
    }
}