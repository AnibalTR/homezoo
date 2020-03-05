package neumont.edu.csc150.c.models;

import java.util.Date;

public class Store {
    Date date = new Date();

    public Food buyFood(int amountOfFood, AnimalTypes.AllAnimals animal) {
        Food returnFood;
        switch(animal.toString()) {
            case "SCARLET_MACAW":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.SCARLET_MACAW);
                break;
            case "GREY_PARROT":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.GREY_PARROT);
                break;
            case "PALLID_PARROT":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.PALLID_PARROT);
                break;
            case "BALL_PYTHON":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.BALL_PYTHON);
                break;
            case "CORN_SNAKE":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.CORN_SNAKE);
                break;
            case "ROSY_BOA":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.ROSY_BOA);
                break;
            case "SPINY_SOFTSHELL_TURTLE":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.SPINY_SOFTSHELL_TURTLE);
                break;
            case "RED_EARED_SLIDER":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.RED_EARED_SLIDER);
                break;
            case "BOX_TURTLE":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.BOX_TURTLE);
                break;
            case "BEARDED_DRAGON":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.BEARDED_DRAGON);
                break;
            case "LEOPARD_GECKO":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.LEOPARD_GECKO);
                break;
            case "IGUANA":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.IGUANA);
                break;
            case "FERRET":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.FERRET);
                break;
            case "RAT":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.RAT);
                break;
            case "SKUNK":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.SKUNK);
                break;
            case "HEDGEHOG":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.HEDGEHOG);
                break;
            case "FENNEC_FOX":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.FENNEC_FOX);
                break;
            case "RACCOON":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.RACCOON);
                break;
            case "BEAR":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.BEAR);
                break;
            case "ENGLISH_BERNARD":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.ENGLISH_BERNARD);
                break;
            case "GERMAN_SHEPARD":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.GERMAN_SHEPARD);
                break;
            case "YORKSHIRE_TERRIER":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.YORKSHIRE_TERRIER);
                break;
            case "PIT_BULL":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.PIT_BULL);
                break;
            case "GREYHOUND":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.GREYHOUND);
                break;
            case "SPHINX":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.SPHINX);
                break;
            case "PERSIAN":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.PERSIAN);
                break;
            case "GARFIELD":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.GARFIELD);
                break;
            case "KOREAN_BOBTAIL":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.KOREAN_BOBTAIL);
                break;
            case "CROW":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.CROW);
                break;
            case "TOUCAN":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.TOUCAN);
                break;
            case "PIDGEON":
                returnFood = new Food(amountOfFood, AnimalTypes.AllAnimals.PIGEON);
                break;
            default:
                returnFood = new Food(0, null);
        }
        return returnFood;
    }

    public Environment buyEnvironment(AnimalTypes.AllAnimals animal) {
        Environment returnEnvironment;
        switch(animal.toString()) {
            case "SCARLET_MACAW":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.SCARLET_MACAW);
                break;
            case "GREY_PARROT":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.GREY_PARROT);
                break;
            case "PALLID_PARROT":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.PALLID_PARROT);
                break;
            case "BALL_PYTHON":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.BALL_PYTHON);
                break;
            case "CORN_SNAKE":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.CORN_SNAKE);
                break;
            case "ROSY_BOA":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.ROSY_BOA);
                break;
            case "SPINY_SOFTSHELL_TURTLE":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.SPINY_SOFTSHELL_TURTLE);
                break;
            case "RED_EARED_SLIDER":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.RED_EARED_SLIDER);
                break;
            case "BOX_TURTLE":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.BOX_TURTLE);
                break;
            case "BEARDED_DRAGON":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.BEARDED_DRAGON);
                break;
            case "LEOPARD_GECKO":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.LEOPARD_GECKO);
                break;
            case "IGUANA":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.IGUANA);
                break;
            case "FERRET":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.FERRET);
                break;
            case "RAT":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.RAT);
                break;
            case "SKUNK":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.SKUNK);
                break;
            case "HEDGEHOG":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.HEDGEHOG);
                break;
            case "FENNEC_FOX":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.FENNEC_FOX);
                break;
            case "RACCOON":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.RACCOON);
                break;
            case "BEAR":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.BEAR);
                break;
            case "ENGLISH_BERNARD":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.ENGLISH_BERNARD);
                break;
            case "GERMAN_SHEPARD":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.GERMAN_SHEPARD);
                break;
            case "YORKSHIRE_TERRIER":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.YORKSHIRE_TERRIER);
                break;
            case "PIT_BULL":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.PIT_BULL);
                break;
            case "GREYHOUND":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.GREYHOUND);
                break;
            case "SPHINX":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.SPHINX);
                break;
            case "PERSIAN":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.PERSIAN);
                break;
            case "GARFIELD":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.GARFIELD);
                break;
            case "KOREAN_BOBTAIL":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.KOREAN_BOBTAIL);
                break;
            case "CROW":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.CROW);
                break;
            case "TOUCAN":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.TOUCAN);
                break;
            case "PIDGEON":
                returnEnvironment = new Environment(null, AnimalTypes.AllAnimals.PIGEON);
                break;
            default:
                returnEnvironment = new Environment(null, null);
        }
        return returnEnvironment;
    }

    public Pet buyPet(AnimalTypes.AllAnimals animal, AnimalTypes.Colors animalColor) {
        Pet returnPet;
        switch(animal.toString()) {
            case "SCARLET_MACAW":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.SCARLET_MACAW, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "GREY_PARROT":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.GREY_PARROT, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "PALLID_PARROT":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.PALLID_PARROT, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "BALL_PYTHON":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.BALL_PYTHON, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "CORN_SNAKE":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.CORN_SNAKE, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "ROSY_BOA":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.ROSY_BOA, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "SPINY_SOFTSHELL_TURTLE":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.SPINY_SOFTSHELL_TURTLE, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "RED_EARED_SLIDER":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.RED_EARED_SLIDER, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "BOX_TURTLE":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.BOX_TURTLE, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "BEARDED_DRAGON":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.BEARDED_DRAGON, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "LEOPARD_GECKO":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.LEOPARD_GECKO, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "IGUANA":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.IGUANA, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "FERRET":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.FERRET, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "RAT":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.RAT, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "SKUNK":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.SKUNK, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "HEDGEHOG":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.HEDGEHOG, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "FENNEC_FOX":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.FENNEC_FOX, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "RACCOON":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.RACCOON, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "BEAR":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.BEAR, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "ENGLISH_BERNARD":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.ENGLISH_BERNARD, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "GERMAN_SHEPARD":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.GERMAN_SHEPARD, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "YORKSHIRE_TERRIER":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.YORKSHIRE_TERRIER, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "PIT_BULL":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.PIT_BULL, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "GREYHOUND":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.GREYHOUND, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "SPHINX":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.SPHINX, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "PERSIAN":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.PERSIAN, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "GARFIELD":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.GARFIELD, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "KOREAN_BOBTAIL":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.KOREAN_BOBTAIL, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "CROW":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.CROW, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "TOUCAN":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.TOUCAN, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            case "PIDGEON":
                returnPet = new Pet(null, AnimalTypes.AllAnimals.PIGEON, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
                break;
            default:
                returnPet = new Pet(null, null, animalColor, 10, 10, 10, 5, date.getTime(), date.getTime(), date.getTime(), date.getTime(), false);
        }
        return returnPet;
    }

    public int getPriceOfPet(AnimalTypes.AllAnimals animal) {
        int petCost = 0;
        switch(animal.toString()) {
            case "SCARLET_MACAW":
                petCost = 200;
                break;
            case "GREY_PARROT":
                petCost = 180;
                break;
            case "PALLID_PARROT":
                petCost = 160;
                break;
            case "BALL_PYTHON":
                petCost = 80;
                break;
            case "CORN_SNAKE":
                petCost = 60;
                break;
            case "ROSY_BOA":
                petCost = 100;
                break;
            case "SPINY_SOFTSHELL_TURTLE":
                petCost = 40;
                break;
            case "RED_EARED_SLIDER":
                petCost = 50;
                break;
            case "BOX_TURTLE":
                petCost = 70;
                break;
            case "BEARDED_DRAGON":
                petCost = 100;
                break;
            case "LEOPARD_GECKO":
                petCost = 80;
                break;
            case "IGUANA":
                petCost = 300;
                break;
            case "FERRET":
                petCost = 80;
                break;
            case "RAT":
                petCost = 40;
                break;
            case "SKUNK":
                petCost = 300;
                break;
            case "HEDGEHOG":
                petCost = 80;
                break;
            case "FENNEC_FOX":
                petCost = 400;
                break;
            case "RACCOON":
                petCost = 150;
                break;
            case "BEAR":
                petCost = 1000;
                break;
            case "ENGLISH_BERNARD":
                petCost = 250;
                break;
            case "GERMAN_SHEPARD":
                petCost = 200;
                break;
            case "YORKSHIRE_TERRIER":
                petCost = 300;
                break;
            case "PIT_BULL":
                petCost = 400;
                break;
            case "GREYHOUND":
                petCost = 420;
                break;
            case "SPHINX":
                petCost = 150;
                break;
            case "PERSIAN":
                petCost = 120;
                break;
            case "GARFIELD":
                petCost = 3000;
                break;
            case "KOREAN_BOBTAIL":
                petCost = 250;
                break;
            case "CROW":
                petCost = 300;
                break;
            case "TOUCAN":
                petCost = 400;
                break;
            case "PIDGEON":
                petCost = 200;
                break;
            default:
                petCost = 0;
        }
        return petCost;
    }

    public int sellPet(AnimalTypes.AllAnimals animal, int stars) {
        int returnCostPet = 0;
        switch(animal.toString()) {
            case "SCARLET_MACAW":
                returnCostPet = 50*(stars -1);
                break;
            case "GREY_PARROT":
                returnCostPet = 45*(stars -1);
                break;
            case "PALLID_PARROT":
                returnCostPet = 40*(stars -1);
                break;
            case "BALL_PYTHON":
                returnCostPet = 20*(stars -1);
                break;
            case "CORN_SNAKE":
                returnCostPet = 30*(stars -1);
                break;
            case "ROSY_BOA":
                returnCostPet = 25*(stars -1);
                break;
            case "SPINY_SOFTSHELL_TURTLE":
                returnCostPet = 10*(stars -1);
                break;
            case "RED_EARED_SLIDER":
                returnCostPet = 25*(stars -1);
                break;
            case "BOX_TURTLE":
                returnCostPet = 17*(stars -1);
                break;
            case "BEARDED_DRAGON":
                returnCostPet = 25*(stars -1);
                break;
            case "LEOPARD_GECKO":
                returnCostPet = 20*(stars -1);
                break;
            case "IGUANA":
                returnCostPet = 75*(stars -1);
                break;
            case "FERRET":
                returnCostPet = 20*(stars -1);
                break;
            case "RAT":
                returnCostPet = 10*(stars -1);
                break;
            case "SKUNK":
                returnCostPet = 75*(stars -1);
                break;
            case "HEDGEHOG":
                returnCostPet = 15*(stars -1);
                break;
            case "FENNEC_FOX":
                returnCostPet = 100*(stars -1);
                break;
            case "RACCOON":
                returnCostPet = 37*(stars -1);
                break;
            case "BEAR":
                returnCostPet = 250*(stars -1);
                break;
            case "ENGLISH_BERNARD":
                returnCostPet = 62*(stars -1);
                break;
            case "GERMAN_SHEPARD":
                returnCostPet = 50*(stars -1);
                break;
            case "YORKSHIRE_TERRIER":
                returnCostPet = 75*(stars -1);
                break;
            case "PIT_BULL":
                returnCostPet = 100*(stars -1);
                break;
            case "GREYHOUND":
                returnCostPet = 105*(stars -1);
                break;
            case "SPHINX":
                returnCostPet = 37*(stars -1);
                break;
            case "PERSIAN":
                returnCostPet = 30*(stars -1);
                break;
            case "GARFIELD":
                returnCostPet = 0;
                break;
            case "KOREAN_BOBTAIL":
                returnCostPet = 62*(stars -1);
                break;
            case "CROW":
                returnCostPet = 75*(stars -1);
                break;
            case "TOUCAN":
                returnCostPet = 100*(stars -1);
                break;
            case "PIDGEON":
                returnCostPet = 50*(stars -1);
                break;
            default:
                returnCostPet = 0;
        }
        return returnCostPet;
    }
}