package step6;

import java.util.Arrays;

public class AA<E> {

    private Object[] data ;


    private int size;

    public AA() {

        data = new Object[10];
    }


    public void add(E e){
        addFirst(e);
    }

    public void addFirst(E e){
        expandIfnecessary(size+1);
        System.arraycopy(data,0,data,1,size+1);
        data[0]=e;
        size++;
    }

    public void addLast(E e){
        expandIfnecessary(size+1);
        data[size++]=e;
    }

    public void debug(){
        for (int i = 0; i < data.length; i++) {

            System.out.print("i = " + i);
            System.out.println(", data = " + data[i]);
        }
    }

    private void expandIfnecessary(int newSize) {

        if(newSize<=data.length)return;

        int newDataSize=(size<<1)>=newSize?(size<<1):newSize;

        data = Arrays.copyOf(data, newDataSize);

    }


    public static void main(String[] args) {
        AA<Integer> t = new AA<>();

        t.addFirst(10);
        t.addFirst(23);
        t.addLast(50);
        t.debug();

        for (int i = 0; i < 8; i++) {
            t.addLast(i);
        }
        t.debug();
    }

}
