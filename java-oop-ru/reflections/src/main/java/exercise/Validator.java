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
                if (fieldData == null) {
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
            if (field.getAnnotations().length == 0) {
                continue;
            }

            Object fieldData = getFieldData(field, object);

            if (fieldData != null) {
                if (field.isAnnotationPresent(MinLength.class)) {
                    int minLength = field.getAnnotation(MinLength.class).minLength();

                    if (fieldData.toString().length() < minLength) {
                        notValidateFields.put(
                                field.getName(),
                                List.of(String.format("length less than %s", minLength))
                        );
                    }
                }
            } else {
                if (field.isAnnotationPresent(NotNull.class)) {
                    notValidateFields.put(field.getName(), List.of("can not be null"));
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
}
// END
