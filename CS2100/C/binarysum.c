#include <stdio.h>
#include <stdbool.h>

int add(int num1[], int num2[], int res[]);
int arr1[] = {1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1};
int arr2[] = {0,0,0,0,1,0,0,1,0,0,0,0,0,0,1,0};
int arr3[] = {1, 1, 0, 0};
int arr4[] = {1, 0, 0 ,0};
int res[4];

int main() {
    //add(arr1, arr2, res);

    printf("%d\n", add(arr3, arr4, res));
    /*printf("====================");*/
    for (int i = 0; i < 4; i++) {
        printf("%d\n", res[i]);
    }
}

int add(int num1[], int num2[], int res[]) {
    bool carry = false;
    for (int i = 4; i > -1; i--) {
        int sum;
        if (carry) {
            sum = 1 + num1[i] + num2[i];
        } else {
            sum = num1[i] + num2[i];
        }
        if (sum > 1) {
            if (sum == 2) {
                res[i] = 0;
            } else if (sum == 3) {
                res[i] = 1;
            }
            carry = true;
        } else {
            res[i] = sum;
            carry = false;
        }
    }
    int start = 3;
    while (carry) {
        int sum = num1[start] + num2[start] + 1;
        if (sum > 1) {
            if (sum == 2) {
                res[start] = 0;
            } else if (sum == 3) {
                res[start] = 1;
            }
        } else {
            res[start] = sum;
            carry = false;
        }
        start--;
    }
    if (arr1[0] == arr2[0] && arr1[0] != res[0]) {
        return 1;
    } else {
        return 0;
    }
}