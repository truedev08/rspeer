package script.data;

public enum Items {
    BANANA("Banana"),
    COINS("Coins"),
    MONKEY_GREEGREE("Karamjan monkey greegree"),
    MONKEY_SPEAK_AMULET("M'speak amulet"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
