package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.tasks.*;

import java.util.Random;

@ScriptMeta(name = "scripts.SapphireRingEnchanter", desc = "Enchants Sapphire Rings", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static Random r = new Random();
    public static double timeGaussian = r.nextGaussian() * 65;

    public static boolean NEED_TO_WITHDRAW = false;

    public static boolean CHECK_START = true;

    public static boolean COSMIC_RUNE_NEED_TO_BUY = false;
    public static boolean SAPPHIRE_RING_NEED_TO_BUY = false;
    public static boolean STAFF_OF_WATER_NEED_TO_BUY = false;
    public static boolean COINS_NEED_MORE = false;

    public static boolean RING_OF_RECOIL_NEED_TO_SELL = false;

    public static STATE state = STATE.WALK_TO_GE;
    public static WITHDRAW_STATE withdrawBankingState = WITHDRAW_STATE.FINISHED_WITHDRAW;

    public enum STATE {
        WALK_TO_GE,
        NEED_MORE_COINS,
        BUY_GE,
        BUY_COSMIC_RUNE,
        BUY_SAPPHIRE_RING,
        SELL_RING_OF_RECOIL,
        BANKING,
        ENCHANTING,
        LOG_OUT,
        ;
    }

    public enum WITHDRAW_STATE {
        FINISHED_WITHDRAW,
        WITHDRAW_COSMIC_RUNE,
        WITHDRAW_SAPPHIRE_RING,
    }


    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new LogOut(), new Traverse(), new Banking(), new Enchant());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }

}
