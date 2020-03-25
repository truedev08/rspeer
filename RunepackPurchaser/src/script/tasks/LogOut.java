
package script.tasks;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.State;

public class LogOut extends Task {

    @Override
    public boolean validate() {
        Log.info("Log Out Validate");
        return Main.state == State.LOG_OUT;
    }

    @Override
    public int execute() {
        Log.severe("Logging out");
        if (Game.isLoggedIn()) {
            Game.logout();
            Log.severe("Logged out...");
            return -1;
        }
        return 1000;
    }
}