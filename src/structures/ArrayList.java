package structures;

import java.util.Arrays;

public class ArrayList<T>{
    private int size;
    private int max_size;
    private T[] arr;

    @SuppressWarnings ("unchecked")
    public ArrayList() {
        size = 0;
        max_size = 10;
        arr = (T[]) (new Object[max_size]);
    }
    @SuppressWarnings ("unchecked")
    public ArrayList(int max_size){
        size = 0;
        this.max_size = max_size;
        arr = (T[]) (new Object[max_size]);
    }

    public void add(T newElement){
        if (size != max_size){
            arr[size++] = newElement;
        }else {
            max_size *= 1.5;
            arr = Arrays.copyOf(arr, max_size);
            arr[size++] = newElement;
        }
    }

    public void add(int index, T newElement){
        if (index < size){
            System.arraycopy(arr, index, arr, index + 1, size - index);
            arr[index] = newElement;
            size++;
        }else {
            add(newElement);
        }
    }

    public void remove(int index){
        if (index > -1 && index < size){
            System.arraycopy(arr,index + 1, arr, index, --size - index);
        }
    }

    public T get(int index){
        T element = null;
        if (index < size){
            element = arr[index];
        }
        return element;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", max_size=" + max_size +
                ", arr=" + (Arrays.toString(Arrays.copyOf(arr, size))) +
                '}';
    }
}
