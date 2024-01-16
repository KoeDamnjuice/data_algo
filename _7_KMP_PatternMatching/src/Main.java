public class Main {
    public static void main(String[] args) {
        String a = "abcfabccabx";
        String b = "ab";
        KMP_patternMatching kmp = new KMP_patternMatching();
        int[] test = kmp.StringToCharCollection(a);
        int[] test_2 = kmp.StringToCharCollection(b);
        int cx = kmp.patternMatching(test,test_2,2);
        System.out.println(cx);
    }
}

class KMP_patternMatching
{
    //这里的循环方法非常巧妙
    private int[] get_next(int[] T)
    {
        int[] next = new int[255];
        next[1] = 0;  //从1开始才是数据,next[0]没有任何意义
        int i=1; //指向尾缀字符  aabcaa
        int j=0; //指向前缀字符
        while (i<T[0])  //尾缀指针只要不到底，就继续循环
        {
            if(j==0 || T[i] == T[j])
            {
                i++;
                j++;
                next[i] = j;
            }else
            {
                j = next[j];    //让j退回到前缀的前缀，因为前缀的前缀等于尾缀的尾缀
            }
        }
        return next;
    }

    //改进版本的KMP算法
    private int[] get_next_val(int[] T)
    {
        int[] next_val = new int[255];
        next_val[1] = 0;  //从1开始才是数据,next[0]没有任何意义
        int i=1; //指向尾缀字符  aabcaa
        int j=0; //指向前缀字符
        while (i<T[0])  //尾缀指针只要不到底，就继续循环
        {
            if(j==0 || T[i] == T[j])
            {
                i++;
                j++;
                //从这里开始改进
                if(T[i] != T[j])
                {
                    next_val[i] = j;  //等同于求next算法
                }else
                {
                    next_val[i] = next_val[j];  //如果ij指针位置值相等，就让他们的next也相等
                }
            }else
            {
                j = next_val[j];    //让j退回到前缀的前缀，因为前缀的前缀等于尾缀的尾缀
            }
        }
        return next_val;
    }

    public int patternMatching(int[] M,int[] T,int pos)
    {
        int i=pos;  //指向主串的指针
        int j=1;    //指向子串的指针
        int[] next = get_next(T);
        while (i<=M[0] && j<=T[0])  //尚未匹配完全
        {
            if(j==0 || M[i] == T[j])
            {
                i++;
                j++;
            }else
            {
                j = next[j];
            }
        }
        if(j>T[0])
            return i-j-pos+1;
        else
            return 0;

    }

    public int[] StringToCharCollection(String s)
    {
        char[] s_mid = s.toCharArray();
        int[] result = new int[s_mid.length+1];
        result[0] = s_mid.length;   //串的首位为字符串字符长度
        for(int i = 0 ; i < s_mid.length;i++)
        {
            result[i+1] = s_mid[i];
        }
        return result;
    }
}

