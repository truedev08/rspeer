package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;

public class Banking extends Task {


    private static final String RING_OF_RECOIL = "Ring of recoil";
    private static final String SAPPHIRE_RING = "Sapphire ring";
    private static final String COSMIC_RUNE = "Cosmic rune";
    private static final String COINS = "Coins";
    private static final String STAFF_OF_WATER = "Staff of water";

    Item sapphire_ring = Inventory.getFirst(Items.SAPPHIRE_RING.getName());

    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == Main.STATE.BANKING;

    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");

        checkEquipStaffOfWater();

        if(Inventory.contains(Items.SAPPHIRE_RING.getName()) && Inventory.contains(Items.COSMIC_RUNE.getName()) && !sapphire_ring.isNoted()) {
            Main.state = Main.STATE.ENCHANTING;
            return 1000;
        }

        if(!Bank.isOpen()) {
            Bank.open();
            Time.sleepUntil(() -> Bank.isOpen(), 5000);
        }

        if (checkForLogOut()) {
            if (Bank.isOpen()) {
                Time.sleep((int)Main.timeGaussian + 950);
                Bank.close();
            }
            return 1000;
        }

        if (!Inventory.isEmpty()) {
            Time.sleep((int)Main.timeGaussian + 950);
            Bank.depositAllExcept(COSMIC_RUNE);
        }

        Main.NEED_TO_WITHDRAW = true;
        while (Main.NEED_TO_WITHDRAW) {
            if (Inventory.contains(Items.RING_OF_RECOIL.getName())) {
                Bank.depositAllExcept(Items.COSMIC_RUNE.getName());
            }
            Time.sleep((int)Main.timeGaussian + 800);
            withdrawForEnchanting();
            Time.sleep((int)Main.timeGaussian + 900);
        }
        Main.state = Main.STATE.ENCHANTING;
        Log.severe("This should only show up when ENCHANTING state has been set");
        Bank.close();

        return 1000;
    }

    public void withdrawForEnchanting () {
        if (!Inventory.contains(Items.COSMIC_RUNE.getName())) {
            Time.sleep((int)Main.timeGaussian + 1000);
            Bank.withdrawAll(Items.COSMIC_RUNE.getName());
            Log.severe("Checked for Cosmic rune withdrawal");
            //Main.withdrawBankingState = Main.withdrawBankingState.WITHDRAW_COSMIC_RUNE;
        } else if (!Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            Time.sleep((int)Main.timeGaussian + 1000);
            Bank.withdrawAll(Items.SAPPHIRE_RING.getName());
            Log.severe("Checked for Sapphire ring withdrawal");
            //Main.withdrawBankingState = Main.withdrawBankingState.WITHDRAW_SAPPHIRE_RING;
        } else {
            //Main.withdrawBankingState = Main.withdrawBankingState.FINISHED_WITHDRAW;
            Main.NEED_TO_WITHDRAW = false;
            Log.severe("After checking for withdrawal conditions, it's ready to enchant");
        }
    }

    public boolean checkForLogOut() {
        if(Bank.getCount(Items.COSMIC_RUNE.getName()) <= 0 && !Inventory.contains(Items.COSMIC_RUNE.getName())) {
            Main.state = Main.STATE.LOG_OUT;
            return true;
        } else if (Bank.getCount(Items.SAPPHIRE_RING.getName()) <= 0 && !Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            Main.state = Main.STATE.LOG_OUT;
            return true;
        }
        return false;
    }

    public void checkEquipStaffOfWater() {
        final String wield_staff_of_water = "Wield";

        Item staffOfWater = Inventory.getFirst(STAFF_OF_WATER);

        if (!Inventory.contains(Items.STAFF_OF_WATER.getName()) && !Equipment.contains(Items.STAFF_OF_WATER.getName())) {
            if (!Bank.isOpen()) {
                Bank.open();
            } else {
                if (!Inventory.isEmpty()) {
                    Bank.depositAllExcept(Items.COSMIC_RUNE.getName());
                }
                Log.fine("Staff of water is being equipped.");
                Bank.withdraw(STAFF_OF_WATER, 1);
                Time.sleep((int)Main.timeGaussian + 950);
            }
            Bank.close();
            Time.sleep((int)Main.timeGaussian + 1050);
            staffOfWater.interact(wield_staff_of_water);
        }
    }
}



