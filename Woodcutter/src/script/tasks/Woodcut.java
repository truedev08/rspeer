package script.tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class Woodcut extends Task {

    private static final String CHOP_DOWN_ACTION = "Chop down";
    private static final String TREE_NAME = "Tree";

    @Override
    public boolean validate() {
        return !Players.getLocal().isAnimating() && !Players.getLocal().isMoving();
    }

    @Override
    public int execute() {
        SceneObject tree = SceneObjects.getNearest(x -> x.getName().equals(TREE_NAME) && Traverse.TREE_AREA.contains(x));

        if (tree != null) {
            tree.interact(CHOP_DOWN_ACTION);
        }
        return 1200;
    }

}
