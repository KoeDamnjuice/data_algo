import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        MyStaticLinkedTable m = new MyStaticLinkedTable();
        int[] a = new int[]{1,2,3,4};
        MyStaticLinkedTable n = new MyStaticLinkedTable(a);
        n.addElement(520,3);  //期待结果{1,2,520,3,4}
        n.deleteElement(1);  //期待结果{2,520,3,4}
    }
}

class MyStaticLinkedTable
{
    List<Node> table = new ArrayList<>();  //静态链表
    int MAXLENGTH = 50;

    public MyStaticLinkedTable()  //创建空的静态链表
    {
        Random random = new Random();
        table.add(0,new Node(1,random.nextInt()));
        for(int i=0;i<MAXLENGTH-2;i++)  //除去首尾两端的内容
        {
            table.add(i+1,new Node(i+2,random.nextInt()));  //内容暂先设置为随机数
        }
        table.add(MAXLENGTH-1,new Node(0,random.nextInt()));
    }

    public MyStaticLinkedTable(int[] init_int)
    {
        Random random = new Random();
        table.add(0,new Node(init_int.length+1,random.nextInt()));

        for(int i=0;i<init_int.length-1;i++)
        {
            table.add(i+1,new Node(i+2,init_int[i]));
        }

        table.add(init_int.length,new Node(0,init_int[init_int.length-1]));  //静态数组最后的元素cursor指向0下标。

        for(int i=init_int.length;i<MAXLENGTH-2;i++)  //超出数组范围的内容全部设置为随机数
        {
            table.add(i+1,new Node(i+2,random.nextInt()));  //内容暂先设置为随机数
        }

        table.add(MAXLENGTH-1,new Node(1,random.nextInt()));
    }

    //在第i个元素之前插入新的节点
    //注意，在静态链表中，尽管想把元素插在第i个元素之前，但是物理存储上，是插在备用链表的第一个下标处，也就是物理表头的cursor处。
    //只是说，在链表的cursor上，这个元素插在了第order个节点之前
    public void addElement(int data,int order) throws RuntimeException
    {
        //先确定order是否在静态数组的范围之内，否则抛出错误
        if(order<1 || order > MAXLENGTH - 2)
        {
            throw new RuntimeException("插入位置超出数组范围");
        }

        //获取备用链表的首个下标
        int first_order = this.getStandbyFirst();

        Node start = this.table.get(MAXLENGTH-1);
        //从表尾开始遍历静态链表，寻找order之前的元素
        for(int i = 0;i<order-1;i++)
        {
            start = table.get(start.cursor);
        }
        start.setCursor(first_order);
        table.get(start.cursor).setCursor(order);
        table.get(start.cursor).setData(data);
    }

    //获取备用数组的首个下标,并将备用链表的第二个元素作为备用链表的第一个元素
    private int getStandbyFirst()
    {
        Node first = this.table.get(table.get(0).cursor);  //获取第一个备用节点
        int order = table.indexOf(first);  //返回第一个备用节点的下标
        first = this.table.get(first.cursor); //将第一个备用节点cursor指向的节点作为新的第一个备用节点
        table.get(0).setCursor(table.indexOf(first)); //让表头指向新的第一备用节点
        return order;
    }

    //删除表中第i个元素
    //首先从表尾开始遍历，寻找静态链表中的第i个元素
    //让表头的cursor指向该元素的下标，让该元素的cursor指向原备用链表的第一个元素之下标
    //让表尾的cursor指向该元素的cursor，结束
    public void deleteElement(int order)
    {
        Node start = table.get(MAXLENGTH-1);
        for(int i=0;i<order;i++)
        {
            start = table.get(start.cursor);
        }
        Node head = table.get(0);
        Node tail = table.get(MAXLENGTH-1);
        tail.setCursor(start.cursor);
        start.setCursor(head.cursor);
        head.setCursor(table.indexOf(start));
    }



    class Node
    {
        public void setCursor(int cursor) {
            this.cursor = cursor;
        }

        int cursor;

        public void setData(int data) {
            this.data = data;
        }

        int data;

        public Node(int cursor,int data)
        {
            this.cursor = cursor;
            this.data = data;
        }
    }
}