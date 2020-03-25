package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.data.State;
import script.tasks.HopWorld;
import script.tasks.SellToShop;
import script.tasks.Traverse;

import java.util.Random;

@ScriptMeta(name = "Nardah Shop Seller", desc = "Sells items to shop", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static Random r = new Random();
    public static double sixFiveTimeGaussian = r.nextGaussian() * 65;
    public static double twentyTimeGaussian = r.nextGaussian() * 20;

    public static script.data.State state = script.data.State.TRAVERSE;

    @Override
    public void onStart() {
        Log.severe("Script Started");
        submit(new Traverse(), new SellToShop(), new HopWorld());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }
}


