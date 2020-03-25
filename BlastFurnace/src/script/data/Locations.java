package script.data;


import org.rspeer.runetek.api.movement.position.Area;

public enum Locations {

    BLAST_FURNACE_AREA(Area.rectangular(3251, 3404, 3255, 3399, -1));
    ;

    private Area area;

    Locations(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}
