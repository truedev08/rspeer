package script.tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.*;

import java.util.function.Predicate;

public class Traverse extends Task {

    Npc customs_officer = Npcs.getNearest("Customs officer");
    Npc seaman_lorris = Npcs.getNearest("Seaman Lorris");
    Npc seaman_tobias = Npcs.getNearest("Seaman Tobias");
    Npc seaman_thresnor = Npcs.getNearest("Seaman Thresnor");

    Npc[] seamenArray = {seaman_thresnor, seaman_lorris, seaman_tobias};

    Npc seamen = Npcs.getNearest((npc) -> npc.getName().contains("Seaman"));

    SceneObject gangplank = SceneObjects.getNearest(Objects.GANGPLANK.getName());

    @Override
    public boolean validate() {
        return Main.state == State.TRAVERSE;
    }

    @Override
    public int execute() {
        Log.fine("Traverse Execute");

        if (Locations.BANANA_FARM_AREA.getArea().contains(Players.getLocal())) {
            Main.state = State.PICK_BANANA;
        } else {
            Movement.walkTo(Locations.BANK_DEPOSIT_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.BANK_DEPOSIT_AREA.getArea().contains(Players.getLocal()), 40000);
            Main.state = State.BANKING;
        }

        return 1000;
    }

    public void travelToBank() {
        if (!Locations.KARMAJA_SHIP_AREA.getArea().contains(Players.getLocal()))    {
            Movement.walkTo(Locations.KARMAJA_SHIP_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.KARMAJA_SHIP_AREA.getArea().contains(Players.getLocal()), 40000);
            return;
        }
        if (!Dialog.isOpen()) {
            customs_officer.interact(Interactions.PAY_FARE.getInteraction());
        } else {
            speakToSeaman();
            Time.sleepUntil(() -> Game.getState() == Game.STATE_LOADING_GAME, 1000);
            gangplank.interact("Cross");
        }
        Movement.walkTo(Locations.BANK_DEPOSIT_AREA.getArea().getCenter());
        Main.needToWalk = false;
    }

    public void travelToBananaFarm() {
        if (!Locations.PORT_SARIM_SHIP_AREA.getArea().contains(Players.getLocal()))    {
            Movement.walkTo(Locations.PORT_SARIM_SHIP_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.PORT_SARIM_SHIP_AREA.getArea().contains(Players.getLocal()), 40000);
            return;
        }
        if (!Dialog.isOpen()) {
            seamen.interact(Interactions.PAY_FARE.getInteraction());
        } else {
            speakToSeaman();
            Log.severe("2", Game.getState());
            Time.sleepUntil(() -> Game.getState() == Game.STATE_LOADING_GAME, 1000);
            gangplank.interact("Cross");
        }
        Movement.walkTo(Locations.BANK_DEPOSIT_AREA.getArea().getCenter());
        Main.needToWalk = false;
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
}
