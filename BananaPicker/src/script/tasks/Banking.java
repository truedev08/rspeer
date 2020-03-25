package script.tasks;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.*;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.*;

public class Banking extends Task {
    Item banana = Inventory.getFirst(Items.BANANA.getName());
    Item coins = Inventory.getFirst(Items.COINS.getName());

    Npc seamen = Npcs.getNearest((npc) -> npc.getName().contains("Seaman"));
    SceneObject gangplank = SceneObjects.getNearest(Objects.GANGPLANK.getName());
    Npc customs_officer = Npcs.getNearest("Customs officer");

    SceneObject bankDepositBox = SceneObjects.getNearest("Bank deposit box");

    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == State.BANKING;
    }

    @Override
    public int execute() {
        if (!Locations.BANK_DEPOSIT_AREA.getArea().contains(Players.getLocal())) {
            walkTo(Locations.KARMAJA_SHIP_AREA.getArea().getCenter(), Locations.BANK_DEPOSIT_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.BANK_DEPOSIT_AREA.getArea().contains(Players.getLocal()), 20000);
        }
        bankDeposit();
        Main.state = State.PICK_BANANA;
        return 1000;
    }

    public boolean checkForLogOut() {
        Time.sleep((int)Main.twentyTimeGaussian + 300);
        if (Bank.getItems() != null) {
            if(Inventory.getCount(true, Items.COINS.getName()) <= 100) {
                Main.state = State.LOG_OUT;
                Log.severe("Checked for Log out, not enough coins");
                return true;
            }
        }
        return false;
    }

    public void bankDeposit() {
        if (Locations.BANK_DEPOSIT_AREA.getArea().contains(Players.getLocal())) {
            Time.sleepUntil(DepositBox::isOpen, 3000);
            DepositBox.depositAllExcept(Items.COINS.getName());
            Time.sleep((int)Main.sixFiveTimeGaussian + 600);
            DepositBox.close();
        }
    }

    public void banking() {
        if (Locations.BANK_DEPOSIT_AREA.getArea().contains(Players.getLocal())) {
            bankDepositBox.interact(Interactions.DEPOSIT.getInteraction());
            Time.sleepUntil(Bank::isOpen, 3000);
            Bank.depositAllExcept(Items.COINS.getName());
            Time.sleep((int)Main.sixFiveTimeGaussian + 600);
            Bank.close();
        }
    }

    public void speakToSeaman() {
        if (Dialog.canContinue()) {
            Dialog.processContinue();
            return;
        }
        if (Dialog.isViewingChatOptions()) {
            Dialog.process("Yes please.", "Can I journey on this ship?", "Search away, I have nothing to hide.", "Ok.");
            Log.severe("1" + Game.getState());
            return;
        }
    }

    public void walkTo(Positionable shipYard, Positionable destination) {
        Movement.walkTo(shipYard);
        speakToSeaman();
        //Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        Time.sleepUntil(() -> !Interfaces.isOpen(299), 1000);
        gangplank.interact("Cross");
        Movement.walkTo(destination);
    }
}
