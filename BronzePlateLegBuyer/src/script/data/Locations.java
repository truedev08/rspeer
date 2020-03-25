package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {

    BANK_AREA(Area.rectangular(3268,3170,3272,3164)),
    LOUIES_ARMOURED_LEGS_BAZAAR(Area.rectangular(3314,3177,3318,3173)),

    ;



    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
