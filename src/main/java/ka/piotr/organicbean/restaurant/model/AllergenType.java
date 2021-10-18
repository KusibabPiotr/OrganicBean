package ka.piotr.organicbean.restaurant.model;

import ka.piotr.organicbean.restaurant.exceptions.NoSuchAllergenTypeException;

public enum AllergenType {
    NONE("none"),
    GLUTENFREE("glutenFree"),
    VEGAN("vegan"),
    VEGETARIAN("vegetarian");

    private String description;

    private AllergenType(String description) {
        this.description = description;
    }

    public static AllergenType getTypeFromDescription(String description) throws NoSuchAllergenTypeException {
        for (AllergenType value : AllergenType.values()) {
            if (value.description.equals(description)) return value;
        }
        throw new NoSuchAllergenTypeException();
    }

}
