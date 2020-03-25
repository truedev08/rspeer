package script.tasks;

import org.rspeer.networking.dax.walker.models.PlayerDetails;
import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.data.Items;

public class IsStaffEquiped extends Task {

    private static final String STAFF_OF_WATER = "Staff of water";
    private static final String WIELD_STAFF_OF_WATER = "Wield";

    @Override
    public boolean validate() {
        Log.info("Checking to see if Staff of water is equipped...");
        return !Equipment.contains(STAFF_OF_WATER);
    }

    @Override
    public int execute() {
        Log.fine("Staff of water is being equipped.");

        Item staffOfWater = Inventory.getFirst(STAFF_OF_WATER);

        if (Inventory.contains(STAFF_OF_WATER) && !Equipment.contains(STAFF_OF_WATER)) {
            staffOfWater.interact(WIELD_STAFF_OF_WATER);
            return 600;
        } else {
            if (!Bank.isOpen()) {
                Bank.open();
            } else {
                if (!Inventory.isEmpty()) {
                    Bank.depositAllExcept(Items.COSMIC_RUNE.getName());
                }
                Bank.withdraw(STAFF_OF_WATER, 1);
                return 1000;
            }
            Bank.close();
            staffOfWater.interact(WIELD_STAFF_OF_WATER);
        }


        return 1000;
    }
}
