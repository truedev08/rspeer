package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    GRAND_EXCHANGE_AREA(Area.rectangular(3333,3333,3333,3333)),
    TAVERLEY_BANK_AREA(Area.rectangular(3333, 3333, 3333, 3333)),
    TAVERLEY_STONE_CIRCLE_AREA(Area.rectangular(3333,3333,3333,3333)),
    TAVERLEY_DUNGEON_AREA(Area.rectangular(3333,3333,3333,3333)),
    CAULDRON_OF_THUNDER_AREA(Area.rectangular(3333,3333,3333,3333)),
    ;




    private Area location;

    Locations(Area location) {
        this.location = location;
    }

    public Area getLocation() {
        return location;
    }
}