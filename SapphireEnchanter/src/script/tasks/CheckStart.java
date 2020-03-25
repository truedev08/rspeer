package script.tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;

public class CheckStart extends Task {

    private static final String COINS = "Coins";
    private static final String COSMIC_RUNE = "Cosmic rune";
    private static final String SAPPHIRE_RING = "Sapphire ring";
    private static final String STAFF_OF_WATER = "Staff of water";
    private static final String RING_OF_RECOIL = "Ring of recoil";

    @Override
    public boolean validate() {
        return Main.CHECK_START;
    }

    @Override
    public int execute() {
        Log.severe("CHECKSTART SHOULD NOT BE RUNNING ANYMORE!!@@!@!");

        if (!Bank.isOpen()) {
            Bank.open();
            Time.sleep((int)Main.timeGaussian + 950);
        }

        if (!Bank.contains(COSMIC_RUNE)) {
            Main.COSMIC_RUNE_NEED_TO_BUY = true;
        }

        if (!Bank.contains(SAPPHIRE_RING)) {
            Main.SAPPHIRE_RING_NEED_TO_BUY = true;
        }

        if (!Bank.contains(STAFF_OF_WATER) && !Equipment.contains(STAFF_OF_WATER)) {
            Main.STAFF_OF_WATER_NEED_TO_BUY = true;
        }

        if (!Bank.contains(COINS)) {
            Log.severe("YOU HAVE NO COINS!!!!@!@@!@!");
        }

        Main.CHECK_START = false;
        return 1000;
    }
}
