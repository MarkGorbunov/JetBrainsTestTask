package url;

import com.config.Config;

public class UrlUtils {
    public static final String BASE_URL = Config.getEnv().getProperty("baseUrl");
    public static final String BASE_API_URL = BASE_URL + "/app/rest";
    public static final String USERS_URL = BASE_API_URL + "/users";
    public static final String ADMIN_AUTH_URL = BASE_URL + "/admin/authAction.html?action=save";
}
