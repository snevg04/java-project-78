package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import hexlet.code.schemas.Schema;

public class ValidatorTest {

    private static Schema schema;

    @BeforeEach
    public void makeValidatorString() {
        schema = new Validator().string();
    }

    @Test
    public void noParamNullTest() {

        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void noParamEmptyTest() {

        boolean actual = schema.isValid("");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredNullTest() {

        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredEmptyTest() {

        boolean actual = schema.required().isValid("");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minLengthWrongTest() {

        boolean actual = schema.minLength(7).isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void minLengthRightTest() {

        boolean actual = schema.minLength(4).isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsRightTest() {

        boolean actual = schema.contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsWrongTest() {

        boolean actual = schema.contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsMinLengthRightTest() {

        boolean actual = schema.minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void containsMinLengthWrongTest() {

        boolean actual = schema.minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsRightTest() {

        boolean actual = schema.required().minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsWrongTest() {

        boolean actual = schema.required().minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void requiredMinLengthContainsNullTest() {

        boolean actual = schema.required().minLength(8).contains("What").isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

}
