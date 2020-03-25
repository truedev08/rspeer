package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Interactions;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class Banking extends Task {

    Item bronze_plate_legs = Inventory.getFirst(Items.BRONZE_PLATE_LEGS.getName());
    Item coins = Inventory.getFirst(Items.COINS.getName());

    Npc banker = Npcs.getNearest("Banker");

    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == State.BANKING;

    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");

        if (!Locations.BANK_AREA.getArea().contains(Players.getLocal())) {
            Movement.walkTo(Locations.BANK_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.BANK_AREA.getArea().contains(Players.getLocal()), 30000);
            return 1000;
        } else {
            Bank.open();
            Time.sleepUntil(Bank::isOpen, 5000);
        }

        checkForLogOut();

        if (!Inventory.contains(Items.COINS.getName())) {
            Bank.withdrawAll(Items.COINS.getName());
        }

        depositPlatelegs();

        Main.state = State.BUYING_PLATE_LEGS;
        Log.severe("BUYING_PLATE_LEGS state is set");
        Bank.close();

        return 1000;
    }

    public void withdrawCoins () {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (!Inventory.contains(Items.COINS.getName()) || Inventory.getCount(Items.COINS.getName()) < 1000) {
            if (Bank.getItems() != null) {
                Bank.withdrawAll(Items.COINS.getName());
            }
        }
    }

    public void depositPlatelegs() {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (Bank.isOpen()) {
            if (Inventory.contains(Items.BRONZE_PLATE_LEGS.getName())) {
                Bank.depositAllExcept(Items.COINS.getName());
            }
        }
    }

    public boolean checkForLogOut() {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (Bank.getItems() != null) {
            if(Bank.getCount(Items.COINS.getName()) <= 500 && Inventory.getCount(Items.COINS.getName()) <= 500) {
                Main.state = State.LOG_OUT;
                return true;
            }
        }
        return false;
    }
}
