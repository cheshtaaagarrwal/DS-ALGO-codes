
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
    int idx=0;
    Node* ContructTree(vector<int> arr)
    {
        if(idx==arr.size() || arr[idx]==-1){
            idx++;
            return nullptr;
        }
        Node* node=new Node(arr[idx++]);
        node->left=ContructTree(arr);
        node->right=ContructTree(arr);
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
    
     void solve(){
        vector<int>arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,70,110,-1,-1,120,-1,-1};
        Node* root=ContructTree(arr);
        Display(root);
    }
     int main(){
solve();
return 0;
    }