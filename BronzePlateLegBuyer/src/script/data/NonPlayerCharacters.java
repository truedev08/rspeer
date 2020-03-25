package script.data;

public enum NonPlayerCharacters {
    LOUIE_LEGS("Louie Legs"),
    BANKER("Banker"),
    ;

    private String name;

    NonPlayerCharacters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
