package script.tasks;

import org.rspeer.script.task.Task;
import script.Main;
import script.data.STATE;


public class Healing extends Task {
     @Override
    public boolean validate() {
         return Main.state == STATE.HEALING;
     }

     @Override
    public int execute() {
         return 1000;
     }
}
