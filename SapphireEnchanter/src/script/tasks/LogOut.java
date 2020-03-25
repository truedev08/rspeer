package script.tasks;

import org.rspeer.runetek.api.ClientSupplier;
import org.rspeer.runetek.api.Game;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptExecutor;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.Main;

public class LogOut extends Task {

    @Override
    public boolean validate() {
        Log.info("Log Out Validate");
        return Main.state == Main.STATE.LOG_OUT;

    }

    @Override
    public int execute() {
        Log.fine("Logging out");
        if (Game.isLoggedIn()) {
            Game.logout();
            Log.severe("Logged out...");
            ScriptExecutor.stop();
        }
        return 1000;
    }
}
