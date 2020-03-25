package script.tasks;

import org.rspeer.networking.dax.walker.DaxWalker;
import org.rspeer.networking.dax.walker.engine.PathHandler;
import org.rspeer.networking.dax.walker.engine.pathfinding.Region;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.tree.WalkAction;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.*;

import java.util.function.Predicate;

public class PickBanana extends Task {

    SceneObject banana_tree_5 = SceneObjects.getByUid(2073);
    SceneObject banana_tree_4 = SceneObjects.getByUid(2074);
    SceneObject banana_tree_3 = SceneObjects.getByUid(2075);
    SceneObject banana_tree_2 = SceneObjects.getByUid(2076);
    SceneObject banana_tree_1 = SceneObjects.getByUid(2077);
    SceneObject banana_tree_0 = SceneObjects.getByUid(2078);

    Position tree_tile_1 = new Position(3333,3333,333);
    Position tree_tile_2 = new Position(3333,3333,333);
    Position tree_tile_3 = new Position(3333,3333,333);
    Position tree_tile_4 = new Position(3333,3333,333);
    Position tree_tile_5 = new Position(3333,3333,333);
    Position tree_tile_6 = new Position(3333,3333,333);
    Position tree_tile_7 = new Position(3333,3333,333);
    Position tree_tile_8 = new Position(3333,3333,333);

    public SceneObject banana_tree = SceneObjects.getNearest(Objects.BANANA_TREE.getName());

    Npc seamen = Npcs.getNearest((npc) -> npc.getName().contains("Seaman"));
    SceneObject gangplank = SceneObjects.getNearest(Objects.GANGPLANK.getName());
    Npc customs_officer = Npcs.getNearest("Customs officer");

    @Override
    public boolean validate() {
        return Main.state == State.PICK_BANANA;
    }

    @Override
    public int execute() {
        if (!Locations.BANANA_FARM_AREA.getArea().contains(Players.getLocal())) {
            walkTo(Locations.PORT_SARIM_SHIP_AREA.getArea().getCenter(), Locations.BANANA_FARM_AREA.getArea().getCenter());
            Time.sleepUntil(() -> Locations.BANANA_FARM_AREA.getArea().contains(Players.getLocal()), 20000);
        }
        if (isPickable()) {
            banana_tree.interact("Pick");
            return 1000;
        }

        if (Inventory.isFull()) {
            Main.state = State.BANKING;
        }
        return 1000;
    }

    public boolean isPickable() {
        if (banana_tree.getId() < 2078) {
            return true;
        } else {
            return false;
        }
    }

    public void travelToBananaFarm() {

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
        Time.sleepWhile(() -> Interfaces.isOpen(299), 1000);
        gangplank.interact("Cross");
        Movement.walkTo(destination);
    }
}
