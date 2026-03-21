package hexlet.code.schemas;

public abstract class BaseSchema {

    public BaseSchema required() {
        return this;
    }

    public BaseSchema minLength(int value) {
        return this;
    }

    public BaseSchema contains(String str) {
        return this;
    }

    public boolean isValid(Object obj) {
        return false;
    }
}
