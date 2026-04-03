package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new HashMap<>();
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    public boolean isValid(T value) {

        if (required) {
            if (value == null) {
                return false;
            }
        }

        var keys = checks.keySet();

        for (var key : keys) {
            if (!checks.get(key).test(value)) {
                return false;
            }
        }

        return true;
    }
}
