package script.data;

public enum Interactions {
    TRADE("Trade"),
    BANK("Bank"),
    ;

    private String interaction;

    Interactions(String interaction) {
        this.interaction = interaction;
    }

    public String getInteraction() {
        return interaction;
    }
}
