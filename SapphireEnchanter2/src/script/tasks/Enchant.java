package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.states.TaskState;
import script.data.Items;

import java.util.Random;

public class Enchant extends Task {
    @Override
    public boolean validate() {
        Log.info("Enchant Validate");
        return Main.taskState == TaskState.ENCHANTING;
    }

    @Override
    public int execute() {
        Log.fine("Enchant Execute");
        while (Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            for (Item jewelery : Inventory.getItems(x -> x.getName().equals(Items.SAPPHIRE_RING.getName()))) {
                Magic.cast(Spell.Modern.LEVEL_1_ENCHANT, jewelery);
                Time.sleep(Main.timeGaussian + 950);
            }
        }
        Log.fine("Need to bank...");
        Main.taskState = TaskState.BANKING;
        return 600;
    }
}
