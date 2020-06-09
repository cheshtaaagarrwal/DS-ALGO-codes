public class bits{
    public static void main(String[] args){

    }
    public static int countAllSetBits_1(int num){
        int count=0;
        for(int i=0;i<32;i++)
        {
            int mask=(1<<i);
            if((num&mask)!=0)
            count++;
        }
        return count;
    }
    public static void countAllSetBits_2(int num){
        int count=0;
        while(num!=0){
            if((num&1)!=0)
            count++;
            num>>>=1;
        }
    }
    public static void countAllSetBits_3(int num){
        int count=0;
        while(num!=0){
            
            count++;
            num & = (num-1);//there is a difference of 1 set value by taking &
        }
    }

    public static int UniqueNumberinArray(int [] arr){
        int res=0;
        for(int ele: arr)
        res^=ele;
        return res;
    }
}