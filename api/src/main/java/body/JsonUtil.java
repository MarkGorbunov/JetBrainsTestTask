package body;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtil {
    @SneakyThrows
    public static String convertObjectToJson(Object object) {
        ObjectMapper Obj = new ObjectMapper();
        return Obj.writeValueAsString(object);
    }
}
