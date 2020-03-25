package script.tasks;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.states.TaskState;

public class LogIn extends Task {

    @Override
    public boolean validate() {
        return Main.taskState == TaskState.LOG_IN;
    }

    @Override
    public int execute() {
        Login.enterCredentials("JulietteChinetti@hotmail.com", "si70dw");
        Log.fine("Logging in");
        if(Game.isLoggedIn()) {
            Main.taskState = TaskState.WALKING;
        }
        return 1000;
    }
}
