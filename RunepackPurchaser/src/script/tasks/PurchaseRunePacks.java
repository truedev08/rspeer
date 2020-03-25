package script.tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class PurchaseRunePacks extends Task {

    SceneObject door = SceneObjects.getNearest("Door");
    Npc aubury = Npcs.getNearest("Aubury");

    @Override
    public boolean validate() {
        Log.info("PurchaseRunePacks Validate");
        return Main.state == State.PURCHASE_RUNE_PACK;
    }

    @Override
    public int execute() {
        Log.fine("PurchaseRunePacks Execute");
        if (Inventory.getCount(true, Items.COINS.getName()) < 5000) {
            Log.severe("Not enough coins!");
            Main.state = State.LOG_OUT;
            return 1000;
        }

        if (Locations.AUBURYS_RUNE_SHOP_AREA.getArea().contains(Players.getLocal())) {
            Tabs.open(Tab.INVENTORY);
            if (aubury != null) {
                Log.severe("Check 1");
                aubury.interact("Trade");
                Log.severe("Check 2");
                Time.sleepUntil(Shop::isOpen, 2500);
                if (!Shop.isOpen()) {
                    return 1000;
                }
                Log.severe("Check 3");
                if (Shop.getItems() != null) {
                    Log.severe("Check 4");
                    if (!Inventory.isFull()) {
                        if (Shop.getQuantity("Earth rune pack") > 18) {
                            Shop.buyFifty("Earth rune pack");
                            Log.severe("Check 5");
                        } else if (Shop.getQuantity("Fire rune pack") > 18) {
                            Shop.buyFifty("Fire rune pack");
                            Log.severe("Check 6");
                        } else {
                            Log.severe("Check 7");
                            if (Shop.isOpen()) {
                                Shop.close();
                                Log.severe("Check 8");
                                Time.sleepUntil(() -> !Shop.isOpen(), 10000);
                            }
                            Main.state = State.HOP_WORLD;
                            return 1000;
                        }
                        //Main.state = State.OPEN_RUNE_PACK;
                        Log.severe("Check 9");
                    }
                }
                Main.state = State.OPEN_RUNE_PACK;
                Log.severe("Check 10");
            } else {
                Log.severe("Check 11");
                return 1000;
            }
        } else {
            Main.state = State.TRAVERSE;
        }
        return 1000;
    }
}
