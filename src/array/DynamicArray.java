package array;

/**
 * GET/SET O(1)
 * 
 * INSERT O(n)
 * 
 * DELETE O(n)
 */

public class DynamicArray {

    private Object[] data;
    private int size;
    private int capacity;

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public String get(int index) {
        return (String) data[index];
    }

    public void set(int index, String value) {
        data[index] = value;
    }

    public void insert(int index, String value) {
        // Check and increase capacity
        if (size == capacity) {
            resize();
        }
        // Copy from left to right
        for (int j = size; j > index; j--) {
            data[j] = data[j - 1];
        }

        // Insert
        data[index] = value;
        size++;
    }

    public void delete(int index) {
        // Copy from right to left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(String value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        // Create a new array with double capacity
        // and copy elements to new array
        capacity = capacity * 2;
        Object[] newData = new Object[capacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println("capacity = " + capacity + "\tsize = " + size);
    }

    public void add(String value) {
        if (size == capacity) {
            resize();
        }
        data[size] = value;
        size++;
    }

    public static void main(String[] args) {
        DynamicArray arr = new DynamicArray(3);
        arr.add("a");
        arr.add("c");
        arr.add("e");
        arr.print();
        arr.insert(1, "b");
        System.out.println(arr.contains("b"));
        arr.set(3, "d");
        arr.print();
        System.out.println(arr.contains("e"));
        arr.delete(0);
        arr.print();
        arr.set(0, "a");
        arr.print();
        System.out.println(arr.get(0));
        System.out.println(arr.isEmpty());
    }
}
