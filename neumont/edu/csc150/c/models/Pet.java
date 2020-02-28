package neumont.edu.csc150.c.models;

public class Pet implements PetInterface {

    public enum Colors{
        BLACK,
        WHITE,
        GREY,
        ORANGE
    }
    private Colors color;
    private int hunger;
    private int attention;
    private int messiness;
    private int stars;
    private int lastFeedingTime;
    private int lastPlayTime;
    private int lastCleaning;
    private int lastSleepTime;
    private boolean isAsleep;

    public Pet() {
    }

    public Pet(Colors color, int hunger, int attention, int messiness, int stars, int lastFeedingTime, int lastPlayTime, int lastCleaning, int lastSleepTime, boolean isAsleep) {
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

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
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

    public int getLastFeedingTime() {
        return lastFeedingTime;
    }

    public void setLastFeedingTime(int lastFeedingTime) {
        this.lastFeedingTime = lastFeedingTime;
    }

    public int getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(int lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public int getLastCleaning() {
        return lastCleaning;
    }

    public void setLastCleaning(int lastCleaning) {
        this.lastCleaning = lastCleaning;
    }

    public int getLastSleepTime() {
        return lastSleepTime;
    }

    public void setLastSleepTime(int lastSleepTime) {
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

    @Override
    public String toString() {
        return String.format("%s<%d<%d<%d<%d<%d<%d<%d<%d<%s",
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
}