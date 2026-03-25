package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    private int minLength;
    private String contains;

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int value) {
        this.minLength = value;
        return this;
    }

    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

    public boolean isValid(String str) {

        if (required) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }

        if (str != null && str.length() < minLength) {
            return false;
        }

        if (contains != null && !str.contains(contains)) {
            return false;
        }

        return true;
    }
}
