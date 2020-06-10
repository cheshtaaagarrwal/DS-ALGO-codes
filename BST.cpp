#include<iostream>
#include<vector>
#include<string>
using namespace std;
 class Node{
     public:
        int data;
        Node* right=nullptr;
        Node* left=nullptr; //Node* left=nullptr;
        Node(int data){
            this->data=data;
        }
    };
    
    Node* ContructBST(vector<int> &arr,int si,int ei)
    {
       if(si>ei)
       return nullptr;
       int mid=(si+ei)>>1;// /2
       Node* node=new Node(arr[mid]);
       node->left=ContructBST(arr,si,mid-1);
       node->right=ContructBST(arr,mid+1,ei);
        return node;
    }

    void Display(Node *node){
        if(node==nullptr)
        return;
        string str="";
        str+=(node->left!=nullptr)?to_string(node->left->data):".";
        str+= "<--"+to_string(node->data)+"-->";
        str+=(node->right!=nullptr)?to_string(node->right->data):".";
       cout<<(str)<<endl;
        Display(node->left);
        Display(node->right);

    }

    //basic=======================================================

    int Height(Node* node){
        if(node==nullptr)
        return -1;
        return max(Height(node->left),Height(node->right)) +1;
    }

    int size(Node* node){
        if(node=nullptr)
        return 0;
        return size(node->left) +size(node->right)+1;
    }
    
    bool find(Node* node,int data){
        Node* curr=node;
        while(curr!=nullptr){
            if(curr->data==data)
            return true;
            else if(curr->data<data)
            curr=curr->right;
            else curr=curr->left;
        }
        return false;
    }

    bool findRec(Node* node,int data){
        if(node==nullptr)
        return false;
            if(node->data==data)
            return true;
            else if(node->data<data)
            return findRec(node->right,data);
            else 
            return findRec(node->left,data);
    
    }

    int Maximumele(Node* node)
    {
      Node* curr=node;
      while(curr->right!=nullptr)
      {
        curr=curr->right;
      }
      return curr->data;
    }

    int Minimumele(Node* node)
    {
      Node* curr=node;
      while(curr->left!=nullptr)
      {
        curr=curr->left;
      }
      return curr->data;
    }

    int LCAofBST(Node* node,int a,int b){
        Node* curr=node;
        while(curr!=nullptr){
            if(a>curr->data && b>curr->data)
            curr=curr->right;
             if(a<curr->data && b<curr->data)
            curr=curr->left;
            else
            return find(curr, a) && find(curr, b) ? curr->data : -1; //LCA point.
            
        }
        return -1;
    }

     int LCAofBSTrec(Node* node,int a,int b){
        
        if(node==nullptr)
        return -1;

            if(a>node->data && b>node->data)
            return LCAofBSTrec(node->right,a,b);
             if(a<node->data && b<node->data)
            return LCAofBSTrec(node->left,a,b);
            else
            return  node->data; //LCA point.      
    }

    void allNodesInRange_01(Node *node, int a, int b, vector<int> &ans)
{
    if (node == nullptr)
        return;

    allNodesInRange_01(node->left, a, b, ans);

    // //sorted Region.
    if (node->data >= a && node->data <= b)
        ans.push_back(node->data);

    allNodesInRange_01(node->right, a, b, ans);
}

//pre Order
void allNodesInRange_02(Node *node, int a, int b, vector<int> &ans)
{
    if (node == nullptr)
        return;
    if (node->data >= a && node->data <= b)
        ans.push_back(node->data);

    if (b < node->data && a < node->data)
        allNodesInRange_02(node->left, a, b, ans);
    else if (a > node->data && b > node->data)
        allNodesInRange_02(node->right, a, b, ans);
    else //LCA Region.
    {
        allNodesInRange_02(node->left, a, b, ans);
        allNodesInRange_02(node->right, a, b, ans);
    }
}

       void solve(){
        vector<int> arr={10,20,30,40,50,60,70,80,90,100,110,120,130};
        Node* root=ContructBST(arr,0,arr.size()-1);
        Display(root);
    //     cout<<LCAofBSTrec(root,30,60)<<endl;
    //    cout<< LCAofBST(root,30,60)<<endl;
    vector<int> ans;
    allNodesInRange_01(root,20,60,ans);
    for(int ele: ans)
    cout<<ele<<" ";
    }
    int main(){
     solve();
     return 0;
    }