package script.tasks;


import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Definitions;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.*;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;

public class SellRingOfRecoil extends Task {

    @Override
    public boolean validate() {
        Log.info("SellRingOfRecoil Validate");
        return Main.state == Main.STATE.SELL_RING_OF_RECOIL;
    }

    @Override
    public int execute() {
        Log.fine("SellRingOfRecoil Execute");
        Item ringOfRecoil = Inventory.getLast(Items.RING_OF_RECOIL.getName());

        if (Main.RING_OF_RECOIL_NEED_TO_SELL) {
            GrandExchange.open();
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.SELL);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(Items.RING_OF_RECOIL.getName());
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() - 50);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep((int)Main.timeGaussian + 950);
            Main.RING_OF_RECOIL_NEED_TO_SELL = false;
            Interfaces.closeAll();
        }



        Main.state = Main.STATE.BANKING;

        return 1000;
    }
}
