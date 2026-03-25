package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {

    private int sizeOf;

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int number) {
        this.sizeOf = number;
        return this;
    }



    public boolean isValid(Map<String, String> map) {

        if (required) {
            if (map == null) {
                return false;
            }
        }

        if (sizeOf > 0) {
            if (map.size() < sizeOf) {
                return false;
            }
        }

        return true;
    }
}
