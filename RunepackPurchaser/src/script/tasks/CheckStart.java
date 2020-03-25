package script.tasks;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class CheckStart extends Task {

    @Override
    public boolean validate() {
        Log.info("CheckStart Validate");
        return Main.state == State.CHECK_START;
    }

    @Override
    public int execute() {
        Log.fine("CheckStart Execute");
        if (!Locations.AUBURYS_RUNE_SHOP_AREA.getArea().contains(Players.getLocal())) {
            Main.state = State.TRAVERSE;
        } else if (Locations.AUBURYS_RUNE_SHOP_AREA.getArea().contains(Players.getLocal()) && Inventory.contains((Item) -> Item.getName().contains("rune pack"))) {
            Main.state = State.OPEN_RUNE_PACK;
        } else {
            Main.state = State.PURCHASE_RUNE_PACK;
        }
        return 1000;
    }
}
