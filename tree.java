import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
public class tree{
    public static void main(String[] args){
solve();
    }
    public static class Node{
        int data;
        Node right=null;
        Node left=null; //Node* left=nullptr;
        Node(int data){
            this.data=data;
        }
    }
    static int idx=0;
    public static Node ContructTree(int[] arr)
    {
        if(idx>=arr.length || arr[idx]==-1){
            idx++;
            return null;
        }
        Node node=new Node(arr[idx++]);
        node.left=ContructTree(arr);
        node.right=ContructTree(arr);
        return node;
    }

    public static void Display(Node node){
        if(node==null)
        return;
        String str="";
        str+=(node.left!=null)?node.left.data:".";
        str+= "<--"+node.data+"-->";
        str+=(node.right!=null)?node.right.data:".";
        System.out.println(str);
        Display(node.left);
        Display(node.right);
    }

    //basic=========================================

    public static int Size(Node node){
        if(node==null)
        return 0;
        return (Size(node.left)+Size(node.right)+1);
    }

    public static int Height(Node node){
        if(node==null)
        return -1;//wrt toedge return 0 wrt node
        return Math.max(Height(node.left),Height(node.right))+1;
    }   

     public static int Maximum(Node node){
        if(node==null)
        return (int)-1e8;//java Integer.MIN_VALUE,c++ INT_MIN;
        return Math.max(Math.max(Maximum(node.left),Maximum(node.right)),node.data);
    }  

    public static int Minimum(Node node){
        if(node==null)
        return (int)1e8;//java Integer.MAX_VALUE,c++ INT_MAX;
        return Math.min(Math.min(Minimum(node.left),Minimum(node.right)),node.data);
    }   

    public static boolean Find(Node node,int data){
        if(node==null)
        return false;
        if(node.data==data)
        return true;
        return Find(node.left,data)||Find(node.right,data);
    }

    //traversal===================================================

    public static void preOrder(Node node){
        if(node==null)
        return;
        System.out.print(node.data+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node){
        if(node==null)
        return;
        
        inOrder(node.left);
        System.out.print(node.data+" ");
        inOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node==null)
        return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data+" ");
    }

    public static boolean roottoNodepath(Node root,int data, ArrayList<Node> path){
        if(root==null)
        return false;
        if(root.data==data){
            path.add(root);
            return true;
        }
        boolean res=roottoNodepath(root.left,data,path)||roottoNodepath(root.right,data,path);
        if(res)
        path.add(root);
        return res;
    }

public static ArrayList<Node>  roottoNodepath_02(Node root,int data){
        if(root==null)
        return new ArrayList<>();
        if(root.data==data){
            ArrayList<Node> path=new ArrayList<>();
            path.add(root);
            return path;
        }
        ArrayList<Node> left=roottoNodepath_02(root.left,data);
        if(left.size()!=0){
            left.add(root);
            return left;
        }
        ArrayList<Node> right=roottoNodepath_02(root.right,data);
        if(right.size()!=0){
            right.add(root);
            return right;
    }
    return new ArrayList<>();
}
    public Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> path1=new ArrayList<>();
        ArrayList<Node> path2=new ArrayList<>();
        
        roottoNodepath(root,p,path1);
        roottoNodepath(root,q,path2);

        Node prev=null;
        int i=path1.size()-1;
        int j=path2.size()-1;
        
        while(i>=0 && j>=0){
          if(path1.get(i).data!=path2.get(j).data) break;
          
          prev=path1.get(i);
          i--;
          j--;
        }

        return prev;
    } 

    static Node LCANode=null;
    public static boolean lowestCommonAncestor_02(Node root, int p, int q) {
        if(root==null) return false;
        
        boolean selfDone=false;
        if(root.data==p || root.data==q){
            selfDone=true;
        }

        boolean leftDone=lowestCommonAncestor_02(root.left,p,q);
        if(LCANode!=null) return true;

        boolean rightDone=lowestCommonAncestor_02(root.right,p,q);
        if(LCANode!=null) return true;
        
        if((selfDone && leftDone) || (selfDone && rightDone) || (leftDone && rightDone))
          LCANode=root;


        return selfDone || leftDone || rightDone; 
    }

    public static void kDown(Node root,int level,Node blockNode){
       if(root==null || root==blockNode) return;

       if(level==0){
         System.out.print(root.data + " ");
         return;  
       }

       kDown(root.left,level-1,blockNode);
       kDown(root.right,level-1,blockNode);

    }

    public static void allNodeKAway(Node root, int target, int K) {
        ArrayList<Node> path=new ArrayList<>();
         roottoNodepath(root,target,path);

        Node blockNode=null;
        for(int i=0;i<path.size();i++){
            if(K-i<0) break;
            kDown(path.get(i),K-i,blockNode);
            blockNode=path.get(i);
        }
    }
    
    public static int allNodeKAway_02_(Node root, int target, int K) {
         if(root==null) return -1;

         if(root.data == target){
            kDown(root,K,null);
            return 1;
         }

         int leftdistance=allNodeKAway_02_(root.left,target,K);
         if(leftdistance!=-1){
            if(K-leftdistance >= 0) kDown(root,K-leftdistance,root.left);
            return leftdistance+1;
         }
         
         int rightdistance=allNodeKAway_02_(root.right,target,K);
          if(rightdistance!=-1){
            if(K-rightdistance >= 0) kDown(root,K-rightdistance,root.right);
            return rightdistance+1;
        }

        return -1;
    
    }

    public static void kDown(Node root,int level){
        if(root==null) return;
 
        if(level==0){
          System.out.print(root.data + " ");
          return;  
        }
 
        kDown(root.left,level-1);
        kDown(root.right,level-1);
 
     }

    public static int allNodeKAway_03_(Node root, int target, int K) {
        if(root==null) return -1;

        if(root.data == target){
           kDown(root,K,null);
           return 1;
        }

        int leftdistance=allNodeKAway_03_(root.left,target,K);
        if(leftdistance!=-1){
           if(K-leftdistance == 0)  
              System.out.print(root.data + " ");
           else
              kDown(root.right,K-leftdistance-1);
           return leftdistance+1;
        }
        
        int rightdistance=allNodeKAway_03_(root.right,target,K);
        if(rightdistance!=-1){
            if(K-rightdistance == 0)
               System.out.print(root.data + " ");
            else
               kDown(root.left,K-rightdistance-1);
            return rightdistance+1;
         }

       return -1;
   
   }

   public static int diameter_01(Node node){
       if(node==null) return 0;

       int ld=diameter_01(node.left);
       int rd=diameter_01(node.right);

       int lh=Height(node.left);
       int rh=Height(node.right);

       int myDia = lh + rh + 2;
       return Math.max(Math.max(ld,rd), myDia);
   }

   public static class diaPair{
       int dia=0;
       int hei=0;

       diaPair(int dia,int hei){
           this.dia=dia;
           this.hei=hei;
       }
   }

   public static diaPair diameter_02(Node node){
    if(node==null) return new diaPair(0,-1) ;

    diaPair lr=diameter_02(node.left); // left result
    diaPair rr=diameter_02(node.right); // right result

    diaPair myRes=new diaPair(0,-1);
    myRes.dia = Math.max(Math.max(lr.dia,rr.dia), (lr.hei+rr.hei+2));
    myRes.hei = Math.max(lr.hei,rr.hei)+1;
    
    return myRes;
}

static int diameter=0;
public static int diameter_03(Node node){
    if(node==null) return -1 ;

    int lh = diameter_03(node.left); // left height
    int rh =diameter_03(node.right); // right height
    
    diameter=Math.max(diameter,lh+rh+2);
    return Math.max(lh,rh)+1;
}
public boolean hasPathSum(Node root, int sum) {
        if(root==null)
            return false;
        if(root.left==null && root.right==null && sum-root.data==0)
            return true;
        boolean res=false;
        res=res||hasPathSum(root.left,sum-root.data);
        res=res||hasPathSum(root.right,sum-root.data);
        return res;
        
    }
    //LevelOrder_series.=========================================================
    public static void LevelOrder_00(Node node){
        LinkedList<Node> pQue= new LinkedList<>(); //addLast and removeFirst()
        pQue.addLast(node);
        while(pQue.size()!=0){
            Node rnode=pQue.removeFirst();
            System.out.print(rnode.data+" ");
            if(rnode.left!=null) pQue.addLast(rnode.left);
             if(rnode.right!=null) pQue.addLast(rnode.right);
        }
    }

    public static void LevelOrder_01(Node node){
        LinkedList<Node> pQue= new LinkedList<>(); //addLast and removeFirst()
        LinkedList<Node> cQue= new LinkedList<>();
        pQue.addLast(node);
        int count=0;
    System.out.print("Level: " + count + " -> ");
        while(pQue.size()!=0){
            Node rnode=pQue.removeFirst();
            System.out.print(rnode.data+" ");
            if(rnode.left!=null) cQue.addLast(rnode.left);
             if(rnode.right!=null) cQue.addLast(rnode.right);
             if(pQue.size()==0){
                 LinkedList<Node> temp=pQue;
                 pQue=cQue;
                 cQue=temp;
                 count++;      
         System.out.print("\nLevel: " + count + " -> ");
             }
        }
    }
    public static void LevelOrder_02(Node node){
        LinkedList<Node> Que= new LinkedList<>(); //addLast and removeFirst()
        Que.addLast(node);
        Que.addLast(null);
        int count=0;
    System.out.print("Level: " + count + " -> ");
        while(Que.size()!=1){
            Node rnode=Que.removeFirst();
            System.out.print(rnode.data+" ");
            if(rnode.left!=null) Que.addLast(rnode.left);
             if(rnode.right!=null) Que.addLast(rnode.right);
             if(Que.getFirst()==null){
                 Que.removeFirst();
                 Que.addLast(null);
                 count++;      
         System.out.print("\nLevel: " + count + " -> ");
             }
        }
    }

     public static void LevelOrder_03(Node node){
        LinkedList<Node> pQue= new LinkedList<>(); //addLast and removeFirst()
        pQue.addLast(node);
        int count=0;
        while(pQue.size()!=0){
             System.out.print("Level: " + count + " -> ");
             count++;
            int size=pQue.size();
            while(size-->0){
            Node rnode=pQue.removeFirst();
            System.out.print(rnode.data+" ");
            if(rnode.left!=null) pQue.addLast(rnode.left);
             if(rnode.right!=null) pQue.addLast(rnode.right);
            }
           System.out.println();
        }
    }

    //view==============================================================================================
public static void Leftview(Node node){
        LinkedList<Node> Que= new LinkedList<>(); //addLast and removeFirst()
        Que.addLast(node);
        while(Que.size()!=0){
             System.out.print(Que.getFirst().data+" ");
            int size=Que.size();
            while(size-->0){
            Node rnode=Que.removeFirst();
            if(rnode.left!=null) Que.addLast(rnode.left);
             if(rnode.right!=null) Que.addLast(rnode.right);
            }
        }
    }
    
public static void Rightview(Node node){
        LinkedList<Node> Que= new LinkedList<>(); //addLast and removeFirst()
        Que.addLast(node);
        while(Que.size()!=0){
             Node pre=null;
            int size=Que.size();
            while(size-->0){
            Node rnode=Que.removeFirst();
            if(rnode.left!=null) Que.addLast(rnode.left);
             if(rnode.right!=null) Que.addLast(rnode.right);
             pre=rnode;
            }
            System.out.print(pre.data+" ");
        }
    }
    static int Leftminval=0;
    static int Rightmaxval=0;
    public static void Width(Node node,int lev){
        if(node==null)
        return;
        Leftminval=Math.min(Leftminval,lev);
        Rightmaxval=Math.max(Rightmaxval,lev);
        Width(node.left,lev-1);
        Width(node.right,lev+1);
    }
    public static class Pairvo{
        Node node;
        int vl;
        Pairvo(Node node,int vl){
            this.node=node;
            this.vl=vl;
        }
    }
    public static void verticalorder(Node node){
        Width(node,0);
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        int n=Rightmaxval-Leftminval+1;
        for(int i=0;i<n;i++)
        arr.add(new ArrayList<>());
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                arr.get(rnode.vl).add(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+1));
            }
        }
        for(ArrayList<Integer> ar: arr)
          System.out.println(ar);
       System.out.println();
    }

     public static void verticalorderSum(Node node){
        Width(node,0);
        int [ ] arr=new int [Rightmaxval-Leftminval+1];
        
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                arr[rnode.vl]+=(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+1));
            }
        }
        for( int ar: arr)
          System.out.println(ar);
       System.out.println();
    }

     public static void Bottomview(Node node){
        Width(node,0);
        int [ ] arr=new int [Rightmaxval-Leftminval+1];
        
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                arr[rnode.vl]=(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+1));
            }
        }
        for( int ar: arr)
          System.out.println(ar);
       System.out.println();
    }

         public static void Topview(Node node){
        Width(node,0);
        int [ ] arr=new int [Rightmaxval-Leftminval+1];
        Arrays.fill(arr,(int)-1e8);
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                if(arr[rnode.vl]==(int)-1e8)
                arr[rnode.vl]=(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+1));
            }
        }
        for( int ar: arr)
          System.out.println(ar);
       System.out.println();
    }

    public static void diagonalorder(Node node){
        Width(node,0);
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        int n=-Leftminval+1;
        for(int i=0;i<n;i++)
        arr.add(new ArrayList<>());
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                arr.get(rnode.vl).add(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+0));
            }
        }
        for(ArrayList<Integer> ar: arr)
          System.out.println(ar);
       System.out.println();
    }

    public static void diagonalSum(Node node){
        Width(node,0);
        int [ ] arr=new int [-Leftminval+1];
        
        LinkedList<Pairvo> que=new LinkedList<>();
        que.addLast(new Pairvo(node,-Leftminval));
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Pairvo rnode=que.removeFirst();
                arr[rnode.vl]+=(rnode.node.data);
               if(rnode.node.left!=null) que.addLast(new Pairvo(rnode.node.left,rnode.vl-1));
               if(rnode.node.right!=null) que.addLast(new Pairvo(rnode.node.right,rnode.vl+0));
            }
        }
        for( int ar: arr)
          System.out.println(ar);
       System.out.println();
    }
//leetcode 987============================================================================================================
//     class Solution {
//     static int Leftminval=0;
//     static int Rightmaxval=0;
//     public static void width(TreeNode root,int lev){
//         if(root==null)
//             return;
//         Leftminval=Math.min(Leftminval,lev);
//          Rightmaxval=Math.max(Rightmaxval,lev);
//         width(root.left,lev-1);
//         width(root.right,lev+1);
        
//     }
//     public static class Pairvo implements Comparable<Pairvo>{
//         TreeNode node;
//         int vl;
//         Pairvo(TreeNode node,int vl){
//             this.node=node;
//             this.vl=vl;
//         }
//         @Override
//         public int compareTo(Pairvo o){// for c++: bool opeartor < ( pairvo const & o) const{
//             if(this.vl==o.vl)
//                 return this.node.val-o.node.val; in c++ replace - with >
//             return this.vl-o.vl; //default behaviour of que
//         }
//     }
//     public List<List<Integer>> verticalTraversal(TreeNode root) {
//         width(root,0);
//          List<List<Integer>> ans=new ArrayList<>();
//         int n=Rightmaxval-Leftminval+1;
//         for(int i=0;i<n;i++)
//             ans.add(new ArrayList<>());
//         PriorityQueue<Pairvo> pque=new PriorityQueue<>();
//         PriorityQueue<Pairvo> cque=new PriorityQueue<>();
//         pque.add(new Pairvo(root,-Leftminval));
//         while(pque.size()!=0){
//             int size=pque.size();
//             while(size-->0){
//                 Pairvo rpair=pque.poll();
//                 ans.get(rpair.vl).add(rpair.node.val);
//                 if (rpair.node.left != null) cque.add(new Pairvo(rpair.node.left, rpair.vl - 1));
//                 if (rpair.node.right != null) cque.add(new Pairvo(rpair.node.right, rpair.vl + 1));
//             }
//              PriorityQueue<Pairvo> temp=pque;
//             pque=cque;
//             cque=temp;
//         }
//         Leftminval=0;
//      Rightmaxval=0;
//         return ans;
//     }
// }

    static Node DDLhead=null;
    static Node DDLprev=null;
    public static void DDL(Node node){
        if(node==null)
          return;
          DDL(node.left);
          if(DDLhead==null)
          DDLhead=node;
          else{
             DLLprev.right = node;
            node.left = DLLprev;
          }
          DDLprev=node;
           DLL(node.right);
    } 
    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root=ContructTree(arr);
        Display(root);
        // ArrayList<Node> path=new ArrayList<>();
        // roottoNodepath(root,100,path);
        // System.out.print(path);
        // Rightview(root);
        // verticalorderSum(root);
        //Topview(root);
        diagonalSum(root);
    }
}
