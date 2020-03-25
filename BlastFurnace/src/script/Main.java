package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.tasks.CreateBars;
import script.tasks.Traverse;

@ScriptMeta(name = "Blast Furnace", desc = "Blast Furnace", developer = "Truedev", category = ScriptCategory.MONEY_MAKING)
public class Main extends TaskScript {

    public static script.data.State state = script.data.State.CHECK_START;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new CreateBars(), new Traverse());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }
}
