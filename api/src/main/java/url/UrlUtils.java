package url;

import com.config.Config;

public class UrlUtils {
    public static final String BASE_URL = Config.getEnv().getProperty("baseUrl") + "/app/rest";
    public static final String USERS_URL = BASE_URL + "/users";


}
