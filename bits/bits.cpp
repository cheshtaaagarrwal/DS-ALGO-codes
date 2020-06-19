#include <iostream>
using namespace std;
int offToOn(int num, int k)
{
    int mask = (1 << k);
    return (num | mask);
}
int onToOff(int num, int k)
{
    int mask = (~(1 << k));
    return (num & mask);
}
int countAllSetBits_2(unsigned_int num)
{
    int count = 0;
    while (num != 0)
    {
        if ((num & 1) != 0)
            count++;
        num >>= 1;
    }
    return count;
}

int main()
{
    int num = 123, k = 3;
    cout << onToOff(num, k);
    return 0;
}