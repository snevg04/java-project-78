package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    private int sizeof;
    private Map<String, BaseSchema<String>> schemas;

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int number) {
        this.sizeof = number;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> rule) {
        this.schemas = rule;
        return this;
    }

    public boolean isValid(Map<String, String> map) {

        if (map == null) {
            return !required;
        }


        if (sizeof > 0) {
            if (map.size() < sizeof) {
                return false;
            }
        }

        if (schemas != null) {

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
        }

        return true;
    }
}
