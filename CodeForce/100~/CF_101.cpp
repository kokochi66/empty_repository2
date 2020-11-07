#include <bits/stdc++.h>
using namespace std;
// - #include <string>
// - #include <vector>
// - #include <memory>
// - #include <map>
// - #include <algorithm>
// - #include <queue>

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    string input;
    cin >> input;
    int n = input.length();
    
    int arr[255];
    for(int i=0;i<255;i++) arr[i] = 0;
    int i, j;
    for(int i=0;i<n;i++){
    	arr[input.at(i)]++;
	}
	int mins = INT_MAX;
	mins = min(mins,(arr['n']-1)/2);
	if(arr['n']<=3) mins = min(mins,arr['n']/3);
    mins = min(min(arr['i'],arr['t']),min(mins,arr['e']/3));
    cout << mins;
}
