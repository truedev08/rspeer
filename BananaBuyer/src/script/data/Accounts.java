package script.data;

public enum Accounts {
    ACCOUNT_1("Mule", "stevephleming104rock@gmail.com", "rockstar18"),
    ACCOUNT_2("Money Maker", "ultra.smith814@yahoo.com", "8hen2"),
    ACCOUNT_3("Money Maker", "james.wellington6831@gmail.com", "shb72b8"),
    ACCOUNT_4("Money Maker", "galt56h4@hotmail.com", "18hfbu"),
    ACCOUNT_5("Money Maker", "optic.stephen5801@yahoo.com", "mv19m"),
    ACCOUNT_6("", "", ""),
    ACCOUNT_7("", "", ""),
    ACCOUNT_8("", "", ""),
    ACCOUNT_9("", "", ""),
    ACCOUNT_10("", "", ""),
    ACCOUNT_11("", "", ""),
    ACCOUNT_12("", "", ""),
    ACCOUNT_13("", "", ""),
    ACCOUNT_14("", "", ""),
    ;

    private String botType;
    private String username;
    private String password;

    Accounts(String botType, String username, String password) {
        this.botType = botType;
        this.username = username;
        this.password = password;
    }

    public String getBotType() {
        return botType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
