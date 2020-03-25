package script.data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {
    BANK_DEPOSIT_AREA(Area.rectangular(3040,3237,3050,3234)),
    BANANA_FARM_AREA(Area.rectangular(2909,3156,2925,3166)),
    PORT_SARIM_SHIP_AREA(Area.rectangular(3024,3224,3029,3212)),
    KARMAJA_SHIP_AREA(Area.rectangular(2951,3149,2957,3146)),

    ;

    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
