package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer minRange;
    private Integer maxRange;

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {

        minRange = min;
        maxRange = max;

        return this;
    }

    public boolean isValid(Integer number) {

        if (number == null) {
            return !required;
        }

        if (positive) {
            if (number <= 0) {
                return false;
            }
        }

        if (minRange != null && (number < minRange || number > maxRange)) {
            return false;
        }

        return true;
    }
}
