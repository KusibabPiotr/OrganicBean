package ka.piotr.organicbean.product.model;

public enum DishType {
    STARTERS("Starters"),
    MAIN("Main Menu"),
    SIDES("Sides"),
    DESSERTS("Desserts"),
    DRINKS("Drinks");

    private String description;

    DishType(String description) {
        this.description = description;
    }
}
