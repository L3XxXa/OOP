package ru.nsu.malov.json.setup;

public class JsonSetupCook {
    private int cookingExperience;

    /**
     * JsonSetupCook class constructor for cook serialize Json
     * @param cookingExperience - cook's working experience
     * */
    public JsonSetupCook(int cookingExperience) {
        this.cookingExperience = cookingExperience;
    }

    /**
     * Get cook's working experience
     * @return cook's working experience
     * */
    public int getCookingExperience() {
        return cookingExperience;
    }

    /**
     * Set cook' ID
     * @param cookingExperience - cook's working experience
     * */
    public void setCookingExperience(int cookingExperience) {
        this.cookingExperience = cookingExperience;
    }
}
