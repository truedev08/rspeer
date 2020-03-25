package script.tasks;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Login;
import org.rspeer.script.task.Task;
import script.Main;
import script.states.TaskState;

public class LogOut extends Task {

    @Override
    public boolean validate() {
        return Main.taskState == TaskState.LOG_OUT;
    }

    @Override
    public int execute() {
        Game.logout();
        return 1000;
    }
}
