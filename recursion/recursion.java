package recursion;

import java.util.Scanner;
import java.util.ArrayList;

public class recursion{
    public static Scanner scn=new Scanner(System.in);
    public static void main(String[] args){
      solve();
    }

    public static void solve(){
        // basic();
        questionSet();
    }

    public static void basic(){
        int a=scn.nextInt();
        int b=scn.nextInt();
        // System.out.println(fact(a));
        System.out.println(power(a,b));
    }

    public static int fact(int n){
        if(n<=1) return 1;
        return fact(n-1)*n;
    }

    public static int power(int a,int b){
        if(b==0) return 1;
        return power(a,b-1)*a;
    }

    public static int powerBtr(int a,int b){
        if(b==0) return 1;
        
        int halfAns=powerBtr(a,b/2);
        halfAns*=halfAns;
        
        return halfAns*(b%2!=0?a:1);
    }

    public static int calls(int n) {  // n=5
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += calls(n - 1);

        System.out.println("In: " + n);

        count += calls(n - 2);
        System.out.println("Post: " + n);

        return count + 3;
    }

    //recursionWithArray.==========================================

    public static void recursionWithArray(){
        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for(int i=0;i<n;i++) arr[i]=scn.nextInt();

        //int[] arr={1,2,3,4,5,6,7,8,9,10};
    }


    public static void display(int[] arr,int idx){
        if(idx==arr.length){
            return;
        }

        System.out.println(arr[idx]);
        display(arr,idx+1);
    } 

    public static int multiplyAllEle(int[] arr,int idx){
        if(idx==arr.length) return 1;
        return multiplyAllEle(arr,idx+1)*arr[idx];
    }

    public static int firstIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;

        if(arr[idx]==data) return idx;
        return firstIndex(arr,idx+1,data);
    }

    
    public static int lastIndex(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        
        int ans=lastIndex(arr,idx+1,data);
        if(ans!=-1) return ans;

        return arr[idx] == data ? idx : -1; 

    }

    
    public static int[] AllIndex(int[] arr,int idx,int data,int count){
        if(idx==arr.length){
            return new int[count];
        }

        if(arr[idx]==data) count++;
        
        int[] ans=AllIndex(arr,idx+1,data,count);
        
        if(arr[idx]==data)
            ans[count-1]=idx;

        return ans;
    }

    //questionSet.==================================================

    public static void questionSet(){
        //    ArrayList<String> ans=permutation("abcd");
        //    for(String s: ans) System.out.println(s);
        // System.out.println(subseq("abc",""));
        System.out.println(permutation("aba",""));
        
    }

    public static ArrayList<String> subsequence(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        
        ArrayList<String> smallAns=subsequence(nstr);
        ArrayList<String> myAns=new ArrayList<>();

        // for(int i=0;i<smallAns.size();i++){
        //     String s=smallAns.get(i);
        //     myAns.add(s);
        //     myAns.add(ch+s);            
        // }

        for(String s:smallAns){
            myAns.add(s);
            myAns.add(ch+s);
        }

        return myAns;
    }

    public static int subseq(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        int count=0;

        count+=subseq(nstr,ans+ch);
        count+=subseq(nstr,ans);
        
        return count;
    }

    public static ArrayList<String> permutation(String str){
        if(str.length()==1){
            ArrayList<String> base=new ArrayList<>();
            base.add(str);
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1); 
        
        ArrayList<String> smallAns=permutation(nstr);
        ArrayList<String> myAns=new ArrayList<>();

        for(String s:smallAns){
            for(int i=0;i<=s.length();i++){
                String ans=s.substring(0,i) + ch + s.substring(i);
                myAns.add(ans);
            }
        }
        return myAns;
    }

    public static int permutation(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            String nstr=str.substring(0,i) + str.substring(i+1);
            count+=permutation(nstr,ch + ans);
        }
        return count;
    }

    public static int permutationUnique(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        boolean[] vis=new boolean[26];
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(!vis[ch-'a']){
                vis[ch-'a']=true;
                String nstr=str.substring(0,i) + str.substring(i+1);
                count+=permutationUnique(nstr,ch + ans);
            }
        }
        return count;
    }


int[][] dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
String[] dirS = {"L", "N", "U", "E", "R", "S", "D", "W"};

class pair{
    String path;
    int len;
    
    pair(String path_,int len_){
        path=path_;
        len=len_;
    }
}

pair shortestPath(int sr, int sc, int er, int ec,int[][] board){
    if(sr==er && sc==ec){
        return new pair("",0);
    }

    board[sr][sc]=1;
    pair myAns=new pair("",board.length * board[0].length);
    
    for(int d=0;d<dirA.length;d++){
        int x = sr+ dirA[d][0];
        int y = sc+ dirA[d][1];
        
        if(x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y]==0){
            pair smallAns=shortestPath(x,y,er,ec,board);
            if(smallAns.len + 1 < myAns.len ){
                myAns.len = smallAns.len + 1;
                myAns.path = dirS[d] + smallAns.path;
            }
        }
    }

    board[sr][sc]=0;
    return myAns;
}
public static int coinChangePermutation_INF(int[] arr,int lidx, int tar, String ans){
    if(lidx==arr.length || tar==0){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        return 0;
    }

     int count=0;
     if(tar-arr[lidx]>=0)
         count+=coinChangePermutation_INF(arr,0,tar-arr[lidx],ans+ arr[lidx]+" ");
     count+=coinChangePermutation_INF(arr,lidx+1,tar,ans);
     
     return count;
}


public static int coinChangePermutation(int[] arr,int lidx, int tar, String ans){
    if(lidx==arr.length || tar==0){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        return 0;
    }

     int count=0;
    if (arr[lidx] > 0 && tar - arr[lidx] >= 0 ){
        int temp = arr[lidx];
        arr[lidx] = -arr[lidx];
        count+=coinChangePermutation(arr,0,tar-temp,ans + temp+" ");
        arr[lidx] = -arr[lidx];
    }
        count+=coinChangePermutation(arr,lidx+1,tar,ans);
     
     return count;
}


public static int coinChangeCombination_INF(int[] arr, int lidx, int tar, String ans){
if(lidx==arr.length || tar==0){
    if(tar==0){
        System.out.println(ans);
        return 1;
    }
    return 0;
}

 int count=0;
 if(tar-arr[lidx]>=0)
     count+=coinChangeCombination_INF(arr,lidx,tar-arr[lidx],ans+ arr[lidx]+" ");
 count+=coinChangeCombination_INF(arr,lidx+1,tar,ans);
 
 return count;
}


public static int coinChangeCombination(int[] arr, int lidx, int tar, String ans){
   if(lidx==arr.length || tar==0){
       if(tar==0){
           System.out.println(ans);
           return 1;
       }
       return 0;
   }

    int count=0;
    if(tar-arr[lidx]>=0)
        count+=coinChangeCombination(arr,lidx+1,tar-arr[lidx],ans+ arr[lidx]+" ");
    count+=coinChangeCombination(arr,lidx+1,tar,ans);
    
    return count;
}

//Queens.====================================================================

public static int queensCombination2D(boolean[][] rooms, int room, int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    System.out.println(ans);
    return 1;
}

int count = 0;
for (int r = room; r < rooms.length*rooms[0].length; r++){
    int x = r / rooms[0].length;
    int y = r % rooms[0].length;
    count += queensCombination2D(rooms, r + 1,tnq-1, ans + "(" + x + ", " + y + ") ");
}
return count;
}

public static int queensPermutation2D(boolean[][] rooms,int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    System.out.println(ans);
    return 1;
}

int count = 0;
for (int r = 0; r < rooms.length*rooms[0].length; r++){
    int x = r / rooms[0].length;
    int y = r % rooms[0].length;

    if (!rooms[x][y])
    {
        rooms[x][y] = true;
        count += queensPermutation2D(rooms,tnq-1,  ans + "(" + x + ", " + y + ") ");
        rooms[x][y] = false;
    }
}
return count;
}

//NqueenProblem.==================================================

public static boolean isAValidMove(boolean[][] board,int r,int c){
int[][] dirA={{0,-1},{-1,-1},{-1,0},{-1,1}};
// int[][] dirA={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};

for(int d=0;d<dirA.length;d++){
    for(int rad=1;rad<=board.length;rad++){
        int x= r + rad*dirA[d][0];
        int y= c + rad*dirA[d][1];
        
        if(x >= 0 && y >= 0 && x < board.length && y < board[0].length){
            if(board[x][y]) return false;
        }else break;
    }
}

return true;
}

public static int Nqueen_01(boolean[][] board, int lpsf, int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    System.out.println(ans);
    return 1;
}

int count = 0;
for (int r = lpsf; r < board.length*board[0].length; r++){
    int x = r / board[0].length;
    int y = r % board[0].length;
    if(isAValidMove(board,x,y)){
        board[x][y]=true;
        count += Nqueen_01(board, r + 1,tnq-1, ans + "(" + x + ", " + y + ") ");
        board[x][y]=false;
    }
}
return count;
}

public static int Nqueen_02(boolean[][] board, int idx, int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0 || idx==board.length*board[0].length)
{
    if(tnq==0){
       System.out.println(ans);
       return 1;
    }
    return 0;
}

int count = 0;

int x = idx / board[0].length;
int y = idx % board[0].length;
if(isAValidMove(board,x,y)){
    board[x][y]=true;
    count += Nqueen_02(board, idx + 1,tnq-1, ans + "(" + x + ", " + y + ") ");
    board[x][y]=false;
}
count += Nqueen_02(board, idx + 1,tnq, ans);
return count;
}

public static int Nqueen_03(boolean[][] board, int idx, int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0 || idx==board.length*board[0].length)
{
    if(tnq==0){
       System.out.println(ans);
       return 1;
    }
    return 0;
}

int count = 0;

int x = idx / board[0].length;
int y = idx % board[0].length;
if(!board[x][y] && isAValidMove(board,x,y)){
    board[x][y]=true;
    count += Nqueen_03(board, 0,tnq-1, ans + "(" + x + ", " + y + ") ");
    board[x][y]=false;
}
count += Nqueen_03(board, idx + 1,tnq, ans);
return count;
}

public static boolean Nqueen_04(boolean[][] rooms,int idx,int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    System.out.println(ans);
    return true;
}

boolean res=false;
for (int r = idx; r < rooms.length*rooms[0].length; r++){
    int x = r / rooms[0].length;
    int y = r % rooms[0].length;

    if (!rooms[x][y] && isAValidMove(rooms,x,y))
    {
        rooms[x][y] = true;
        res=res || Nqueen_04(rooms,0,tnq-1,  ans + "(" + x + ", " + y + ") ");
        rooms[x][y] = false;
    }
}
return res;
}


public static boolean nKnight(int[][] board,int r,int c,int move){
board[r][c]=move;
if(move==63){ 
   for(int[] b: board){
    for(int ele: b) System.out.print(ele+ " ");
    System.out.println();
   }
   return true;
}

int[] xMove = {  2, 1, -1, -2, -2, -1,  1,  2 }; 
int[] yMove = {  1, 2,  2,  1, -1, -2, -2, -1 };
boolean res=false;

for(int d=0;d<8;d++){
   int x = r + xMove[d];
   int y = c + yMove[d]; 

   if(x >= 0 && y >= 0 && x < 8 && y < 8 && board[x][y]==-1)
       res = res || nKnight(board , x , y , move+1);
}

board[r][c]=-1;

return res;

}


//optimised========================================================================

static boolean[] ROW;
static boolean[] COL;
static boolean[] DIAG;
static boolean[] ADIAG;

public static int Nqueen_05(int n,int m,int idx,int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    // System.out.println(ans);
    return 1;
}

int count = 0;
for (int r = idx; r < n*m; r++){
    int x = r / m;
    int y = r % m;

    if (!ROW[x] && !COL[y] && !DIAG[x+y] && !ADIAG[x-y+m-1])
    {
        ROW[x]=true; COL[y]=true; DIAG[x+y]=true; ADIAG[x-y+m-1]=true;
        count+= Nqueen_05(n,m,r+1,tnq-1,  ans);
        ROW[x]=false; COL[y]=false; DIAG[x+y]=false; ADIAG[x-y+m-1]=false;           
    }
}
return count;
}

static int row=0;
static int col=0;
static int diag=0;
static int adiag=0;

public static int Nqueen_06(int n,int m,int idx,int tnq, String ans) // qpsf: queen place so far.
{
if (tnq==0)
{
    // System.out.println(ans);
    return 1;
}

int count = 0;
for (int r = idx; r < n*m; r++){
    int x = r / m;
    int y = r % m;

    if ((row & (1<<x))==0 && (col & (1<<y))==0 && (diag & (1<<(x+y)))==0 && (adiag & (1<<(x-y + m - 1)))==0)
    {
        row^=(1<<x);
        col^=(1<<y);
        diag^=(1<<(x+y));
        adiag^=(1<<(x-y+m-1));

        count+= Nqueen_06(n,m,r+1,tnq-1,  ans );
       
        row^=(1<<x);
        col^=(1<<y);
        diag^=(1<<(x+y));
        adiag^=(1<<(x-y+m-1));
    }
}
return count;
}

//==========================================================================

public static String[] words={"mobile","samsung","sam","sung", 
"man","mango","icecream","and", 
"go","like","i","ice","cream","ilik","esa"};

public static boolean isContains(String word){
 for(String s: words) if(s.equals(word)) return true;
  return false;
}

public static int wordBreak(String str,int idx,String ans){
if(idx==str.length()){
   System.out.println(ans);
   return 1;
}

int count=0;
for(int i=idx+1;i<=str.length();i++){
    String smallStr=str.substring(idx,i);
    if(isContains(smallStr))
      count+=wordBreak(str,i,ans+ smallStr + " ");
}

return count;
}


public static void wordBreak(){
 String str="ilikesamsungandmangoandicecream";
System.out.println(wordBreak(str,0,""));
}



// ==========================================================================

public static void coinChange(){
int[] arr={2,3,5,7};
int tar=10;


//  System.out.println(coinChangePermutation_INF(arr,0,tar,""));
//  System.out.println(coinChangePermutation(arr,0,tar,""));
//  System.out.println(coinChangeCombination_INF(arr,0,tar,""));
System.out.println(coinChangeCombination(arr,0,tar,""));

}


public static void queenProblem()
{
boolean[][] rooms=new boolean[4][4];
int tnq = 4;

// System.out.println(queensCombination2D(rooms,0,tnq,""));
System.out.println(queensPermutation2D(rooms,tnq,""));
}

public static void Nqueen(){
//boolean[][] board=new boolean[10][10];
//int tnq=10;
// System.out.println(Nqueen_01(board,0,tnq,""));
// System.out.println(Nqueen_02(board,0,tnq,""));
// System.out.println(Nqueen_03(board,0,tnq,""));
// System.out.println(Nqueen_04(board,0,tnq,""));

// int[][] board=new int[8][8];
// for(int i=0;i<board.length;i++)
//   Arrays.fill(board[i],-1);
// System.out.println(nKnight(board,0,0,0));

int n=10;
ROW=new boolean[n];
COL=new boolean[n];
DIAG=new boolean[n+n-1];
ADIAG=new boolean[n+n-1];

long s= System.currentTimeMillis();
System.out.println(Nqueen_05(n,n,0,n,""));
long e= System.currentTimeMillis();
System.out.println(e-s);


// long s= System.currentTimeMillis();
// System.out.println(Nqueen_06(n,n,0,n,""));
// long e= System.currentTimeMillis();
// System.out.println(e-s);

}


}