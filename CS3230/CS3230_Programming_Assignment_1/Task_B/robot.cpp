/*
Name:
Describe your algorithm and explain your time complexity here.
*/
#include <iostream>
using namespace std;

int main() {
    int N, B;
    scanf("%d %d", &N, &B);
    int arr[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &arr[i]);
    }
	int W[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &W[i]);
    }
	int C[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &C[i]);
    }
	int WCopy[N];
    for (int i = 0; i < N; i++) {
        WCopy[i] = W[i];
    }
	int answer = 0;
    sort(WCopy, WCopy + N);
    for (int i = 0; i < N; i++) {
        cout << W[i] << " " << WCopy[i] << endl;
    }
    printf("%d\n", answer);
	return 0;
}
