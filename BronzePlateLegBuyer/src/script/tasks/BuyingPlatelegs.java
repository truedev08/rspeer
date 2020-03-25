package script.tasks;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import script.Main;
import script.data.*;

public class BuyingPlatelegs extends Task {

    Area louies_armoured_legs_bazaar = Locations.LOUIES_ARMOURED_LEGS_BAZAAR.getArea();

    Npc lousie_legs = Npcs.getNearest("Lousie Legs");

    @Override
    public boolean validate() {
        return Main.state == State.BUYING_PLATE_LEGS;
    }

    @Override
    public int execute() {
        if (!Locations.LOUIES_ARMOURED_LEGS_BAZAAR.getArea().contains(Players.getLocal())) {
            Movement.walkTo(Locations.LOUIES_ARMOURED_LEGS_BAZAAR.getArea().getCenter());
            Time.sleepUntil(() -> Locations.LOUIES_ARMOURED_LEGS_BAZAAR.getArea().contains(Players.getLocal()), 30000);
            return 1000;
        } else {
            lousie_legs.interact(Interactions.TRADE.getInteraction());
            Time.sleepUntil(Shop::isOpen, 2000);
        }

        if (Shop.getQuantity(Items.BRONZE_PLATE_LEGS.getName()) >= 1) {
            Shop.buyFive(Items.BRONZE_PLATE_LEGS.getName());
        } else{
            Shop.close();
        }

        if (Inventory.isFull()) {
            Main.state = State.BANKING;
            return 1000;
        }

        Time.sleep((int)Main.twentyTimeGaussian + 600);
        Main.state = State.HOPPING_WORLDS;

        return 1000;
    }

    public void inventoryFull() {
        if (Inventory.isFull()) {
            Main.state = State.BANKING;
        }
    }
}
