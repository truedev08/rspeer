package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import script.FirstScript;

public class Drop extends Task {

    private static final String DROP_ACTION = "Drop";

    @Override
    public boolean validate() {
        return Inventory.isFull() && FirstScript.powercutting;
    }

    @Override
    public int execute() {
        for (Item item : Inventory.getItems(x -> !x.getName().equals(FirstScript.REGULAR_LOGS))) {
            item.interact(DROP_ACTION);
            Time.sleep(150,250);
        }
        return 600;
    }
}
