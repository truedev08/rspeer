package script;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;
import script.tasks.Banking;
import script.tasks.Drop;
import script.tasks.Traverse;
import script.tasks.Woodcut;

@ScriptMeta(name = "scripts.Woodcutter", desc = "Cuts wood", developer = "Truedev", category = ScriptCategory.WOODCUTTING)
public class FirstScript extends TaskScript {

    public static final String REGULAR_LOGS = "Logs";
    public static boolean powercutting = false;

    @Override
    public void onStart() {
        Log.fine("Script Started");
        submit(new Banking(),
                new Drop(),
                new Traverse(),
                new Woodcut());
    }

    @Override
    public void onStop() {
        Log.severe("Script Stopped");
    }

}
