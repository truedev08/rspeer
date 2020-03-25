package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    NARDAH_ARMOR_SHOP_AREA(Area.rectangular(3404,2925,3409,2920)) {

    }

    ;

    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
