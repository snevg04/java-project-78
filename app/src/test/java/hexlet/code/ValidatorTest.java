package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ValidatorTest {

    private static Validator v;

    @BeforeEach
    public void makeValidatorString() {
        v = new Validator();
    }

    @Test
    public void noParamNullTest() {

        StringSchema schema = v.string();
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void noParamEmptyTest() {

        StringSchema schema = v.string();
        boolean actual = schema.isValid("");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredNullTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredEmptyTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().isValid("");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minLengthWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(7).isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minLengthRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(4).isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsMinLengthRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsMinLengthWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsNullTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(8).contains("What").isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberNullTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberPositiveRightTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.positive().isValid(5);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberPositiveWrongTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.positive().isValid(-5);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberPositiveZeroTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.positive().isValid(0);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberRangeRightTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.range(5, 10).isValid(7);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberRangeWrongTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.range(5, 10).isValid(15);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void numberRequiredPositiveRangeRightTest() {

        NumberSchema schema = v.number();
        boolean actual = schema.required().positive().range(5, 10).isValid(-5);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

}
