package script.tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.*;

public class BuyingThread extends Task {


    Npc dommik = Npcs.getNearest("Dommik");
    SceneObject curtain = SceneObjects.getNearest(Objects.CURTAIN.getName());

    @Override
    public boolean validate() {
        Log.info("BuyingThread validate");
        return Main.state == State.BUYING_THREAD;
    }

    @Override
    public int execute() {
        Log.fine("BuyingThread execute");
        if (inventoryFull()) {
            return 1000;
        }
        Log.severe("Check 1");
        if (!Locations.DOMMICKS_CRAFTING_STORE_AREA_OUTER.getArea().contains(Players.getLocal())) {
            Log.severe("Check 2");
            Movement.walkTo(Locations.DOMMICKS_CRAFTING_STORE_AREA_OUTER.getArea().getCenter());
            Time.sleepUntil(() -> Locations.DOMMICKS_CRAFTING_STORE_AREA_OUTER.getArea().contains(Players.getLocal()), 30000);
            Log.severe("Check 3");
            if (curtain.getId() == 1533 && !Locations.DOMMICKS_CRAFTING_STORE_AREA_INNER.getArea().contains(Players.getLocal())) {
                Log.severe("Check 4");
                curtain.interact("Open");
                Time.sleepUntil(() -> curtain.getId() == 1534, 1000);
            }
            return 1000;
        } else {
            Log.severe("Check 6");
            Time.sleepUntil(() -> Game.getState() != Game.STATE_HOPPING_WORLD, 10000);
            Npcs.getNearest("Dommik").interact(Interactions.TRADE.getInteraction());
            Time.sleepUntil(Shop::isOpen, 2000);
            if (Shop.getItems() != null) {
                Log.severe("Check 7");
                if (Shop.getQuantity(Items.NEEDLE.getName()) >= 1) {
                    Shop.buyFive(Items.NEEDLE.getName());
                    Time.sleep((int)Main.twentyTimeGaussian + 630);
                }
                if (Shop.getQuantity(Items.THREAD.getName()) >= 1) {
                    Log.severe("Check 8");
                    if (Shop.getQuantity(Items.THREAD.getName()) >= 51) {
                        for (int i = 0; i <= 1; i++) {
                            Shop.buyFifty(Items.THREAD.getName());
                            Time.sleep((int)Main.twentyTimeGaussian + 700);
                        }
                    } else {
                        Shop.buyFifty(Items.THREAD.getName());
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
