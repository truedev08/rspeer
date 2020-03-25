package script.tasks;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.State;

import java.util.Random;

public class HopWorld extends Task {

    int[] worldArray = {301,308,316,326,335,371,379,380,382,383,384,393,394,397,398,399,418,425,426,430,431,433,434,435,436,437,438,439,440,451,452,453,454,455,456,457,458,459,469,470,471,472,473,474,475,476,497,498,499,500,501,502,503,504};
    Random r = new Random();
    int randomWorld = r.nextInt(worldArray.length);

    @Override
    public boolean validate() {
        Log.info("HopWorld Validate");
        return Main.state == State.HOPPING_WORLDS;
    }

    @Override
    public int execute() {
        Log.fine("HopWorld Execute");
        Tabs.open(Tab.LOGOUT);
        hopToWorld(worldArray[randomWorld]);
        Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        if (Game.getState() == Game.STATE_HOPPING_WORLD) {
            Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        } else if (Game.getState() == Game.STATE_LOADING_REGION) {
            Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        }
        Main.state = State.BUYING_BANANA;
        return 1000;
    }

    public void hopToWorld(int world){
        //Initiate interfaces
        InterfaceComponent worldSwitcher = Interfaces.getComponent(182,3);
        InterfaceComponent worldSelect = Interfaces.getComponent(69,0);
        //Open logout tab
        Tabs.open(Tab.LOGOUT);
        //Open world selector
        if(worldSwitcher != null){
            if(worldSwitcher.getMaterialId() == -1){
                worldSwitcher.interact("World Switcher");
                //Wait untill the world select screen is loaded
                Time.sleepUntil(() -> worldSwitcher.getMaterialId() != -1, 2000);
            }
        }
        //Hop to world
        if(worldSelect != null){
            WorldHopper.randomHopInF2p();
        }
    }
}
