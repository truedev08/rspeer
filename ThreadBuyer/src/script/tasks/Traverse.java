package script.tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class Traverse extends Task {

    @Override
    public boolean validate() {
        Log.info("Traverse Validate");
        return Main.state == State.TRAVERSE;
    }

    @Override
    public int execute() {
        Log.fine("Traverse Execute");
        if (Locations.DOMMICKS_CRAFTING_STORE_AREA_OUTER.getArea().contains(Players.getLocal()) && Inventory.getCount(true, Items.COINS.getName()) > 5000) {
            Main.state = State.BUYING_THREAD;
            return 1000;
        } else if(!Locations.BANK_AREA.getArea().contains(Players.getLocal())) {
            Movement.walkTo(Locations.BANK_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.BANK_AREA.getArea().contains(Players.getLocal()), 15000);
        } else if (Locations.BANK_AREA.getArea().contains(Players.getLocal())) {
            Main.state = State.BANKING;
            return 1000;
        }
        return 1000;
    }
}
