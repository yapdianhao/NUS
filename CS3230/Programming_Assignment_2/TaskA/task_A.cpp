/*
Name:
Describe your algorithm and explain your time complexity here:

Prove the correctness of your algorithm here:
*/

#include <iostream>
#include <vector> 

using namespace std;

int main() {
	int n;
	cin >> n;
	vector<int> a(2 * n), b(2 * n);
	for (int i = 0; i < n * 2; i++) {
		cin >> a[i] >> b[i];
	}
	long long answer = 0;

	///write your code here
	
	cout << answer << endl;

	return 0;
}
