package neumont.edu.csc150.c.models;

public class Food {
    private int amountOfFood;
    private AnimalTypes.AllAnimals type;

    public Food() {}

    public Food(int amountOfFood, AnimalTypes.AllAnimals foodType) {
        this.amountOfFood = amountOfFood;
        this.type = foodType;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    public AnimalTypes.AllAnimals getFoodType() {
        return type;
    }

    public void setFoodType(AnimalTypes.AllAnimals type) {
        this.type = type;
    }

    public String serialize() {
        return String.format("%d<%s,", this.getAmountOfFood(), this.getFoodType());
    }

    @Override
    public String toString() {
        return String.format("Amount of Food : %d/3\r\nFood is suitable for : %s",
                this.getAmountOfFood(), this.getFoodType());
    }
}
