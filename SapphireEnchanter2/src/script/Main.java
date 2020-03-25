package script;


import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.event.listeners.GameStateListener;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.states.DepositBankingState;
import script.states.TaskState;
import script.states.WithdrawBankingState;
import script.tasks.*;

import java.util.Random;

@ScriptMeta(name = "Sapphire Enchanter 2.0", desc = "Enchants Sapphire rings", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static Random r = new Random();
    public static int timeGaussian = (int)r.nextGaussian() * 50;

    public static boolean TIME_TO_LOG_OUT = false;

    public static boolean NEED_TO_WITHDRAW = false;

    public static boolean COSMIC_RUNE_NEED_BUY = false;
    public static boolean SAPPHIRE_RING_NEED_BUY = false;
    public static boolean STAFF_OF_WATER_NEED_BUY = false;

    public static boolean RING_OF_RECOIL_NEED_SELL = false;

    public static boolean WITHDRAW_COSMIC_RUNE = false;

    public static TaskState taskState = TaskState.WALKING;
    public static DepositBankingState depositBankingState = DepositBankingState.BANKING_COMPLETED;
    public static WithdrawBankingState withdrawBankingState = WithdrawBankingState.BANKING_COMPLETED;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        checkLogIn();
        submit(new LogIn(), new LogOut(), new Traverse(), new Banking(), new SellRingofRecoil(), new RestockGE(), new Enchant());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }

    public TaskState checkLogIn() {
        if (Game.isLoggedIn()) {
            Log.fine("Begin Walking");
            return taskState = TaskState.WALKING;
        }
        else {
            Log.severe("Must Log In");
            return taskState = TaskState.LOG_IN;
        }
    }

    public void timeToLogOut() {
        if (TIME_TO_LOG_OUT) {
            taskState = TaskState.LOG_OUT;
        }
    }
}
