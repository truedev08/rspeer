package script.tasks;

import org.rspeer.script.task.Task;
import script.Main;
import script.data.State;

public class LogOut extends Task {

    @Override
    public boolean validate() {
        return Main.state == State.LOG_OUT;
    }

    @Override
    public int execute() {
        return 1000;
    }
}
