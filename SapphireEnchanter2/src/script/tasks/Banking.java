package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Booleans;
import script.states.DepositBankingState;
import script.states.TaskState;
import script.data.Items;
import script.states.WithdrawBankingState;

public class Banking extends Task {

    private int X = 100;
    public static boolean bankIsOpen = false;

    //items = new Items[];


    @Override
    public boolean validate() {
        return Main.taskState == TaskState.BANKING;
    }

    @Override
    public int execute() {

        if(!Bank.isOpen()) {
            Bank.open();
        }
        checkForRestock();

        if (checkForRestock()) {
            Main.taskState = TaskState.BUYING_GRAND_EXCHANGE;
            return 1000;
        }

        if (Inventory.contains(Items.RING_OF_RECOIL.getName()) && Inventory.getCount(Items.RING_OF_RECOIL.getName()) < 50 && Bank.getCount(Items.RING_OF_RECOIL.getName()) < 50) {
            Main.depositBankingState = DepositBankingState.DEPOSIT_INVENTORY_EXCEPT_COSMIC_RUNE;
        } else {
            //Main.depositBankingState = DepositBankingState.BANKING_COMPLETED;
        }

        switch (Main.depositBankingState) {
            case DEPOSIT_COSMIC_RUNE:
                Bank.depositAll(Items.COSMIC_RUNE.getName());
                break;
            case DEPOSIT_SAPPHIRE_RING:
                Bank.depositAll(Items.SAPPHIRE_RING.getName());
                break;
            case DEPOSIT_RING_OF_RECOIL:
                Bank.depositAll(Items.RING_OF_RECOIL.getName());
                break;
            case DEPOSIT_STAFF_OF_WATER:
                Bank.depositAll(Items.STAFF_OF_WATER.getName());
                break;
            case DEPOSIT_INVENTORY_EXCEPT_COSMIC_RUNE:
                Bank.depositAllExcept(Items.COSMIC_RUNE.getName());
                break;
            case DEPOSIT_INVENTORY:
                Bank.depositInventory();
                break;
        }

        Main.NEED_TO_WITHDRAW = true;
        while (Main.NEED_TO_WITHDRAW) {
            withdrawForEnchanting();
            switch (Main.withdrawBankingState) {
                case WITHDRAW_COSMIC_RUNE:
                    Bank.depositAll(Items.COSMIC_RUNE.getName());
                    break;
                case WITHDRAW_SAPPHIRE_RING:
                    Bank.depositAll(Items.SAPPHIRE_RING.getName());
                    break;
                case WITHDRAW_RING_OF_RECOIL:
                    Bank.setWithdrawMode(Bank.WithdrawMode.NOTE);
                    Bank.depositAll(Items.RING_OF_RECOIL.getName());
                    break;
                case WITHDRAW_STAFF_OF_WATER:
                    Bank.depositAll(Items.STAFF_OF_WATER.getName());
                    break;
            }
        }
        Main.taskState = TaskState.ENCHANTING;
        return 1000;
    }

    public boolean checkForRestock() {
        if (Bank.getCount(Items.COSMIC_RUNE.getName()) > 30 || Inventory.getCount(Items.COSMIC_RUNE.getName()) > 30) {
            Log.fine("Enough Cosmic runes");
            Booleans.cosmicRuneBuy = false;
        } else {
            Log.severe("Must buy more Cosmic runes");
            Booleans.cosmicRuneBuy = true;
            return true;
        }

        if (Bank.getCount(Items.SAPPHIRE_RING.getName()) > 30 || Inventory.getCount(Items.SAPPHIRE_RING.getName()) > 30) {
            Log.fine("Enough Sapphire rings");
            Booleans.sapphireRingBuy = false;
        } else {
            Log.severe("Must buy more Sapphire rings");
            Booleans.sapphireRingBuy = true;
            return true;
        }

        if (Bank.getCount(Items.STAFF_OF_WATER.getName()) >= 1 || Equipment.contains(Items.STAFF_OF_WATER.getName()) || Inventory.contains(Items.STAFF_OF_WATER.getName())) {
            Log.fine("Enough Staff of water");
            Booleans.staffOfWaterBuy = false;
        } else {
            Log.severe("Must buy Staff of water");
            Booleans.staffOfWaterBuy = true;
            return true;
        }
        return false;
    }

    public void withdrawForEnchanting () {
        if (!Inventory.contains(Items.COSMIC_RUNE.getName()) || Inventory.getCount(Items.COSMIC_RUNE.getName()) < 30) {
            //Bank.withdraw(Items.COSMIC_RUNE.getName(), 30);
            Main.withdrawBankingState = WithdrawBankingState.WITHDRAW_COSMIC_RUNE;
        } else if (!Inventory.contains(Items.SAPPHIRE_RING.getName()) || Inventory.getCount(Items.SAPPHIRE_RING.getName()) < 27) {
            //Bank.withdrawAll(Items.SAPPHIRE_RING.getName());
            Main.withdrawBankingState = WithdrawBankingState.WITHDRAW_SAPPHIRE_RING;
        } else {
            Main.withdrawBankingState = WithdrawBankingState.BANKING_COMPLETED;
            Main.NEED_TO_WITHDRAW = false;
        }
    }
}
