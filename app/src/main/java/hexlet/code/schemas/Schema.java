package hexlet.code.schemas;

public abstract class Schema {

    public Schema required() {
        return this;
    }

    public Schema minLength(int value) {
        return this;
    }

    public Schema contains(String str) {
        return this;
    }

    public boolean isValid(Object obj) {
        return false;
    }
}
