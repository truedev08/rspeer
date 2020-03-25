package script.tasks;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import script.Main;

public class Banking extends Task {

    //private static final String RING_OF_RECOIL = "Ring of recoil";
    //private static final String COSMIC_RUNE = "Cosmic rune";

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        if(!Bank.isOpen()) {
            Bank.open();
            return 600;
        }

        Bank.depositAllExcept(Main.COSMIC_RUNE);
        return 1000;
    }
}
