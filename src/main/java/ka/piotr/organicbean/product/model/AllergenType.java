package ka.piotr.organicbean.product.model;

public enum AllergenType {
    NONE("none"),
    GLUTENFREE("glutenFree"),
    VEGAN("vegan"),
    VEGETARIAN("vegetarian");

    private String description;

    private AllergenType(String description) {
        this.description = description;
    }

}
