public class Main {
    public static void main(String[] args) {
        try {
            int[] myI = new int[]{1, 44, 212, 32, 4, 97, 23, 14};
            MyLinearTable lt = new MyLinearTable(myI, 10);
            int get_one = lt.GetItem(3);
            lt.Insert(3, 5);

            int[] myII = new int[]{1, 44, 212, 32, 4, 97, 23, 14, 5567};
            MyLinearTable ltt = new MyLinearTable(myII, 10);
            ltt.Delete(3);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

class MyLinearTable {
    private int MAXRANGE;
    private int[] items;
    private int length_now;

    public MyLinearTable(int[] inputs, int maxrange) {
        MAXRANGE = maxrange;
        items = new int[MAXRANGE];
        length_now = inputs.length;
        if(length_now > MAXRANGE) {
            throw new RuntimeException("输入线性表长度超过数组长度");
        }
        for(int n = 0; n < inputs.length; n++) {
            items[n] = inputs[n];
        }
    }

    public int GetItem(int i) throws RuntimeException {
        if(i < 1 || i > this.length_now) {
            throw new RuntimeException("索引超过线性表的长度");
        }

        return items[i - 1];
    }

    public void Insert(int i, int item) throws RuntimeException {
        int k;
        if(this.length_now == MAXRANGE) {
            throw new RuntimeException("线性表已满");
        }
        if(i < 1 || i > this.length_now) {
            throw new RuntimeException("插入位置超出范围");
        }
        if(i < this.length_now) //插入位置不位于线性表末尾
        {
            for(k = this.length_now - 1; k > i - 1; k--) {
                items[k + 1] = items[k];
            }
        }
        items[i - 1] = item;
        this.length_now++;
    }

    public int Delete(int i) throws RuntimeException {
        int k;
        int result;
        if(this.length_now == 0) {
            throw new RuntimeException("线性表为空");
        }

        if(i < 1 || i > this.length_now) {
            throw new RuntimeException("删除位置超出索引");
        }
        result = this.items[i - 1];
        if(i < this.length_now) {
            for(k = i; k < length_now; k++) {
                this.items[k - 1] = this.items[k];
            }
        }
        this.length_now--;
        for(i=this.length_now;i<MAXRANGE;i++)
        {
            this.items[i]=0;
        }
        return result;
    }
}