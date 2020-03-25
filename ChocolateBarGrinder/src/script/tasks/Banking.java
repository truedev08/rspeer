package script.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import script.Main;
import script.data.Items;
import script.data.State;

public class Banking extends Task {

    Item chocolate_bar = Inventory.getFirst(Items.CHOCOLATE_BAR.getName());
    Item knife = Inventory.getLast(Items.KNIFE.getName());
    public static String choco = "Chocolate bar";

    @Override
    public boolean validate() {
        Log.info("Banking Validate");

        return Main.state == State.BANKING;

    }

    @Override
    public int execute() {
        Log.fine("Banking Execute");

        if(Inventory.contains(Items.CHOCOLATE_BAR.getName()) && Inventory.contains(Items.KNIFE.getName()) && !chocolate_bar.isNoted()) {
            Main.state = State.CHOCOLATE_GRINDING;
            return 800;
        }

        if(!Bank.isOpen()) {
            Bank.open();
            Time.sleepUntil(Bank::isOpen, 5000);
        }

        if (Bank.getItems() != null) {
            if (checkForLogOut()) {
                if (Bank.isOpen()) {
                    Time.sleep((int)Main.sixFiveTimeGaussian + 950);
                    Bank.close();
                }
                return 1000;
            }
            withdrawKnife();
            withdrawChocolateBar();
        }




        Time.sleep((int)Main.sixFiveTimeGaussian + 500);
        if (Bank.isOpen()) {
            Bank.close();
        }

        Main.state = State.CHOCOLATE_GRINDING;
        return 800;
    }

    public boolean checkForLogOut() {
        if(!Bank.contains(Items.CHOCOLATE_BAR.getName()) || !Bank.contains(Items.KNIFE.getName()) && !Inventory.contains(Items.KNIFE.getName())) {
            Main.state = State.LOG_OUT;
            return true;
        }
        return false;
    }

    public void withdrawChocolateBar() {
        if (!Inventory.contains(Items.CHOCOLATE_BAR.getName())) {
            Bank.depositAllExcept(Items.KNIFE.getName());
            if (Bank.getWithdrawMode() == Bank.WithdrawMode.NOTE) {
                Bank.setWithdrawMode(Bank.WithdrawMode.ITEM);
            } else {
                Bank.withdrawAll(Items.CHOCOLATE_BAR.getName());
            }
        }
    }

    public void withdrawKnife() {
        if (!Inventory.contains(Items.KNIFE.getName())) {
            Bank.withdraw(Items.KNIFE.getName(), 1);
            Time.sleepUntil(() -> Inventory.contains(Items.KNIFE.getName()), 1000);
        }
    }

    public void checkBankLoad() {

    }
}
