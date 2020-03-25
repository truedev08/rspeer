package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Enchant extends Task {

    private static final String ENCHANT_JEWLERY = "Enchant";
    private static final String SAPPHIRE_RING = "Sapphire ring";
    private static final String RING_OF_RECOIL = "Ring of recoil";

    @Override
    public boolean validate() {
        return !Players.getLocal().isAnimating() && !Players.getLocal().isMoving();
    }

    @Override
    public int execute() {
        Item sapphire_ring;

        for (Item jewelery : Inventory.getItems(x -> x.getName().equals(SAPPHIRE_RING))) {
            Magic.cast(Spell.Modern.LEVEL_1_ENCHANT, jewelery);
            Time.sleep(150,250);
        }

       return 600;
    }

}
