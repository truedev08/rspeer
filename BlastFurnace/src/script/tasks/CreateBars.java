package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import script.Main;
import script.data.Items;
import script.data.State;

public class CreateBars extends Task {

    SceneObject sink = SceneObjects.getNearest("Sink");
    SceneObject conveyorBelt = SceneObjects.getNearest("Conveyor belt");
    SceneObject barDispenser = SceneObjects.getNearest("Bar dispenser");

    Item bucketOfWater = Inventory.getFirst("Bucket of water");



    @Override
    public boolean validate() {
        return Main.state == State.CREATE_BARS;
    }

    @Override
    public int execute() {

        if (Inventory.contains(Items.COAL.getName()) && Inventory.contains(Items.IRON_ORE.getName()) && Inventory.contains(Items.BUCKET_OF_WATER.getName())) {
            conveyorBelt.click();
            Inventory.use(x -> x.getName().contains("water"), barDispenser);
            barDispenser.click();
            if (Dialog.canContinue()) {
                Dialog.processContinue();
            }

        }

        Bank.open();

        return 1000;
    }
}
