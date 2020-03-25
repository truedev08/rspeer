package script.tasks;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Traverse extends Task {

    public static final Area TREE_AREA = Area.rectangular(3174, 3262, 3190, 3250);

    @Override
    public boolean validate() {
        return !TREE_AREA.contains(Players.getLocal());
    }

    @Override
    public int execute() {
        Movement.walkTo(TREE_AREA.getCenter());
        return 1000;
    }
}
