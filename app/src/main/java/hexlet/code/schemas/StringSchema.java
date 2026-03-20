package hexlet.code.schemas;

public class StringSchema extends Schema {

    private boolean required;
    private int minLength;
    private String contains;

    public boolean getRequired() {
        return required;
    }

    public int getMinLength() {
        return minLength;
    }

    public String getContains() {
        return contains;
    }

    @Override
    public StringSchema required() {
        required = !required;
        return this;
    }

    @Override
    public StringSchema minLength(int value) {
        this.minLength = value;
        return this;
    }

    @Override
    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

    public boolean isValid(Object obj) {

        if (obj == null) {
            return !required;
        }

        String str = obj.toString();

        if (required) {
            if (str.isEmpty()) {
                return false;
            }
        }

        if (str.length() < minLength) {
            return false;
        }

        if (contains != null && !str.contains(contains)) {
            return false;
        }

        return true;
    }
}
