package script.tasks;

import org.rspeer.script.task.Task;
import script.Main;
import script.data.State;

public class Muling extends Task {

    @Override
    public boolean validate() {
        return Main.state == State.MULING;
    }

    @Override
    public int execute() {
        return 1000;
    }
}
