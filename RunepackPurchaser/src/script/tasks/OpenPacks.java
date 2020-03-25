package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.State;

public class OpenPacks extends Task {

    Item runepack = Inventory.getFirst((Item) -> Item.getName().contains("rune pack"));

    @Override
    public boolean validate() {
        Log.info("OpenPacks Validate");
        return Main.state == State.OPEN_RUNE_PACK;
    }

    @Override
    public int execute() {
        Log.fine("OpenPacks Execute");
        for (Item pack : Inventory.getItems(x -> x.getName().contains("rune pack"))) {
            pack.click();
            Time.sleep((int)Main.twentyTimeGaussian + 50);
        }
        Main.state = State.PURCHASE_RUNE_PACK;
        return 100;
    }
}
