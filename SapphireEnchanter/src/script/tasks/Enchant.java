package script.tasks;


import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.Event;
import org.rspeer.runetek.event.EventDispatcher;
import org.rspeer.runetek.event.EventListener;
import org.rspeer.script.LoopTask;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;

import java.beans.EventHandler;

public class Enchant extends Task {

    private static final String ENCHANT_JEWLERY = "Enchant";

    public boolean ITEMS_TRUE = Main.COSMIC_RUNE_NEED_TO_BUY || Main.SAPPHIRE_RING_NEED_TO_BUY || Main.STAFF_OF_WATER_NEED_TO_BUY || Main.RING_OF_RECOIL_NEED_TO_SELL;

    @Override
    public boolean validate() {
        Log.info("Enchant Validate");
        return Main.state == Main.STATE.ENCHANTING;
    }

    @Override
    public int execute() {
        Item sapphire_ring = Inventory.getFirst(Items.SAPPHIRE_RING.getName());
        Log.fine("Enchant Execute");
        Magic.cast(Spell.Modern.LEVEL_1_ENCHANT, sapphire_ring);
        Time.sleep((int)Main.timeGaussian + 300);
        if (Players.getLocal().isAnimating()) {
            Time.sleepUntil(() -> !Inventory.contains(Items.SAPPHIRE_RING.getName()) || Dialog.canContinue(), 116000);
            if (Dialog.canContinue()) {
                while (Dialog.canContinue()) {
                    Dialog.processContinue();
                    Time.sleep((int)Main.timeGaussian + 800);
                }
                return 1000;
            }
        }


        Log.severe("this should not show up when enchanting");
        Time.sleep((int)Main.timeGaussian + 950);
/*
        while (Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            for (Item jewelery : Inventory.getItems(x -> x.getName().equals(Items.SAPPHIRE_RING.getName()))) {
                Magic.cast(Spell.Modern.LEVEL_1_ENCHANT, jewelery);
                Time.sleep(Main.timeGaussian + 1000);
            }
        }
*/

        if (!Inventory.contains(Items.SAPPHIRE_RING.getName())) {
            Log.severe("Need to bank...");
            Main.state = Main.STATE.BANKING;
            return 1000;
        }


        return 1000;
    }
}
