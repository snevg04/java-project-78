package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class ShapeSchema extends BaseSchema<Map<String, String>> {

    private Map<String, BaseSchema<String>> schemas = new HashMap<>();

    public ShapeSchema put(String str1, BaseSchema<String> schema) {

        schemas.put(str1, schema);
        return this;
    }

    public boolean isValid(Map<String, String> map) {

        if (map == null) {
            return !required;
        }

        for (var key : schemas.keySet()) {

            var actualSchema = schemas.get(key);

            if (actualSchema == null) {
                continue;
            }

            var currentValue = map.get(key);

            if (!actualSchema.isValid(currentValue)) {
                return false;
            }
        }

        return true;
    }
}
