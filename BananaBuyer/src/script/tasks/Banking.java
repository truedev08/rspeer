package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class Banking extends Task {
    Item banana = Inventory.getFirst(Items.BANANA.getName());
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

        if (checkForLogOut()) {
            Main.state = State.LOG_OUT;
            return 1000;
        }
        withdrawCoins();

        Main.state = State.BUYING_BANANA;
        Log.severe("BUYING_THREAD state is set");
        Bank.close();

        return 1000;
    }

    public void withdrawCoins () {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (Bank.getItems() != null) {
            if (Inventory.getCount(true, Items.COINS.getName()) < 10000 || !Inventory.contains(Items.COINS.getName())) {
                Bank.withdrawAll(Items.COINS.getName());
            }
        }
    }

    public boolean checkForLogOut() {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (Bank.getItems() != null) {
            if(Inventory.getCount(true, Items.COINS.getName()) <= 1000 && Bank.getCount(Items.COINS.getName()) <= 1000) {
                Main.state = State.LOG_OUT;
                Log.severe("Checked for Log out, not enough coins");
                return true;
            }
        }
        return false;
    }
}
