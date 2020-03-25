package script.tasks;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.states.TaskState;

public class Traverse extends Task {

    private static final Area GRAND_EXCHANGE_AREA = Area.rectangular(3160,3493,3169,3485);

    @Override
    public boolean validate() {
        Log.info("Traverse Validate");
        return Main.taskState == TaskState.WALKING;
    }

    @Override
    public int execute() {
        Log.fine("Traverse Execute");
        if (GRAND_EXCHANGE_AREA.contains(Players.getLocal())) {
            Main.taskState = TaskState.BANKING;
        } else {
            Movement.walkTo(GRAND_EXCHANGE_AREA.getCenter());
            Log.severe("This message should only show up when walking to GE");
            //Time.sleepUntil(isAtGrandExchange, 1000);
        }
        return 1000;
    }
}
