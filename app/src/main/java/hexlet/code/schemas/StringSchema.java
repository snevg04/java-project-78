package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addCheck("contains", value -> str != null && value.contains(str));
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (required) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        }

        for (var check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }

}
