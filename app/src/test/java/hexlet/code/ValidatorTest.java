package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.ShapeSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {

    private static Validator v;

    @BeforeEach
    public void makeValidatorString() {
        v = new Validator();
    }

    @Test
    public void stringNoParamNullTest() {

        StringSchema schema = v.string();
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringNoParamEmptyTest() {

        StringSchema schema = v.string();
        boolean actual = schema.isValid("");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringRequiredNullTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringRequiredEmptyTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().isValid("");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringMinLengthWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(7).isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringMinLengthRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(4).isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringContainsRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringContainsWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringContainsMinLengthRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringContainsMinLengthWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringRequiredMinLengthContainsRightTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(4).contains("Hel").isValid("Hello");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringRequiredMinLengthContainsWrongTest() {

        StringSchema schema = v.string();
        boolean actual = schema.required().minLength(8).contains("What").isValid("Hello");
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void stringRequiredMinLengthContainsNullTest() {

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

    @Test
    public void mapNullTest() {

        MapSchema schema = v.map();
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapRequiredRightTest() {

        MapSchema schema = v.map();
        boolean actual = schema.required().isValid(new HashMap<String, String>());
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapRequiredWrongTest() {

        MapSchema schema = v.map();
        boolean actual = schema.required().isValid(null);
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapSizeOfRightTest() {

        int size = 2;
        MapSchema schema = v.map();
        boolean actual = schema.sizeOf(size).isValid(Map.of(
                "key1", "value1",
                "key2", "value2"
        ));

        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapSizeOfWrongTest() {

        int size = 2;
        MapSchema schema = v.map();
        boolean actual = schema.sizeOf(size).isValid(
                Map.of("key1", "value1")
        );

        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapRequiredSizeOfRightTest() {

        MapSchema schema = v.map();
        boolean actual = schema.required().sizeOf(2).isValid(Map.of(
                "key1", "value1",
                "key2", "value2"
        ));
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mapRequiredSizeOfWrongTest() {

        MapSchema schema = v.map();
        boolean actual = schema.required().sizeOf(2).isValid(
                Map.of("key1", "value1")
        );
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shapeNullTest() {

        ShapeSchema schema = v.shape();
        schema.put("firstName", v.string());
        schema.put("secondName", v.string());
        boolean actual = schema.isValid(null);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shapeNoSchemaTest() {

        ShapeSchema schema = v.shape();
        boolean actual = schema.put("firstName", null).isValid(
                Map.of("firstName", "John",
                        "secondName", "Smith")
        );
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shapeRequiredMinLengthRightTest() {

        ShapeSchema schema = v.shape();
        schema.put("firstName", v.string().required().minLength(4));
        schema.put("secondName", v.string().required().minLength(5));
        boolean actual = schema.isValid(Map.of(
                "firstName", "John",
                "secondName", "Smith")
        );
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shapeRequiredMinLengthOneRightOneWrongTest() {

        ShapeSchema schema = v.shape();
        schema.put("firstName", v.string().required().minLength(5));
        schema.put("secondName", v.string().required().minLength(5));
        boolean actual = schema.isValid(Map.of(
                "firstName", "John",
                "secondName", "Smith")
        );
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shapeRequiredMinLengthWrongTest() {

        ShapeSchema schema = v.shape();
        schema.put("firstName", v.string().required().minLength(5));
        schema.put("secondName", v.string().required().minLength(6));
        boolean actual = schema.isValid(Map.of(
                "firstName", "John",
                "secondName", "Smith")
        );
        boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

}
