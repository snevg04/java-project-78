package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> value.size() == number);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addCheck("shape", value -> {
            if (value == null) {
                return true;
            }

            for (var key : schemas.keySet()) {

                var actualSchema = schemas.get(key);

                var currentValue = value.get(key);

                if (!actualSchema.isValid(currentValue)) {
                    return false;
                }
            }

            return true;
        });
        return this;
    }
}
