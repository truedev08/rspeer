package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Locations;
import script.data.State;

public class SellToShop extends Task {

    Npc Seddu = Npcs.getNearest("Seddu");

    public static final int x = 10;

    private String[] items = {"Black med helm", "Steel kiteshield", "Green d'hide vamb", "Green d'hide chaps", "Rune chainbody", "Rune platelegs", "Rune plateskirt"};


    @Override
    public boolean validate() {
        return Main.state == State.SELL_TO_SHOP;
    }

    @Override
    public int execute() {

        if (Locations.NARDAH_ARMOR_SHOP_AREA.getArea().contains(Players.getLocal())) {
            Log.severe("Check 1");
            if (Seddu != null) {
                Log.severe("Check 2");
                Seddu.interact("Trade");
                Log.severe("Check 3");
                Time.sleepUntil(Shop::isOpen, 5000);
                if (!Shop.isOpen()) {
                    Log.severe("Check 4");
                    return 1000;
                }
                if (Shop.getItems() != null) {
                    Log.severe("Check 5");
                    Time.sleep((int)Main.twentyTimeGaussian + 1000);
                    for (int i = 0; i < items.length; i++) {
                        Time.sleep((int)Main.sixFiveTimeGaussian + 300);
                        Log.severe("Check 6");
                        if (Inventory.contains(items[i])) {
                            Log.severe("Check 7");
                            if (Shop.getQuantity(items[i]) == 0) {
                                Log.severe("Check 8");
                                for (int x = 0; x < 4; x++) {
                                    Log.severe("Check 9");
                                    Time.sleep((int)Main.sixFiveTimeGaussian + 300);
                                    Shop.sellOne(items[i]);
                                    Log.severe("Check 10");
                                }
                            } else if (Shop.getQuantity(items[i]) == 1) {
                                Log.severe("Check 8");
                                for (int x = 0; x < 3; x++) {
                                    Log.severe("Check 9");
                                    Time.sleep((int)Main.sixFiveTimeGaussian + 300);
                                    Shop.sellOne(items[i]);
                                    Log.severe("Check 10");
                                }
                            } else if (Shop.getQuantity(items[i]) == 2) {
                                Log.severe("Check 8");
                                for (int x = 0; x < 2; x++) {
                                    Log.severe("Check 9");
                                    Time.sleep((int)Main.sixFiveTimeGaussian + 300);
                                    Shop.sellOne(items[i]);
                                    Log.severe("Check 10");
                                }
                            } else if (Shop.getQuantity(items[i]) == 3) {
                                Log.severe("Check 11");
                                Time.sleep((int)Main.sixFiveTimeGaussian + 300);
                                Shop.sellOne(items[i]);
                                Log.severe("Check 12");
                            }
                        }
                    }
                    if (Shop.isOpen()) {
                        Time.sleep((int)Main.sixFiveTimeGaussian + 200);
                        Shop.close();
                        Log.severe("Check Shop is closed");
                        Time.sleepUntil(() -> !Shop.isOpen(), 10000);
                    }
                    Main.state = State.HOP_WORLD;
                    return 1000;
                }
            }
        } else {
            Main.state = State.TRAVERSE;
            return 1000;
        }
        return 1000;
    }

}


