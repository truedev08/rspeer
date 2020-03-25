package script.tasks;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;

import java.util.function.Predicate;

public class MakePotion extends Task {

    @Override
    public boolean validate() {
        Log.info("MakePotion Validate");
        return Main.state == Main.STATE.MAKING_POTION;
    }

    @Override
    public int execute() {

        Item ranarr_weed = Inventory.getFirst(Items.RANARR_WEED.getName());

        Log.fine("MakePotion Execute");
        Log.severe("clicking!!!");
        makePotion();
        //Time.sleepUntil(() -> Production.isOpen(), 3000);

        //Time.sleep((int)Main.timeGaussian + 300);
        if (Players.getLocal().isAnimating()) {
            Time.sleepUntil(() -> !Inventory.contains(Items.RANARR_WEED.getName()) || !Inventory.contains(Items.VIAL_OF_WATER.getName()) ||Dialog.canContinue(), 116000);
        }
        Time.sleep((int)Main.timeGaussian + 950);
        /*
        while (Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            for (Item jewelery : Inventory.getItems(x -> x.getName().equals(Items.SAPPHIRE_RING.getName()))) {
                Magic.cast(Spell.Modern.LEVEL_1_ENCHANT, jewelery);
                Time.sleep(Main.timeGaussian + 1000);
            }
        }
        */
        if (!Inventory.contains(Items.VIAL_OF_WATER.getName()) || !Inventory.contains(Items.RANARR_WEED.getName())) {
            Log.severe("Need to bank...");
            Main.state = Main.STATE.BANKING;
            return 1000;
        }


        return 1000;
    }

    public int makePotion() {
        Item vial_of_water = Inventory.getFirst(Items.VIAL_OF_WATER.getName());
        Inventory.use(x -> x.getName().equals(Items.RANARR_WEED.getName()), vial_of_water);
        if (Production.isOpen()) {
            Time.sleep((int)Main.timeGaussian + 100);
            Production.initiate();
        }
        return 1000;
    }
}
