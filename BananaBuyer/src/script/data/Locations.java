package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    BANK_AREA(Area.rectangular(3268,3169,3273,3164)),
    SOLIHIB_FOOD_STALL_AREA(Area.rectangular(3268,3169,3273,3164)),
    GRAND_EXCHANGE_AREA(Area.rectangular(3268,3169,3273,3164)),


    ;

    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
