#include <iostream>
#include <string>
#include <vector>
using namespace std;

void mazepath(int sr, int sc, int dr, int dc, string Myans)
{
    if (sr > dr || sc > dc)
    {
        return;
    }
    if (sr == dr && sc == dc)
    {
        cout << Myans << endl;
    }
    mazepath(sr + 1, sc, dr, dc, Myans + 'V');
    mazepath(sr, sc + 1, dr, dc, Myans + 'H');
    mazepath(sr + 1, sc + 1, dr, dc, Myans + 'D');
}

//suduko=====================================
bool Issafetoplace(vector<vector<char>> &board, int x, int y, int num)
{
    for (int i = 0; i < 9; i++)
    {
        if (board[x][i] - '0' == num)
            return false;
    }
    for (int i = 0; i < 9; i++)
    {
        if (board[i][y] - '0' == num)
            return false;
    }
    x -= x % 3;
    y -= y % 3;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[x + i][y + j] - '0' == num)
                return false;
        }
    }
    return true;
}
bool Solvesuduko_(vector<vector<char>> &board, vector<int> calls, int idx)
{
    if (idx == calls.size())
        return true;
    int r = calls[idx] / 9;
    int c = calls[idx] % 9;
    for (int i = 1; i <= 9; i++)
    {
        if (Issafetoplace(board, r, c, i))
        {
            board[r][c] = (char)(i + '0');
            if (Solvesuduko_(board, calls, idx + 1))
                return true;
            board[r][c] = '.';
        }
    }
    return false;
}

void solveSudoku(vector<vector<char>> &board)
{
    vector<int> calls;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == '.')
                calls.push_back(i * 9 + j);
        }
    }
    Solvesuduko_(board, calls, 0);
}
//===============================================
vector<int> Srow;
vector<int> Scol;
vector<vector<int>> Smat;
bool Solvesuduko_1(vector<vector<char>> &board, vector<int> calls, int idx)
{
    if (idx == calls.size())
        return true;
    int r = calls[idx] / 9;
    int c = calls[idx] % 9;
    for (int i = 1; i <= 9; i++)
    {
        int mask = 1 << i;
        if ((Srow[r] & mask) == 0 && (Scol[c] & mask) == 0 && (Smat[r / 3][c / 3] & mask) == 0)
        {
            board[r][c] = (char)(i + '0');
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;
            if (Solvesuduko_(board, calls, idx + 1))
                return true;
            board[r][c] = '.';
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;
        }
    }
    return false;
}

void solveSudoku_1(vector<vector<char>> &board)
{

    vector<int> calls;
    Srow.assign(9, 0);
    Scol.assign(9, 0);
    Smat.assign(3, vector<int>(3, 0));

    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == '.')
                calls.push_back(i * 9 + j);
            else
            {
                int num = board[i][j] - '0';
                int mask = 1 << num;
                Srow[i] ^= mask;
                Scol[j] ^= mask;
                Smat[i / 3][j / 3] ^= mask;
            }
        }
    }
    Solvesuduko_1(board, calls, 0);
}
//crypto================================================================================
string str1 = "send";
string str2 = "more";
string str3 = "money";
int Isnumberused = 0;
vector<int> assignednumber(26, 0);
int StringtoNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + assignednumber[str[i] - 'a'];
    }
    return res;
}
int cryptoSolver(string str, int idx)
{
    if (idx == str.length())
    {
        int num1 = StringtoNumber(str1);
        int num2 = StringtoNumber(str2);
        int num3 = StringtoNumber(str3);
        if (assignednumber[str1[0] - 'a'] != 0 && assignednumber[str2[0] - 'a'] != 0 && assignednumber[str3[0] - 'a'] != 0 && num1 + num2 == num3)
        {
            cout << num1 << endl
                 << "+" << num2 << endl
                 << "---------" << endl
                 << num3 << endl
                 << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int i = 0; i <= 9; i++)
    {
        int mask = 1 << i;
        if ((Isnumberused & mask) == 0)
        {
            Isnumberused ^= mask;
            assignednumber[str[idx] - 'a'] = i;
            count += cryptoSolver(str, idx + 1);
            assignednumber[str[idx] - 'a'] = 0;
            Isnumberused ^= mask;
        }
    }
    return count;
}
void crypto()
{
    string str = str1 + str2 + str3;
    vector<int> fre(26, 0);
    for (int i = 0; i < str.length(); i++)
    {
        fre[str[i] - 'a']++;
    }
    str = "";
    for (int i = 0; i < 26; i++)
    {
        if (fre[i] > 0)
            str += char(i + 'a');
    }
    cout << cryptoSolver(str, 0);
}

int main()
{
    int n, m;
    //cout<<"enter no of rows and column";
    //cin>>n>>m;
    //mazepath(0,0,n,m,"");
    crypto();
    return 0;
}