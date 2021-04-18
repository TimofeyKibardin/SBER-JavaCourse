package Homework.Exercise9_Reflection;

public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;
    private String name;

    private LazyInitializedSingleton(){}

    public static LazyInitializedSingleton getInstance(){ // #3
        if(instance == null){		//если объект еще не создан
            System.out.println("new instance");
            instance = new LazyInitializedSingleton();	//создать новый объект
        }
        return instance;	// вернуть ранее созданный объект
    }

    @Override
    public String toString() {
        return "LazyInitializedSingleton{" +
                "name='" + name + '\'' +
                '}';
    }

    private void getMessage() {
        System.out.println("Любитель рефлексии получил доступ к методу");
    }
}
