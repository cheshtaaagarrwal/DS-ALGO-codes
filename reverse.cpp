#include<iostream>
#include<cstring>
#include<algorithm>
using namespace std;
void Reverse(string str,int idx){
    int i;
    for( i=idx;i<str.size();i++){
        if(str[i]==' '){
            string rev=str.substr(0,i);
            rev=reverse(str.begin(),str.end());
            cout<<rev<<" ";
            break;
        }
    }
    Reverse(str.substr(i+1),i+1);

}
int main(){
    string str;
    getline(cin,str);
    Reverse(str,0);
}