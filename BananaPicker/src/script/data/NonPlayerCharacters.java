package script.data;

public enum NonPlayerCharacters {

    SEAMAN_LORRIS("Seaman Lorris"),
    SEAMAN_TOBIAS("Seaman Tobias"),
    SEAMAN_THRESNOR("Seaman Thresnor"),
    CUSTOMS_OFFICER("Customs officer"),


    ;

    private String name;

    NonPlayerCharacters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
