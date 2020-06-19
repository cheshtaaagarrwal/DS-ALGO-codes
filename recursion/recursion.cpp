#include <iostream>
#include <vector>
using namespace std;

void printIncr(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncr(a + 1, b);
}

void printDec(int a, int b)
{
    if (a == b + 1)
        return;

    printDec(a + 1, b);
    cout << a << " ";
}

void printIncreDec(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0)
        cout << a << " hi " << endl;
    printIncreDec(a + 1, b);
    if (a % 2 != 0)
        cout << a << " bye " << endl;
}

//recursionWithArray.==========================================

void recursionWithArray()
{
    // int n;
    // cin >> n;
    // vector<int> arr(n, 0);
    // for (int i = 0; i < n; i++)
    //     cin >> arr[i];

    vector<int> arr{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}

int maximum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return max(arr[idx], maximum(arr, idx + 1));
}

int Minimum(vector<int> &arr, int idx)
{
    if (idx == arr.size() - 1)
        return arr[idx];

    return min(arr[idx], Minimum(arr, idx + 1));
}

bool find(vector<int> &arr, int idx, int data)
{
    if (idx == arr.size())
        return false;
    if (arr[idx] == data)
        return true;
    return find(arr, idx + 1, data);
}

//questionSet.==================================================

vector<string> words = {
    ":;/",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqrs",
    "tuv",
    "wx",
    "yz",
    "&*%",
    "#@$",
};
vector<string> keyPad(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);
    string word = words[ch - '0'];

    vector<string> smallAns = keyPad(nstr);
    vector<string> myAns;

    for (string s : smallAns)
    {
        for (int i = 0; i < word.length(); i++)
            myAns.push_back(word[i] + s);
    }
    return myAns;
}

int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    char ch = ques[0];
    string nques = ques.substr(1);
    string word = words[ch - '0'];

    int count = 0;
    for (int i = 0; i < word.length(); i++)
        count += keyPad(nques, ans + word[i]);

    return count;
}

int keyPad02(string ques, int idx, string ans)
{
    if (idx == ques.length())
    {
        cout << ans << endl;
        return 1;
    }

    char ch = ques[idx];
    string word = words[ch - '0'];

    int count = 0;
    for (int i = 0; i < word.length(); i++)
        count += keyPad02(ques, idx + 1, ans + word[i]);

    if (idx + 1 < ques.length())
    {
        int num = (ch - '0') * 10 + (ques[idx + 1] - '0');
        if (num == 10 || num == 11)
        {
            word = words[num];
            for (int i = 0; i < word.length(); i++)
                count += keyPad02(ques, idx + 2, ans + word[i]);
        }
    }
    return count;
}

//PathType.================================================

vector<string> mazepath_HVD(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    if (sr + 1 <= er)
    {
        vector<string> Verti = mazepath_HVD(sr + 1, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + s);
    }

    if (sc + 1 <= ec)
    {
        vector<string> Hori = mazepath_HVD(sr, sc + 1, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + s);
    }

    if (sc + 1 <= ec && sr + 1 <= er)
    {
        vector<string> Diag = mazepath_HVD(sr + 1, sc + 1, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + s);
    }
}

vector<string> mazepathMulti_HVD(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;

    for (int jump = 1; sr + jump <= er; jump++)
    {
        vector<string> Verti = mazepathMulti_HVD(sr + jump, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + to_string(jump) + s);
    }

    for (int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> Hori = mazepathMulti_HVD(sr, sc + jump, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + to_string(jump) + s);
    }

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
    {
        vector<string> Diag = mazepathMulti_HVD(sr + jump, sc + jump, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + to_string(jump) + s);
    }
}

int mazepath_HVD(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && ec == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += mazepath_HVD(sr + 1, sc, er, ec, ans + "V");
    if (sc + 1 <= ec)
        count += mazepath_HVD(sr, sc + 1, er, ec, ans + "H");
    if (sr + 1 <= er && sc + 1 <= ec)
        count += mazepath_HVD(sr + 1, sc + 1, er, ec, ans + "D");

    return count;
}

int mazepath_MultiHVD(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_MultiHVD(sr + jump, sc, er, ec, ans + "V" + to_string(jump));

    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr, sc + jump, er, ec, ans + "H" + to_string(jump));

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr + jump, sc + jump, er, ec, ans + "D" + to_string(jump));

    return count;
}

vector<vector<int>> dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
vector<string> dirS = {"L", "N", "U", "E", "R", "S", "D", "W"};

int floodFill(int sr, int sc, int er, int ec, vector<vector<int>> &board, string ans) // 0 free cell and 1 is blocked cell
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0; //mark.
    board[sr][sc] = 1;
    for (int d = 0; d < dirA.size(); d++)
    {
        int x = sr + dirA[d][0];
        int y = sc + dirA[d][1];
        if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
        {
            count += floodFill(x, y, er, ec, board, ans + dirS[d]);
        }
    }
    board[sr][sc] = 0; //unmark
    return count;
}

class FFpair
{
public:
    string path = "";
    int len;

    FFpair(string path_, int len_)
    {
        path = path_;
        len = len_;
    }
};

FFpair LongestHeight(int sr, int sc, int er, int ec, vector<vector<int>> &board) // 0 free cell and 1 is blocked cell
{
    if (sr == er && sc == ec)
    {
        FFpair base("", 0);
        return base;
    }

    FFpair myAns("", 0);
    board[sr][sc] = 1;
    for (int d = 0; d < dirA.size(); d++)
    {

        for (int rad = 1; rad <= board.size(); rad++)
        {
            int x = sr + rad * dirA[d][0];
            int y = sc + rad * dirA[d][1];
            if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
            {
                FFpair smallAns = LongestHeight(x, y, er, ec, board);
                if (myAns.len < smallAns.len + 1)
                {
                    myAns.len = smallAns.len + 1;
                    myAns.path = dirS[d] + to_string(rad) + smallAns.path;
                }
            }
        }
    }
    board[sr][sc] = 0; //unmark
    return myAns;
}

int ShortestHeight(int sr, int sc, int er, int ec, vector<vector<int>> &board) // 0 free cell and 1 is blocked cell
{
    if (sr == er && sc == ec)
    {
        return 0;
    }

    int myHeight = board.size() * board[0].size();
    board[sr][sc] = 1;
    for (int d = 0; d < dirA.size(); d++)
    {
        for (int rad = 1; rad <= board.size(); rad++)
        {
            int x = sr + rad * dirA[d][0];
            int y = sc + rad * dirA[d][1];
            if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] == 0)
            {
                myHeight = min(myHeight, ShortestHeight(x, y, er, ec, board));
            }
        }
    }
    board[sr][sc] = 0; //unmark
    return myHeight + 1;
}

//leetcode 1219.================================================

int getMaximumGold(int r, int c, vector<vector<int>> &board, vector<vector<int>> &dir)
{
    board[r][c] = -board[r][c];
    int res = 0;
    for (int d = 0; d < 4; d++)
    {
        int x = r + dir[d][0];
        int y = c + dir[d][1];

        if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] > 0)
            res = max(res, getMaximumGold(x, y, board, dir));
    }

    board[r][c] = -board[r][c];
    return res + board[r][c];
}

int getMaximumGold(vector<vector<int>> &board)
{
https: //zoom.us/j/3020059848?pwd=Y3k5eU1jckJpc0NLaTRLZlBCdC9tUT09
    vector<vector<int>> dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int res = 0;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] > 0)
            {
                res = max(res, getMaximumGold(i, j, board, dir));
            }
        }
    }
    return res;
}

//leetcode 980.==============================================

int uniquePathsIII(int sr, int sc, int er, int ec, int freeCell, vector<vector<int>> &board, vector<vector<int>> &dir)
{
    freeCell--;
    if (sr == er && sc == ec)
    {
        if (freeCell == 0)
            return 1;
        return 0;
    }

    int count = 0;
    int temp = board[sr][sc];
    board[sr][sc] = -2;

    for (int d = 0; d < 4; d++)
    {
        int x = sr + dir[d][0];
        int y = sc + dir[d][1];
        if (x >= 0 && y >= 0 && x < board.size() && y < board[0].size() && board[x][y] >= 0)
            count += uniquePathsIII(x, y, er, ec, freeCell, board, dir);
    }

    board[sr][sc] = temp;
    return count;
}

int uniquePathsIII(vector<vector<int>> &board)
{
    vector<vector<int>> dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    int sr, sc, er, ec;
    int freeCell = 0;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] != -1)
                freeCell++;
            if (board[i][j] == 1)
            {
                sr = i;
                sc = j;
            }

            if (board[i][j] == 2)
            {
                er = i;
                ec = j;
            }
        }
    }

    return uniquePathsIII(sr, sc, er, ec, freeCell, board, dir);
}

int coinChangePermutation_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[lidx] >= 0)
            count += coinChangePermutation_INF(arr, 0, tar - arr[lidx], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangePermutation(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
    {
        int temp = arr[i];
        if (arr[i] > 0 && tar - arr[i] >= 0)
        {
            arr[i] = -arr[i];
            count += coinChangePermutation(arr, 0, tar - temp, ans + to_string(temp) + " ");
            arr[i] = -arr[i];
        }
    }
    return count;
}

int coinChangeCombination_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination_INF(arr, i, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

int coinChangeCombination(vector<int> &arr, int lidx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = lidx; i < arr.size(); i++)
        if (tar - arr[i] >= 0)
            count += coinChangeCombination(arr, i + 1, tar - arr[i], ans + to_string(arr[i]) + " ");

    return count;
}

//Queen's===========================================================

int queensCombination(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        count += queensCombination(rooms, r + 1, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");

    return count;
}

int queensPermutation(vector<bool> &rooms, int room, int qpsf, int tnq, string ans) // qpsf: queen place so far.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int r = room; r < rooms.size(); r++)
        if (!rooms[r])
        {
            rooms[r] = true;
            count += queensPermutation(rooms, 0, qpsf + 1, tnq, ans + "Q" + to_string(qpsf) + "R" + to_string(r) + " ");
            rooms[r] = false;
        }

    return count;
}

//Sudoku.====================================================================

bool isSafeToPlaceNumber(vector<vector<char>> &board, int x, int y, int num)
{
    //row
    for (int i = 0; i < 9; i++)
        if (board[x][i] - '0' == num)
            return false;

    //col
    for (int i = 0; i < 9; i++)
        if (board[i][y] - '0' == num)
            return false;

    //mat
    x = (x / 3) * 3; // x-=x%3;
    y = (y / 3) * 3; // y-=y%3;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (board[x + i][y + j] - '0' == num)
                return false;

    return true;
}

vector<int> Srow;
vector<int> Scol;
vector<vector<int>> Smat;

bool solveSudoku_(vector<vector<char>> &board, vector<int> &calls, int idx)
{
    if (idx == calls.size())
        return true;

    int r = calls[idx] / 9;
    int c = calls[idx] % 9;
    for (int num = 1; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((Srow[r] & mask) == 0 && (Scol[c] & mask) == 0 && (Smat[r / 3][c / 3] & mask) == 0)
        {
            board[r][c] = (char)(num + '0');
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;

            if (solveSudoku_(board, calls, idx + 1))
                return true;

            board[r][c] = '.';
            Srow[r] ^= mask;
            Scol[c] ^= mask;
            Smat[r / 3][c / 3] ^= mask;
        }
    }
    return false;
}

//==============================================================================

void solveSudoku(vector<vector<char>> &board)
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

    solveSudoku_(board, calls, 0);
}

//Crypto.==========================================================
string str1 = "send";
string str2 = "more";
string str3 = "money";
int isNumberUsed = 0;
vector<int> assignedNumber(26, -1);

int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
        res = res * 10 + assignedNumber[str[i] - 'a'];
    return res;
}

int cryptoSolver_(string &str, int idx)
{
    if (idx == str.length())
    {
        int num1 = stringToNumber(str1);
        int num2 = stringToNumber(str2);
        int num3 = stringToNumber(str3);

        if (assignedNumber[str1[0] - 'a'] != 0 && assignedNumber[str2[0] - 'a'] != 0 && assignedNumber[str3[0] - 'a'] != 0 && num1 + num2 == num3)
        {
            cout << num1 << endl
                 << "+" << num2 << endl
                 << "------" << endl
                 << num3 << endl
                 << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    for (int num = 0; num <= 9; num++)
    {
        int mask = 1 << num;
        if ((isNumberUsed & mask) == 0)
        {
            isNumberUsed ^= mask;
            assignedNumber[str[idx] - 'a'] = num;

            count += cryptoSolver_(str, idx + 1);

            isNumberUsed ^= mask;
            assignedNumber[str[idx] - 'a'] = -1;
        }
    }
    return count;
}

void crypto()
{
    string str = str1 + str2 + str3;
    vector<int> freq(26, 0);
    for (int i = 0; i < str.length(); i++)
        freq[str[i] - 'a']++;

    str = "";
    for (int i = 0; i < 26; i++)
        if (freq[i] > 0)
            str += (char)(i + 'a');

    // cout << str << endl;
    cout << cryptoSolver_(str, 0) << endl;
}

//SetProblem.===============================================================

int equiSet(vector<int> &arr, int idx, int set1, int set2, string set1S, string set2S)
{
    if (idx == arr.size())
    {
        if (set1 == set2)
        {
            cout << set1S + " = " + set2S << endl;
            return 1;
        }

        return 0;
    }

    int count = 0;
    count += equiSet(arr, idx + 1, set1 + arr[idx], set2, set1S + " " + to_string(arr[idx]), set2S);
    count += equiSet(arr, idx + 1, set1, set2 + arr[idx], set1S, set2S + " " + to_string(arr[idx]));

    return count;
}

int equiSet_02(vector<int> &arr, int idx, int set1, int set2, string set1S, string set2S)
{
    if (idx == arr.size())
    {
        if (set1 == set2 && set1 != 0)
        {
            cout << set1S + " = " + set2S << endl;
            return 1;
        }

        return 0;
    }
    int count = 0;
    count += equiSet_02(arr, idx + 1, set1 + arr[idx], set2, set1S + to_string(arr[idx]) + " ", set2S);
    count += equiSet_02(arr, idx + 1, set1, set2 + arr[idx], set1S, set2S + to_string(arr[idx]) + " ");

    count += equiSet_02(arr, idx + 1, set1, set2, set1S, set2S);

    return count;
}

//CROSS WORD.=================================================
vector<vector<char>> board{{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
vector<string> words = {"agra", "norway", "england", "gwalior"};

bool canPlaceHorizontal(string word, int x, int y)
{

    if (y == 0 && word.length() < board[0].size())
    {
        if (board[x][y + word.length()] != '+')
            return false;
    }
    else if ((y + word.length()) == board[0].size() && (word.length() != board[0].size()))
    {
        if (board[x][y - 1] != '+')
            return false;
    }
    else
    {
        if (((y - 1) >= 0 && board[x][y - 1] != '+') || ((y + word.length()) < board[0].size() && board[x][y + word.length()] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((y + i) == board[0].size())
            return false;
        if (board[x][y + i] != '-' && board[x][y + i] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordHorizontal(string word, int x, int y)
{
    vector<bool> loc(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[x][y + i] == '-')
        {
            loc[i] = true;
            board[x][y + i] = word[i];
        }
    }
    return loc;
}

void unPlaceWordHorizontal(string word, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < word.length(); i++)
        if (loc[i])
            board[x][y + i] = '-';
}

bool canPlaceVertical(string word, int x, int y)
{

    if (x == 0 && word.length() < board.size())
    {
        if (board[x + word.length()][y] != '+')
            return false;
    }
    else if ((x + word.length()) == board.size() && (word.length() != board.size()))
    {
        if (board[x - 1][y] != '+')
            return false;
    }
    else
    {
        if (((x - 1) >= 0 && board[x - 1][y] != '+') || ((x + word.length()) < board.size() && board[x + word.length()][y] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((x + i) == board.size())
            return false;
        if (board[x + i][y] != '-' && board[x + i][y] != word[i])
            return false;
    }

    return true;
}

vector<bool> placeWordVertical(string word, int x, int y)
{
    vector<bool> loc(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[x + i][y] == '-')
        {
            loc[i] = true;
            board[x + i][y] = word[i];
        }
    }

    return loc;
}

void unPlaceWordVertical(string word, int x, int y, vector<bool> &loc)
{
    for (int i = 0; i < word.length(); i++)
        if (loc[i])
            board[x + i][y] = '-';
}

int crossWord_(int idx)
{
    if (idx == words.size())
    {
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
                cout << board[i][j] << " ";
            cout << endl;
        }

        return 1;
    }

    string word = words[idx];
    int count = 0;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {
                if (canPlaceHorizontal(word, i, j))
                {
                    vector<bool> loc = placeWordHorizontal(word, i, j);
                    count += crossWord_(idx + 1);
                    unPlaceWordHorizontal(word, i, j, loc);
                }

                if (canPlaceVertical(word, i, j))
                {
                    vector<bool> loc = placeWordVertical(word, i, j);
                    count += crossWord_(idx + 1);
                    unPlaceWordVertical(word, i, j, loc);
                }
            }
        }
    }
    return count;
}

void crossWord()
{
    crossWord_(0);
}

void SetProblem()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    // cout << equiSet(arr, 1, 10, 0, "10 ", "") << endl;
    cout << equiSet_02(arr, 0, 0, 0, "", "") << endl;
}

void queenProblem()
{
    vector<bool> rooms(16, false);
    int tnq = 4;
    cout << queensCombination(rooms, 0, 0, tnq, "") << endl;
    // cout << queensPermutation(rooms, 0, 0, tnq, "") << endl;
}

void coinChange()
{
    // vector<int> arr{2, 3, 5, 7};
    // vector<int> arr{1,1,1,1,1};
    int tar = 10;
    // cout << coinChangePermutation_INF(arr,0, tar, "") << endl;
    // cout << coinChangePermutation(arr, 0, tar, "") << endl;

    // cout << coinChangeCombination_INF(arr,0, tar, "") << endl;
    // cout << coinChangeCombination(arr, 0, tar, "") << endl;
}

void pathType()
{
    int n = 3, m = 5;
    // cout << mazepath_MultiHVD(0, 0, n - 1, m - 1, "") << endl;
    vector<vector<int>> board(3, vector<int>(3, 0));
    // cout << floodFill(0, 0, 2, 2, board, "") << endl;
    FFpair ans = LongestHeight(0, 0, 2, 2, board);
    cout << ans.path << " " << ans.len << endl;
    // cout << ShortestHeight(0, 0, 2, 2, board) << endl;
}

void questionSet()
{
    // vector<string> ans = keyPad("489");
    // cout << keyPad("489", "");
    cout << keyPad("1011489", "");

    // for (string s : ans)
    //     cout << s << " ";
}

void basics()
{
    int a, b;
    cin >> a >> b;

    // printIncr(a, b);
    // printDec(a, b);
    printIncreDec(a, b);
}

void solve()
{
    // basics();
    // questionSet();
    pathType();
}

int main()
{
    solve();
    return 0;
}