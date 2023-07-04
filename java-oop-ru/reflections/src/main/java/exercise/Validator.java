package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static List<String> validate(Object object) {
        List<String> notValidateFields = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                Object fieldData = getFieldData(field, object);
                if (!validateNotNull(fieldData)) {
                    notValidateFields.add(field.getName());
                }
            }
        }
        return notValidateFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> notValidateFields = new HashMap<>();

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object fieldData = getFieldData(field, object);

            if (field.isAnnotationPresent(NotNull.class)) {
                if (!validateNotNull(fieldData)) {
                    notValidateFields.put(field.getName(), List.of("can not be null"));
                    continue;
                }
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                Integer minLength = field.getAnnotation(MinLength.class).minLength();
                if (!validateMinLength(fieldData, minLength)) {
                    notValidateFields.put(field.getName(), List.of(String.format("length less than %s", minLength)));
                }
            }
        }

        return notValidateFields;
    }

    private static Object getFieldData(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean validateNotNull(Object fieldData) {
        return fieldData != null;
    }

    private static Boolean validateMinLength(Object fieldData, Integer minLength) {
        return fieldData.toString().length() >= minLength;
    }
}
// END
