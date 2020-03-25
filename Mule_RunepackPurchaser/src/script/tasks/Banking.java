package script.tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.EventListener;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class Banking extends Task {
    SceneObject door = SceneObjects.getNearest("Door");

    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == State.Banking;
    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");
        return 1000;

    }
}
