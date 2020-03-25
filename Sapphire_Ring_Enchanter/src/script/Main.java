package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;


@ScriptMeta(name = "Jewel Enchanter", desc = "Enchants Sapphire Rings", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    //public static final String RING_OF_RECOIL = "Ring of recoil";
    //public static final String SAPPHIRE_RING = "Sapphire ring";
    public static final String COSMIC_RUNE = "Cosmic rune";

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit();
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }


}