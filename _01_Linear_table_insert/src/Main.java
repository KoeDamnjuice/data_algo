import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{3,9,2,42,13,97};
        MyArray myArray = new MyArray(array,9);
        for(Integer i:myArray.getItems())
        {
            System.out.println(i);
        }
        int see = myArray.GetItem(3);
        System.out.println(see);
        myArray.Insert(2,50);
        for(Integer i:myArray.getItems())
        {
            System.out.println(i);
        }
    }
}

class MyArray  //线性表类
{
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    private int size;

    public int[] getItems() {
        return items;
    }

    private int[] items;

    private int capacity;

    public MyArray(int[] inputs, int capacity) {
        this.capacity = capacity;
        items = new int[inputs.length];
        if(inputs.length > capacity) {
            throw new RuntimeException("线性表长度不得大于数组容量");
        }
        for(int i = 0; i < inputs.length; i++) {
            this.items[i] = inputs[i];
        }
        this.size = inputs.length;
    }

    public int GetItem(int i) {
        if(i < 1 || i > items.length) {
            throw new RuntimeException("超过线性表的索引");
        }
        return items[i - 1];
    }

    public void Insert(int i, int item) {
        int k;//中间数据
        List<Integer> list = Arrays.stream(items).boxed().collect(Collectors.toList());
        if(items.length == capacity) {
            throw new RuntimeException("线性表已经满了");
        }
        if(i < 1 || i > items.length + 1)  //items.length+1代表最后一位
        {
            throw new RuntimeException("插入位置超出线性表范围");
        }

        list.add(i-1,item);
        items = list.stream().mapToInt(Integer::valueOf).toArray();
    }
}