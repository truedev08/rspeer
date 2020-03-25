package script.tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.ScriptExecutor;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class CheckOnStart extends Task {

    @Override
    public boolean validate() {
        Log.info("CheckOnStart Validate");
        return Main.state == State.CHECK_ON_START;
    }

    @Override
    public int execute() {
        Log.fine("CheckStart Execute");

        if (!Inventory.isFull() && Locations.BANANA_FARM_AREA.getArea().contains(Players.getLocal())) {
            Main.state = State.PICK_BANANA;
            return 1000;
        } else {
            Main.state = State.BANKING;
        }
        return 1000;
    }
}
