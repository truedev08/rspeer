package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Trade;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.providers.RSItemDefinition;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.State;

import java.util.function.Predicate;

public class TradeMule extends Task {

    private static final Predicate<Item> RESTRICTED_ITEMS = item -> {
        String name = item.getName();
        return name.contains("x") || name.contains("y");
    };

    public static final Predicate<Item> ITEM_TO_MULE = item -> {
        if (RESTRICTED_ITEMS.test(item)) {
            return false;
        }
        RSItemDefinition definition = item.getDefinition();
        return definition != null && definition.isTradable() || item.isExchangeable();
    };

    private String muleName = "Mule";

    @Override
    public boolean validate() {
        return Inventory.contains(ITEM_TO_MULE) || Trade.isOpen();
    }

    @Override
    public int execute() {
        if (!Trade.isOpen()) {
            Player mule = Players.getNearest(p->muleName.toLowerCase().equals(p.getName().toLowerCase()));
            if (mule == null) {
                Log.info("No mule found");
            } else if (mule.interact("Trade with")) {
                Time.sleepUntil(Trade::isOpen, 10000);
            }
        } else {
            if (Trade.isOpen(true) && Trade.isWaitingForMe()) {
                Log.info("Accepting second screen");
                Trade.accept();
            } else {
                if (Inventory.contains(ITEM_TO_MULE)) {
                    Log.info("Offering items");
                    for (Item item : Inventory.getItems(ITEM_TO_MULE)) {
                        Trade.offerAll(item.getId());
                    }
                } else if (Trade.isWaitingForMe()) {
                    Log.info("Accepting first screen");
                    Trade.accept();
                }
            }
        }
        return 1000;
    }
}
