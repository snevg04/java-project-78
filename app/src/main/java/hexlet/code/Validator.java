package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public BaseSchema string() {
        return new StringSchema();
    }
}
