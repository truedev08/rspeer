package script.tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Locations;
import script.data.State;

public class Traverse extends Task {

    SceneObject door = SceneObjects.getNearest("Door");

    @Override
    public boolean validate() {
        Log.info("Traverse Validate");
        return Main.state == State.TRAVERSE;
    }

    @Override
    public int execute() {
        Log.fine("Traverse Execute");
        if (!Locations.NARDAH_ARMOR_SHOP_AREA.getArea().contains(Players.getLocal())) {
            Movement.walkTo(Locations.NARDAH_ARMOR_SHOP_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.NARDAH_ARMOR_SHOP_AREA.getArea().contains(Players.getLocal()), 50000);
            if (door.getId() == 1111) {
                door.click();
                return 1000;
            } else {
                Main.state = State.SELL_TO_SHOP;
            }
        } else {
            Main.state = State.SELL_TO_SHOP;
        }
        return 1000;
    }
}
