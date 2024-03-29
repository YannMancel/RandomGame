package com.mancel.yann.logan.logan.main;

import com.mancel.yann.logan.logan.annotations.Meow;

import java.lang.reflect.Field;

/**
 * Created by Yann MANCEL on 30/08/2019.
 * Name of the project: Logan
 * Name of the package: com.mancel.yann.logan.logan.main
 */
public class Logan {

    // FIELDS --------------------------------------------------------------------------------------


    // CONSTRUCTORS --------------------------------------------------------------------------------

    public Logan() {
    }

    // METHODS -------------------------------------------------------------------------------------

    public static <T> void onSaveInstanceState(T obj) {
        // INTROSPECTION
        Class<?> c = obj.getClass();

        // CLASS
        if (c.isAnnotationPresent(Meow.class)) {
            System.out.println(Meow.class.getSimpleName() + " annotation is present on " + c);
        }

        // FIELDS
        Field[] declaredFields = c.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Meow.class)) {
                System.out.println(Meow.class.getSimpleName() + " annotation is present on " + field.getName());

                // MEOW
                Meow meow = field.getAnnotation(Meow.class);
                System.out.println(" + The message: " + meow.message());

                // Deletes encapsulation
                field.setAccessible(true);

                if (field.getType() == String.class) {
                    String content = null;
                    try {
                        content = (String) field.get(obj);
                        System.out.println(" + " + field.getName() + ": " + content);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        Logan logan = new Logan();
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         