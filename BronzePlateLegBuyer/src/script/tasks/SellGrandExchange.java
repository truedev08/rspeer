package script.tasks;

import org.rspeer.script.task.Task;
import script.Main;
import script.data.State;

public class SellGrandExchange extends Task {

    @Override
    public boolean validate() {
        return Main.state == State.SELLING_BRONZE_LEGS_GE;
    }

    @Override
    public int execute() {
        return 1000;
    }
}
