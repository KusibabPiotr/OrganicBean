package ka.piotr.organicbean.product.model;

public enum FoodType {
    STARTERS("Starter"),
    MAIN("Main"),
    SIDES("Sides"),
    DESSERTS("Desserts");

    private String description;

    FoodType(String description) {
        this.description = description;
    }
}
