package script.data;

public enum Objects {

    SINK("Sink"),
    CONVEYOR_BELT("Conveyor belt"),
    BAR_DISPENSER("Bar dispenser"),


    ;

    private String name;

    Objects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
