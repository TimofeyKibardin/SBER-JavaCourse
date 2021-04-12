package Homework.Exercise6;

import java.util.*;

//Decrease comparator
class DecreaseComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}

//Increase Comparator
class IncreaseComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

class MyArrayList <T> {
    private final int INIT_SIZE = 10;
    private final int CUT_RATE = 2;
    private Object[] obj = new Object[INIT_SIZE];
    private int pointer; //размер массива, получение через size()


    /*Вспомогательный метод для масштабирования*/
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(obj, 0, newArray, 0, pointer);
        obj = newArray;
    }

    /*Добавить элемент*/
    void add(T value) {
        if (pointer == obj.length - 1) {
            resize(obj.length * 2);
        }
        obj[pointer++] = value;
    }

    /*Добавить элемент по индексу*/
    void add(int index, T value) {
        if (index < 0) return;
        if (pointer == obj.length - 1) {
            resize(obj.length * 2);
        }

        if (index > pointer) {
            index = pointer;
        }

        for (int i = pointer; i >= index; i--) {
            obj[i + 1] = obj[i];
        }
        obj[index] = value;
        pointer++;
    }

    /*Добавить всю коллекцию в конец массива*/
    boolean addAll(Collection<T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        for (T value : c) {
            add(value);
        }
        return true;
    }
    /*Добавить всю коллекцию в массив начиная с индекса*/
    boolean addAll(int index, Collection<T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty() || index < 0) {
            return false;
        }
        if (index > pointer) {
            index = pointer;
        }
        for (T value : c) {
            add(index++, value);
        }
        return true;
    }

    /*Размер массива*/
    int size() {
        return pointer;
    }

    /*Получить элемент по индексу*/
    T get(int index) {
        return (T) obj[index];
    }

    /*Установить элемент по индексу*/
    void set(int index, T value) {
        obj[index] = value;
    }

    /*Удалить элемент по индексу*/
    void delete(int index) {
        for (int i = index; i < pointer; i++) {
            obj[i] = obj[i + 1];
        }
        obj[pointer] = null;
        pointer--;
        if (obj.length > INIT_SIZE && pointer < obj.length / CUT_RATE) {
            resize(obj.length / 2);
        }
    }

    /*Найти элемент в массиве по индексу*/
    int indexOf(T value) {
        int result = -1;
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].equals(value)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /*Получение индекса последнего вхождения элемента*/
    int lastIndexOf(T value) {
        int lastIndex = -1;
        if (value == null) {
            for (int i = 0; i < pointer; i++) {
                if (obj[i] == null) {
                    lastIndex = i;
                }
            }
            return lastIndex;
        } else {
            for (int i = 0; i < pointer; i++) {
                if (value.equals(obj[i])) {
                    lastIndex = i;
                }
            }
            return lastIndex;
        }
    }

    /*Преобразовать коллекцию в массив*/
    Object[] toArray() {
        Object[] newArray = new Object[obj.length];
        System.arraycopy(obj, 0, newArray, 0, size());
        return newArray;
    }

    /*Очистить массив*/
    void clear() {
        for (int i = 0; i < pointer; i++) {
            obj[i] = null;
        }
        pointer = 0;
    }

    /*Проверить равенство двух массивов по хэшкоду*/
    boolean equals(Collection<T> ob2) {
        if (ob2 == null) return false;
        if (Arrays.hashCode(obj) == ob2.hashCode()) {
            return true;
        } else return false;
    }

    /*Создать саблист*/
    MyArrayList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            int temp = fromIndex;
            fromIndex = toIndex;
            toIndex = temp;
        }

        if ((fromIndex < 0) || (toIndex > pointer)) {
            return null;
        }

        MyArrayList<T> newSubList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            newSubList.add((T) obj[i]);
        }
        return newSubList;
    }

    /*Итератор*/
    Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < pointer && obj[currentIndex] != null;
            }

            @Override
            public T next() throws IndexOutOfBoundsException {
                return (T) obj[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /*Разделение*/
    private int partition(T[] obj, int start, int end, Comparator comparator) {
        int marker = start;
        for (int i = start; i <= end; i++) {
            if (comparator.compare(obj[i], obj[end]) <= 0) {
                T temp = obj[marker]; //swap
                obj[marker] = obj[i];
                obj[i] = temp;
                marker++;
            }
        }
        return marker - 1;
    }

    /*Быстрая сортировка*/
    private void quicksort(T[] obj, int start, int end, Comparator comparator) {
        if (start >= end) return;

        int pivot = partition(obj, start, end, comparator);
        quicksort(obj, start, pivot - 1, comparator);
        quicksort(obj, pivot + 1, end, comparator);
    }

    /*Сортировка*/
    void sort(Comparator comparator) {
        quicksort((T[]) obj, 0, pointer - 1, comparator);
    }
}
