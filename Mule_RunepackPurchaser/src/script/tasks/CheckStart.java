package script.tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class CheckStart extends Task {

    @Override
    public boolean validate() {
        Log.info("CheckStart Validate");
        return Main.state == State.CHECK_START;
    }

    @Override
    public int execute() {
        Log.fine("CheckStart Execute");
        return 1000;
    }
}
