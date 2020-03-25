package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.tasks.Banking;
import script.tasks.LogOut;
import script.tasks.MakePotion;
import script.tasks.Traverse;

import java.util.Random;

@ScriptMeta(name = "Potion maker Herblore", desc = "Creates Herblore potions", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static Random r = new Random();
    public static double timeGaussian = r.nextGaussian() * 65;

    public static boolean NEED_TO_WITHDRAW = false;
    public static STATE state = STATE.WALK_TO_GE;

    public enum STATE {
        WALK_TO_GE,
        NEED_MORE_COINS,
        BUY_GE,
        BANKING,
        MAKING_POTION,
        LOG_OUT,
        ;
    }

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new LogOut(), new Traverse(), new Banking(), new MakePotion());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }
}
