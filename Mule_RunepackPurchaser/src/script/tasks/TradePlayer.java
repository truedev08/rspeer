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
import script.data.Accounts;
import script.data.Locations;
import script.data.State;

import java.util.function.Predicate;

public class TradePlayer extends Task {

    //String[] playerArray = {"Pentik", "Ridepurge0", "James"};

    private String traderName = "";
    private boolean tradePending = false;


    @Override
    public boolean validate() {
        Log.info("TradePlayer Validate");
        return Main.state == State.TRADE;

    }

    @Override
    public int execute() {
        Log.fine("TradePlayer Execute");

        /*
        for (Accounts account : Accounts.values()) {
            accountSelect(account);
            Players.getNearest(traderName).interact("Trade with");
        }
         */

        Players.getNearest(Accounts.ACCOUNT_2.getUsername()).interact("Trade with ");
        return 1000;
    }

    public void accountSelect(Accounts x) {
        if (x.getBotType() == "Money Maker") {
            traderName = x.getUsername();
            Players.getNearest(traderName).interact("Trade with");
        }

    }

}

