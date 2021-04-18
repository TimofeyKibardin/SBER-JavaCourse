package Homework.Exercise9_Reflection;

import java.lang.reflect.*;

public class BreakTheRules {
    public static void main(String[] args) {

        //Работа с классом
        try {
            Class<?> lis = Class.forName("Homework.Exercise9_Reflection.LazyInitializedSingleton");
            System.out.println(lis.getName());

            System.out.println("--------------------------------");

            //Модификатор доступа класса
            int mods = lis.getModifiers();
            if (Modifier.isPublic(mods)) System.out.println("Public");
            if (Modifier.isAbstract(mods)) System.out.println("Abstract");
            if (Modifier.isFinal(mods)) System.out.println("Final");


            //Конструктор и его параметры
            Constructor[] constructors = lis.getConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                System.out.println(constructor.getName());
                Class<?>[] params = constructor.getParameterTypes();
                for (Class<?> param : params) {
                    System.out.println(param.getName());
                }
            }

            System.out.println("--------------------------------");

            //Поля
            Field[] fields = lis.getDeclaredFields();
            for (Field field : fields) {
                Class<?> fld = field.getType();
                field.setAccessible(true);
                System.out.println("Class name: " + field.getName());
                System.out.println("Class type: " + fld.getName());
            }

            System.out.println("--------------------------------");

            //Методы класса
            Method[] methods = lis.getMethods();
            for (Method method : methods) {
                System.out.println("Method name: " + method.getName());
                System.out.println("Method type: " + method.getReturnType().getName());
            }

            System.out.println("--------------------------------");

            //Изменяем значение name
            Field f = lis.getClass().getDeclaredField("name");
            f.setAccessible(true);
            f.set(lis, "Сломал инкапсуляцию");
            System.out.println(lis);

            //Метод
            toString(lis);

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void toString(Object lis) {
        try {
            Method method = lis.getClass().getDeclaredMethod("toString");
            method.setAccessible(true);
            method.invoke(lis);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
