package script.tasks;

import org.rspeer.script.task.Task;

public class RunToggle extends Task {

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int execute() {
        return 1000;
    }
}
