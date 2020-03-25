package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.tasks.Banking;

public class RestockSellGrandExchange extends Task {

    private static final String COINS = "Coins";
    private static final String COSMIC_RUNE = "Cosmic rune";
    private static final String SAPPHIRE_RING = "Sapphire ring";
    private static final String STAFF_OF_WATER = "Staff of water";
    //private static final String SELL_ALL_RING_OF_RECOIL = "Sell All";
    //public static final Area GRAND_EXCHANGE_AREA = Area.rectangular(3160,3493,3169,3485);

    @Override
    public boolean validate() {
        Log.info("RestockGE Validate");
        //return Main.COSMIC_RUNE_NEED_TO_BUY || Main.SAPPHIRE_RING_NEED_TO_BUY || Main.STAFF_OF_WATER_NEED_TO_BUY;
        return Main.state == Main.STATE.BUY_GE;
    }

    @Override
    public int execute() {
        Log.fine("RestockGE Execute");

        if (!Bank.isOpen()) {
            Bank.open();
            Time.sleep((int)Main.timeGaussian + 950);
        }
        Bank.depositInventory();
        Time.sleep((int)Main.timeGaussian + 950);

        if (!Inventory.contains(COINS) || Inventory.getCount(COINS) < 200000) {
            Bank.withdraw(COINS, 700000);
            Time.sleep((int)Main.timeGaussian + 950);
        }

        Bank.close();
        Time.sleep((int)Main.timeGaussian + 950);

        if (!GrandExchange.isOpen()) {
            GrandExchange.open();
            Time.sleep((int)Main.timeGaussian + 950);
        }

        if (Main.COSMIC_RUNE_NEED_TO_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(COSMIC_RUNE);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 100);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(1000);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep((int)Main.timeGaussian + 950);
            Main.COSMIC_RUNE_NEED_TO_BUY = false;
        }

        if (Main.SAPPHIRE_RING_NEED_TO_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(SAPPHIRE_RING);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 100);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(1000);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep((int)Main.timeGaussian + 950);
            Main.SAPPHIRE_RING_NEED_TO_BUY = false;
        }

        if (Main.STAFF_OF_WATER_NEED_TO_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(STAFF_OF_WATER);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 500);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(2);
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep((int)Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep((int)Main.timeGaussian + 950);
            Main.STAFF_OF_WATER_NEED_TO_BUY = false;
        }


        GrandExchange.collectAll();
        Time.sleep((int)Main.timeGaussian + 950);
        Interfaces.closeAll();
        Time.sleep((int)Main.timeGaussian + 950);

        if (!Inventory.isEmpty()) {
            Bank.open();
            Time.sleep((int)Main.timeGaussian + 950);
            Bank.depositInventory();
            Time.sleep((int)Main.timeGaussian + 950);
            Bank.close();
        }

        Main.state = Main.STATE.BANKING;
        return 1000;
    }
}
