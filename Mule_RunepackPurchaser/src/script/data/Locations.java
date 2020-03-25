package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    BANK_AREA(Area.rectangular(3250,3424,3257,3419)),
    AUBURYS_RUNE_SHOP_AREA(Area.rectangular(3251, 3404, 3255, 3399)),
    ;


    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
