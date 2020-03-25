package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import script.Main;
import script.data.Items;
import script.data.State;

public class Banking extends Task {

    @Override
    public boolean validate() {
        return Main.state == State.BANKING;
    }

    @Override
    public int execute() {

        Bank.depositAllExcept(Items.COINS.getName());

        if (!Inventory.contains(Items.BUCKET_OF_WATER.getName())) {
            Bank.withdraw(Items.BUCKET_OF_WATER.getName(), 1);
        }
        Bank.withdraw(Items.COAL.getName(), 13);
        Bank.withdraw(Items.IRON_ORE.getName(), 13);

        Bank.close();
        return 1000;
    }
}
