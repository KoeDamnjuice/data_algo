
//测试代码
public class Main {

    public static void main(String[] args)
    {
        int[] ints = new int[]{1,2};
        Circal a = new Circal(5,ints);
        a.enqueue(3);
        a.enqueue(4);
        int x = a.dequeue();
        a.enqueue(520);
        int y = a.dequeue();
        a.enqueue(630);
    }
}

class Circal
{
    private int[] content;
    private int QueueSize;

    private int front = 0;

    private int rear = 0;

    public Circal(int size,int[] content) throws RuntimeException{
        if(size < content.length)
        {
            throw new RuntimeException("size不当小于content的长度");
        }
        this.content = new int[size];
        this.QueueSize = size;
        for(int i = 0;i<content.length;i++)
        {
            this.content[i] = content[i];
            this.rear++;   //尾指针后移,由于是新建，不需要考虑循环的问题
        }
    }

    public Circal(int size)
    {
        this.content = new int[size];
        this.QueueSize = size;
        //此时front和rear都是0，指向一致，代表队列是空
    }

    //这里的synchronized是什么意思
    public synchronized void enqueue(Object obj) throws RuntimeException{
        //注意队列是尾进头出。
        //要插入队列，首先要判断队列是否为满
        //将新的数据插入到rear指针指向的位置
        //rear指针+1并循环
        if(((rear+1)%QueueSize)==front)
        {
            throw new RuntimeException("队列已满");
        }

        content[rear] = (int) obj;
        rear = (rear+1)%QueueSize;
    }


    public int dequeue() throws RuntimeException {
        //出队列，先判断队列是否为空
        //如果不为空，则从队首抽出数据
        //front+1
        if(this.isEmpty())
        {
            throw new RuntimeException("队列为空");
        }
        int result = content[front];
        content[front] = 0;
        front = (front+1)%QueueSize;
        return result;
    }



    public synchronized boolean isEmpty() {
        return front == rear;   //在循环队列中，如果首尾指针指向一直，则代表为空
    }

    public int lengthOfQueue()
    {
        return (rear-front+QueueSize)%QueueSize;
    }
}