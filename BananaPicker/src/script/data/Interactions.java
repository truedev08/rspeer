package script.data;

public enum Interactions {
    TRADE("Trade"),
    BANK("Bank"),
    PAY_FARE("Pay-fare"),
    PICK("Pick"),
    CROSS("Cross"),
    DEPOSIT("Deposit"),
    ;

    private String interaction;

    Interactions(String interaction) {
        this.interaction = interaction;
    }

    public String getInteraction() {
        return interaction;
    }
}
