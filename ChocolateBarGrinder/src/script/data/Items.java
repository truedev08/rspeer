package script.data;

public enum Items {
    CHOCOLATE_BAR("Chocolate bar", 13000),
    KNIFE("Knife", 100),
    ;

    private String name;
    private int buyLimit;

    Items(String name, int buyLimit) {
        this.name = name;
        this.buyLimit = buyLimit;
    }

    public String getName() {
        return name;
    }

    public int getBuyLimit() {
        return buyLimit;
    }
}
