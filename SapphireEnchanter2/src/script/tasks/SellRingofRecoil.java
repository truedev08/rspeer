package script.tasks;

import org.rspeer.commons.Time;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.states.TaskState;

public class SellRingofRecoil extends Task {
    @Override
    public boolean validate() {
        Log.info("SellRingOfRecoil Validate");
        return Main.taskState == TaskState.SELLING_GRAND_EXCHANGE;
    }

    @Override
    public int execute() {
        Log.fine("SellRingOfRecoil Execute");
        Item ringOfRecoil = Inventory.getLast(Items.RING_OF_RECOIL.getName());

        if (Main.RING_OF_RECOIL_NEED_SELL) {
            GrandExchange.open();
            Time.sleep(1000);
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.SELL);
            Time.sleep(1000);
            GrandExchangeSetup.setItem(Items.RING_OF_RECOIL.getName());
            Time.sleep(1000);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() - 50);
            Time.sleep(1000);
            GrandExchangeSetup.confirm();
            Time.sleep(1000);
            GrandExchange.collectAll();
            Time.sleep(1000);
            Main.RING_OF_RECOIL_NEED_SELL = false;
            Interfaces.closeAll();
        }



        Main.taskState = TaskState.BANKING;

        return 1000;
    }
}
