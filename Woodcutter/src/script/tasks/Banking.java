package script.tasks;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import script.FirstScript;

public class Banking extends Task {

    @Override
    public boolean validate() {
        return Inventory.isFull() && !FirstScript.powercutting;
    }

    @Override
    public int execute() {
        if (!Bank.isOpen()) {
            Bank.open();
            return 600;
        }

        Bank.depositAll(FirstScript.REGULAR_LOGS);
        return 1000;
    }

}