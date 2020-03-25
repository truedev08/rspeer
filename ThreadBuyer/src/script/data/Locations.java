package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    BANK_AREA(Area.rectangular(3268,3169,3273,3164)),
    DOMMICKS_CRAFTING_STORE_AREA_INNER(Area.rectangular(3318,3197,3323,3191)),
    DOMMICKS_CRAFTING_STORE_AREA_OUTER(Area.rectangular(3315,3197,3323,3191)),

    ;



    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
