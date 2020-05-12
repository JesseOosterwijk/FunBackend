package Api.Helpers.Endpoints;

public class Endpoints {
    private Endpoints() {}

    public static final String AUTHSERVER = "http://localhost:8079";
    public static final String LOGIN = AUTHSERVER + "/auth/auth";
    public static final String REGISTER = AUTHSERVER + "/auth/register";

}
