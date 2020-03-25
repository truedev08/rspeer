package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.data.State;
import script.tasks.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

@ScriptMeta(name = "Mule Runepack Buyer", desc = "Mules for Runepack Buyer", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    //Socket socket = new Socket("localhost", 14000);

    public static Random r = new Random();
    public static double sixFiveTimeGaussian = r.nextGaussian() * 65;
    public static double twentyTimeGaussian = r.nextGaussian() * 20;

    public static script.data.State state = script.data.State.TRADE;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new Traverse(), new TradePlayer());
        Log.severe("Check 1");
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }

}
