package script.tasks;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Traverse extends Task {

    public static final Area GRAND_EXCHANGE_AREA = Area.rectangular(3160,3493,3169,3485);

    @Override
    public boolean validate() {
        return !GRAND_EXCHANGE_AREA.contains(Players.getLocal());
    }

    @Override
    public int execute() {
        Movement.walkTo(GRAND_EXCHANGE_AREA.getCenter());
        return 1000;
    }
}
