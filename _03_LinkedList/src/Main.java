import java.util.LinkedList;

//实现链式结构线性表
public class Main {
    public static void main(String[] args) {
        try{
            int[] start = new int[]{1, 5, 3};
            LinkedTable lt = new LinkedTable(start);
            int x = lt.getNode(1);
            lt.addNode(77,2);//在第二个节点后新增一个节点
            lt.deleteNode(3);
            lt.deleteLinkedTable();
        }catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }
}

class LinkedTable   //
{
    private Node head = new Node(1); //定义头节点

    class Node {
        private int data;

        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public LinkedTable(int[] init_array) {
        head.next = new Node(init_array[0]);   //头节点的data一般不做设置
        Node next_node = head.next;
        for(int i = 1; i < init_array.length; i++) {
            if(i == init_array.length) {
                next_node.next = null;
            } else {
                next_node.next = new Node(init_array[i]);
                next_node = next_node.next;
            }
        }
    }



    public int getNode(int i) throws RuntimeException
    {
        Node node = this.head;
        for(int j = 0;j<i;j++)   //链表读取的时间复杂度为O(n)
        {
            node = node.next;
            if(node == null && j<i)
            {
                throw new RuntimeException("索引超出限制");
            }
        }
        return node.data;
    }

    //节点增加,在第i个节点后新增一个节点
    public void addNode(int data,int i) throws RuntimeException
    {
        Node addOne = new Node(data);
        Node node = this.head;
        for(int j = 0;j<i;j++)  //链表插入一个或多个数据的时间的复杂度都为O(n)
        {
            if(node != null && node.next == null && j==i-1) //当插入到链表的最后一位
            {
                node.next = addOne;
                addOne.next = null;
            }

            if(node == null && j<i)
            {
                throw new RuntimeException("索引超出限制");
            }
            node = node.next;
        }
        Node node_third = node.next;
        node.next = addOne;
        addOne.next = node_third;
    }

    //删除第i个节点
    public void deleteNode(int i)
    {
        Node node = this.head;
        for(int j=0;j<i-1;j++)
        {

                node = node.next;
            if(node.next == null)
            {
                throw new RuntimeException("超出链表范围");
            }
        }

        Node next_node = node.next;
        node.next = next_node.next;
        next_node = null;   //清空这个节点
    }
}