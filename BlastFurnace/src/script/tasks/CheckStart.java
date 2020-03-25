package script.tasks;

import org.rspeer.script.task.Task;
import script.Main;
import script.data.State;

public class CheckStart extends Task {

    @Override
    public boolean validate() {
        return Main.state == State.CHECK_START;
    }

    @Override
    public int execute() {

        Main.state = State.BANKING;
        return 1000;
    }
}
