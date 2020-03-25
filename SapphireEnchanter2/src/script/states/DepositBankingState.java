package script.states;

public enum DepositBankingState {

    DEPOSIT_ITEMS,

    DEPOSIT_ALL,
    DEPOSIT_INVENTORY,
    DEPOSIT_INVENTORY_EXCEPT_COSMIC_RUNE,
    DEPOSIT_STAFF_OF_WATER,
    DEPOSIT_RING_OF_RECOIL,
    DEPOSIT_SAPPHIRE_RING,
    DEPOSIT_COSMIC_RUNE,

    IS_NOTED,
    IS_ITEM,

    BANKING_COMPLETED,

    ;

    DepositBankingState() {
    }
}
