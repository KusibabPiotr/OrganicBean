package ka.piotr.organicbean.product.model;

public enum AllergenType {
    NONE("none"),
    GLUTEN_FREE("glutenFree"),
    GLUTEN_FREE_VEGAN("glutenFree,vegan"),
    GLUTEN_FREE_VEGAN_VEGETARIAN("glutenFree,vegan,vegetarian"),
    GLUTEN_FREE_VEGETARIAN("glutenFree,vegetarian"),
    VEGAN("vegan"),
    VEGAN_VEGETARIAN("vegan,vegetarian"),
    VEGETARIAN("vegetarian");

    private String description;

    private AllergenType(String description) {
        this.description = description;
    }
}
