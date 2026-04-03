package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value >= min && value <= max);
        return this;
    }

//    public final boolean isValid(Integer number) {
//
//        if (number == null) {
//            return !required;
//        }
//
//        if (positive) {
//            if (number <= 0) {
//                return false;
//            }
//        }
//
//        if (minRange != null && (number < minRange || number > maxRange)) {
//            return false;
//        }
//
//        return true;
//    }
}
