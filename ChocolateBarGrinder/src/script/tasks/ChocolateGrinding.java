package script.tasks;


import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.State;

public class ChocolateGrinding extends Task {

    Item chocolate_bar = Inventory.getLast(Items.CHOCOLATE_BAR.getName());

    @Override
    public boolean validate() {
        Log.info("ChocolateGrinding Validate");
        return Main.state == State.CHOCOLATE_GRINDING;
    }

    @Override
    public int execute() {
        Log.fine("ChocolateGrinding Execute");
        if(!Inventory.contains(Items.CHOCOLATE_BAR.getName())) {
            Main.state = State.BANKING;
            return 600;
        }
        makeChocolateDust();
        return 90;
    }

    public void makeChocolateDust() {
        Item chocolate_bar = Inventory.getLast(Items.CHOCOLATE_BAR.getName());
        Inventory.use(x -> x.getName().equals(Items.KNIFE.getName()), chocolate_bar);
        Time.sleep((int)Main.twentyTimeGaussian + 120);
    }
}
