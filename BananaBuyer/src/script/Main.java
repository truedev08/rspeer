package script;


import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.tasks.*;

import java.util.Random;

@ScriptMeta(name = "ThreadBuyer", desc = "Buys Threads", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static Random r = new Random();
    public static double sixFiveTimeGaussian = r.nextGaussian() * 65;
    public static double twentyTimeGaussian = r.nextGaussian() * 20;

    public static script.data.State state = script.data.State.TRAVERSE;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new LogOut(), new Traverse(), new Banking(), new BuyingBanana(), new HopWorld());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }

}