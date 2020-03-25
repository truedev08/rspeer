package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.tasks.Healing;

@ScriptMeta(name = "Quest: Drudic Ritual", desc = "Drudic Ritual Quest", developer = "Truedev", category = ScriptCategory.QUESTING)
public class Main extends TaskScript {


    public static script.data.STATE state = script.data.STATE.HEALING;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new Healing());
    }


    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }


}
