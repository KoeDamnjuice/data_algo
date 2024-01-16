//本代码主要实现朴素模式的字符串模式匹配
//朴素模式匹配算法的最佳时间复杂度为O(m+n)
//朴素模式匹配算法的最坏时间复杂度为O((m-n+1)*n)
public class Main {
    public static void main(String[] args)
    {
        String test_1 = "啊哈哈哈哈123啊啊";
        String test_2 = "哈12";
        StringFunction sf = new StringFunction();
        int pos = sf.patternMatching(test_1,test_2,2);
        System.out.println(pos);
    }
}

class StringFunction
{
    public int patternMatching(String M,String T,int pos)
    {
        int[] A = StringToCharCollection(M);
        int[] B = StringToCharCollection(T);
        return MatchingIntCo(A,B,pos);
    }


    //M是主串
    //T是子串
    private int MatchingIntCo(int[] M,int[] T,int pos) throws RuntimeException
    {
        int i = pos;    //主串指针
        int j = 1;    //子串指针
        if(M[0] < T[0])
        {
            throw new RuntimeException("主串的长度不应该小于子串的长度");
        }
        while (j<T[0])
        {
            if(M[i] == T[j])
            {
                i++;
                j++;
            }
            else
            {
                i = i-j+2;
                j=1;
            }
        }
        return i-j+1;
    }

    //为了方便算法运行，转为int集合
    private int[] StringToCharCollection(String s)
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