package script.data;

public enum Objects {

    DOOR("Door"),

    ;

    private String name;

    Objects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
