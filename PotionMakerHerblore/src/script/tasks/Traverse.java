package script.tasks;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;

public class Traverse extends Task {
    public static final Area GRAND_EXCHANGE_AREA = Area.rectangular(3160,3493,3169,3485);

    @Override
    public boolean validate() {
        Log.info("Traverse Validate");
        return Main.state == Main.STATE.WALK_TO_GE;
    }

    @Override
    public int execute() {
        Log.fine("Traverse Execute");
        if(!GRAND_EXCHANGE_AREA.contains(Players.getLocal())) {
            Movement.walkTo(GRAND_EXCHANGE_AREA.getCenter());
        }
        if (GRAND_EXCHANGE_AREA.contains(Players.getLocal())) {
            Main.state = Main.STATE.BANKING;
            return 1000;
        }
        return 1000;
    }
}
