package neumont.edu.csc150.c.models;


public class Environment {
    private Pet pet = null;
    private AnimalTypes.AllAnimals animalsCage = AnimalTypes.AllAnimals.EMPTY;

    public Environment(){
    }

    public Environment(Pet pet, AnimalTypes.AllAnimals animalsCage) {
        this.setPet(pet);
        this.setAnimalsCage(animalsCage);
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public AnimalTypes.AllAnimals getAnimalsCage() {
        return animalsCage;
    }

    public void setAnimalsCage(AnimalTypes.AllAnimals animalsCage) {
        this.animalsCage = animalsCage;
    }

    public String serialize() {
        String returnString;
        if(getPet() == null){
            returnString = getAnimalsCage().toString()+"\\,";
        }
        else{
            returnString = getPet().serialize()+"\\,";
        }
        return returnString;
    }

//    @Override
//    public String toString(){
//
//    }
}
