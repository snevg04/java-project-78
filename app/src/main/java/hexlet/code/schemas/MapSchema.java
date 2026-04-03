package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    private Map<String, BaseSchema<String>> schemas;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> value.size() == number);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> rule) {
        this.schemas = rule;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> map) {

        if (!super.isValid(map)) {

            return false;
        }

        if (schemas == null || map == null) {
            return true;
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
