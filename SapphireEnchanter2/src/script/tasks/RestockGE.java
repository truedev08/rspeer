package script.tasks;

import org.rspeer.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.states.TaskState;

public class RestockGE extends Task {

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
        return Main.taskState == TaskState.BUYING_GRAND_EXCHANGE;
    }

    @Override
    public int execute() {
        Log.fine("RestockGE Execute");

        if (!Bank.isOpen()) {
            Bank.open();
            Time.sleep(Main.timeGaussian + 950);
        }
        Bank.depositInventory();
        Time.sleep(Main.timeGaussian + 950);

        if (!Inventory.contains(COINS) || Inventory.getCount(COINS) < 200000) {
            Bank.withdraw(COINS, 700000);
            Time.sleep(Main.timeGaussian + 950);
        }

        Bank.close();
        Time.sleep(Main.timeGaussian + 950);

        if (!GrandExchange.isOpen()) {
            GrandExchange.open();
            Time.sleep(Main.timeGaussian + 950);
        }









        if (Main.COSMIC_RUNE_NEED_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(COSMIC_RUNE);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 100);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(1000);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep(Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep(Main.timeGaussian + 950);
            Main.COSMIC_RUNE_NEED_BUY = false;
        }

        if (Main.SAPPHIRE_RING_NEED_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(SAPPHIRE_RING);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 100);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(1000);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep(Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep(Main.timeGaussian + 950);
            Main.SAPPHIRE_RING_NEED_BUY = false;
        }

        if (Main.STAFF_OF_WATER_NEED_BUY) {
            GrandExchange.createOffer(RSGrandExchangeOffer.Type.BUY);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setItem(STAFF_OF_WATER);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setPrice(GrandExchangeSetup.getPricePerItem() + 500);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.setQuantity(2);
            Time.sleep(Main.timeGaussian + 950);
            GrandExchangeSetup.confirm();
            Time.sleep(Main.timeGaussian + 950);
            GrandExchange.collectAll();
            Time.sleep(Main.timeGaussian + 950);
            Main.STAFF_OF_WATER_NEED_BUY = false;
        }

        GrandExchange.collectAll();
        Time.sleep(1000);
        Interfaces.closeAll();
        Time.sleep(1000);

        if (!Inventory.isEmpty()) {
            Bank.open();
            Time.sleep(Main.timeGaussian + 950);
            Bank.depositInventory();
            Time.sleep(Main.timeGaussian + 950);
            Bank.close();
        }

        Main.taskState = TaskState.BANKING;
        return 1000;
    }
}
