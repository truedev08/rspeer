package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;

public class Banking extends Task {
    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == Main.STATE.BANKING;

    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");
        if(!Bank.isOpen()) {
            Bank.open();
        }


        if (!Main.NEED_TO_WITHDRAW) {
            Time.sleep((int)Main.timeGaussian + 950);
            Bank.depositInventory();
        }

        if (checkForLogOut()) {
            if (Bank.isOpen()) {
                Time.sleep((int)Main.timeGaussian + 950);
                Bank.close();
            }
            return 1000;
        }

        Main.NEED_TO_WITHDRAW = true;
        while (Main.NEED_TO_WITHDRAW) {
            Time.sleep((int) Main.timeGaussian + 800);
            withdrawForPotions();
            Time.sleep((int) Main.timeGaussian + 900);
        }
        Main.state = Main.STATE.MAKING_POTION;
        Log.severe("This should only show up when MAKING_POTION state has been set");
        Bank.close();

        return 1000;
    }

    public void withdrawForPotions () {
            if (!Inventory.contains(Items.VIAL_OF_WATER.getName())) {
                Time.sleep((int)Main.timeGaussian + 1000);
                Bank.withdraw(Items.VIAL_OF_WATER.getName(), 14);
                Log.severe("Checked for Ranarr potion (unf) withdrawal");
                //Main.withdrawBankingState = Main.withdrawBankingState.WITHDRAW_COSMIC_RUNE;
            } else if (!Inventory.contains(Items.RANARR_WEED.getName())) {
                Time.sleep((int)Main.timeGaussian + 1000);
                Bank.withdraw(Items.RANARR_WEED.getName(), 14);
                Log.severe("Checked for Ranarr weed withdrawal");
                //Main.withdrawBankingState = Main.withdrawBankingState.WITHDRAW_SAPPHIRE_RING;
            } else {
                //Main.withdrawBankingState = Main.withdrawBankingState.FINISHED_WITHDRAW;
                Main.NEED_TO_WITHDRAW = false;
                Log.severe("After checking for withdrawal conditions, it's ready to make potions");
            }
    }

    public boolean checkForLogOut() {
        if(Bank.getCount(Items.VIAL_OF_WATER.getName()) <= 0 && !Inventory.contains(Items.VIAL_OF_WATER.getName())) {
            Main.state = Main.STATE.LOG_OUT;
            return true;

        } else if (Bank.getCount(Items.RANARR_WEED.getName()) <= 0 && !Inventory.contains(Items.RANARR_WEED.getName())) {
            Main.state = Main.STATE.LOG_OUT;
            return true;
        }
        return false;
    }
}
