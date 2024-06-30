package gmail.davidsousalves.converter;

import jakarta.persistence.Tuple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransformerTupleToDTO <T>{

    public static <T> List<T> convertTupleToList(Class<T> clazz, List<Tuple> tuple) {
        return tuple.stream()
                .map(e -> convertTupleToObject(clazz, e))
                .map(e -> (T) e)
                .collect(Collectors.toList());
    }

    public static <T> T convertTupleToObject(Class<T> clazz, Tuple tuple) {
        try {
            Method[] methods = clazz.getMethods();
            Class<?> dtoClassName = Class.forName(clazz.getName(), true, clazz.getClassLoader());
            Constructor<T> dtoConstructor = clazz.getConstructor();
            Object dtoObject = dtoConstructor.newInstance();

            List<String> propNames = Arrays.stream(methods)
                    .filter(method -> (method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().startsWith("getClass"))
                    .map(method -> {
                        String methodName = method.getName().substring(0, 2).equalsIgnoreCase("is") ? method.getName().substring(2) : method.getName();
                        methodName = methodName.replace("get", "");
                        String s = methodName.substring(0, 1).toLowerCase();
                        StringBuilder sb = new StringBuilder();
                        sb.append(s);
                        sb.append(methodName.substring(1));

                        return sb.toString();
                    }).collect(Collectors.toList());

            propNames.forEach(prop -> {
                try {
                    Field field = dtoClassName.getDeclaredField(prop);
                    field.setAccessible(true);
                    if (Long.class.equals(field.getType()) && tuple.get(prop) != null) {
                        field.set(dtoObject, Long.valueOf(tuple.get(prop).toString()));
                    } else {
                        field.set(dtoObject, tuple.get(prop));
                    }
                } catch (IllegalArgumentException ex) {
                    if (!ex.getMessage().contains("Unknown alias")) {
//                        throw new RuntimeException(ex);
                    }
                    System.out.println(ex.getMessage());
                } catch (IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                    System.out.println(ex.getMessage());
                }

            });

            T object = (T) dtoObject;
            return object;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
