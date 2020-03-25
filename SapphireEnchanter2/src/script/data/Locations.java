package script.data;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;

public enum Locations {

    GRAND_EXCHANGE_AREA(Area.rectangular(3160,3493,3169,3485)),

    ;


    private Area location;

    Locations(Area location) {
        this.location = location;
    }

    public void setLocation(Area location) {
        this.location = location;
    }

    public Area getLocation() {
        return location;
    }
}
