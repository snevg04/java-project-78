package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    protected boolean required;

    public abstract boolean isValid(T t);
}
