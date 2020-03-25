package script.tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.*;

public class BuyingBanana extends Task {

    Npc solihib = Npcs.getNearest("Solihib");
    //SceneObject curtain = SceneObjects.getNearest(Objects.CURTAIN.getName());

    @Override
    public boolean validate() {
        Log.info("BuyingThread validate");
        return Main.state == State.BUYING_BANANA;
    }

    @Override
    public int execute() {
        Log.fine("BuyingThread execute");
        if (inventoryFull()) {
            return 1000;
        }
        Log.severe("Check 1");
        if (!Locations.SOLIHIB_FOOD_STALL_AREA.getArea().contains(Players.getLocal())) {
            Log.severe("Check 2");
            Movement.walkTo(Locations.SOLIHIB_FOOD_STALL_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.SOLIHIB_FOOD_STALL_AREA.getArea().contains(Players.getLocal()), 30000);
            Log.severe("Check 3");
            /*
            if (curtain.getId() == 1533 && !Locations.SOLIHIB_FOOD_STALL_AREA.getArea().contains(Players.getLocal())) {
                Log.severe("Check 4");
                curtain.interact("Open");
                Time.sleepUntil(() -> curtain.getId() == 1534, 1000);
            }
             */
            return 1000;
        } else {
            Log.severe("Check 6");
            Time.sleepUntil(() -> Game.getState() != Game.STATE_HOPPING_WORLD, 10000);
            Npcs.getNearest("Dommik").interact(Interactions.TRADE.getInteraction());
            Time.sleepUntil(Shop::isOpen, 2000);
            if (Shop.getItems() != null) {
                Log.severe("Check 7");
                if (Shop.getQuantity(Items.BANANA.getName()) >= 1) {
                    Log.severe("Check 8");
                    if (Shop.getQuantity(Items.BANANA.getName()) >= 51) {
                        for (int i = 0; i <= 19; i++) {
                            Shop.buyFifty(Items.BANANA.getName());
                            Time.sleep((int)Main.twentyTimeGaussian + 700);
                        }
                    } else {
                        Shop.buyFifty(Items.BANANA.getName());
                    }
                }
                Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
                Shop.close();
            }
        }

        Time.sleep((int)Main.twentyTimeGaussian + 600);
        Main.state = State.HOPPING_WORLDS;

        return 1000;
    }

    public boolean inventoryFull() {
        if (Inventory.isFull()) {
            Main.state = State.BANKING;
            return true;
        } else {
            return false;
        }
    }
}
