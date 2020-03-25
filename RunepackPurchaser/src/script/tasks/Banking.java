package script.tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.EventListener;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.Locations;
import script.data.State;

public class Banking extends Task {
    SceneObject door = SceneObjects.getNearest("Door");

    @Override
    public boolean validate() {
        Log.info("Banking Validate");
        return Main.state == State.BANKING;
    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");
        if (!Locations.BANK_AREA.getArea().contains(Players.getLocal())) {
            if (door.getId() == 1111) {
                door.click();
                Time.sleepUntil(() -> door.getId() == 2222 , 3000);
                return 1000;
            } else {
                Movement.walkTo(Locations.BANK_AREA.getArea().getCenter());
                Time.sleepUntil(() -> Locations.BANK_AREA.getArea().contains(Players.getLocal()), 20000);
            }
        } else {
            Bank.open();
            Time.sleepUntil(Bank::isOpen, 2000);
            if (Bank.getItems() != null) {
                if (Bank.getCount(Items.COINS.getName()) < 100000) {
                    Main.state = State.LOG_OUT;
                    return 1000;
                } else {
                    Bank.withdrawAll(Items.COINS.getName());
                }
            }
        }

        return 1000;

    }
}
