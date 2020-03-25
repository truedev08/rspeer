package script.data;

public enum Objects {
    CURTAIN("Curtain"),
    ;

    private String name;

    Objects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
