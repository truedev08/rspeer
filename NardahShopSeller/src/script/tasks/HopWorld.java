package script.tasks;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.State;

import java.lang.reflect.Array;
import java.util.Random;

public class HopWorld extends Task {

    int[] worldArray = {302,303,304,305,306,307,309,310,311,312,313,314,315,317,318,319,320,321,322,323,327,328,329,330,331,332,333,334,336,337,338,339,340,341,342,344,346,347,348,350,351,352,354,355,356,357,358,359,360,362,367,368,369,370,374,375,376,377,378,386,387,388,389,390,395,421,422,424,444,445,465,466,491,492,493,494,495,496,513,514,515,516,517,518,519,520,521,522,523,524,525};
    Random r = new Random();
    int randomWorld = r.nextInt(worldArray.length);

    int currentWorld = 0;

    @Override
    public boolean validate() {
        Log.info("HopWorld Validate");
        return Main.state == State.HOP_WORLD;
    }

    @Override
    public int execute() {
        Log.fine("HopWorld Execute");
        Tabs.open(Tab.LOGOUT);
        hopToWorld(currentWorld);
        Time.sleep((int)Main.sixFiveTimeGaussian + 6000);
        Time.sleepWhile(() -> Game.getState() == Game.STATE_LOADING_GAME, 5000);
        if (Game.getState() == Game.STATE_HOPPING_WORLD) {
            Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        } else if (Game.getState() == Game.STATE_LOADING_REGION) {
            Time.sleep((int)Main.sixFiveTimeGaussian + 1000);
        }
        Main.state = State.SELL_TO_SHOP;
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
                //Wait until the world select screen is loaded
                Time.sleepUntil(() -> worldSwitcher.getMaterialId() != -1, 2000);
            }
        }
        //Hop to world
        if(worldSelect != null){
            WorldHopper.hopTo(nextWorld(worldArray));
        }
    }

    public int nextWorld(int[] world) {
        currentWorld++;
        if (currentWorld == 92) {
            currentWorld = 0;
        }
        int x = worldArray[currentWorld];
        return x;
    }
}
