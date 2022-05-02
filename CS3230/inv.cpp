#include <iostream>
#include <array>
#include <unordered_map>

using namespace std;

/**
    Idea: enhanced merge sort. In a normal merge sort, in the merge function, if leftarr[i] > rightarr[j], 
    then all the remaining elements in the left array after leftarr[i] is larger than leftarr[j], so we have 
    leftarray's length - i inversions. However, since this question requests for a multiple of P, we cannot 
    make sure that all the inverted elements are a multiple of n. So, a storing of the visited elements in a data 
    structure is required. We are going to use a data structure with O(1) accessing time, the hashmap.

    Idea: before the merging, first iterate through the left and right array. If the rightarr[j] < leftarr[i],
    we will "record" the remainder of the smaller element divided by P, rightarr[j] % P into the hashmap. If rightarr[j] is 
    a multiple of P, we will add P instead. If leftarr[i] < rightarr[j], we will find the complement of leftarr[i] % P which adds up to P,
    and add the value of the complement to the inversions. An example is shown below.

    leftarr = [4,5,6,7], rightarr = [1,2,3,9], and find inversions that sum to a multiple of 2.
    [4,5,6,7]  [1,2,3,9]  hashmap = {}
    ^           ^
    after first iteration,
    [4,5,6,7]  [1,2,3,9]  {1: 1}
     ^            ^
    [4,5,6,7] [1,2,3,9]  {1: 1, 0: 1}
     ^             ^  
    [4,5,6,7] [1,2,3,9] {1: 2, 0: 1}
     ^               ^
    [4,5,6,7] [1,2,3,9] {1: 2, 0: 1}   inv = 1
       ^             ^
    [4,5,6,7] [1,2,3,9] {1: 2, 0: 1}   inv = 1 + 2 = 3
         ^           ^
    [4,5,6,7] [1,2,3,9] {1: 2. 0: 1}   inv = 3 + 1 = 4
           ^         ^
    [4,5,6,7] [1,2,3,9] {1: 2, 0: 1}   inv = 4 + 2 = 6 //done. 

    Time complexity: 2T(n/2) + O(n) = ⍬(n lg n)
*/

int mergesort(int arr[], int temp[], int left, int right, int key);
int merge(int arr[], int temp[], int left, int mid, int right, int key);

int mergesort(int arr[], int temp[], int left, int right, int key) {
    int mid, inv = 0;
    if (right > left) {
        mid = (right + left) / 2;
        inv += mergesort(arr, temp, left, mid, key);
        inv += mergesort(arr, temp, mid + 1, right, key);
        inv += merge(arr, temp, left, mid + 1, right, key);
    }
    return inv;
}

int merge(int arr[], int temp[], int left, int mid, int right, int key) {
    unordered_map<int, int> map;
    int i, j, k, inv = 0;
    i = left;
    j = mid;
    k = left;
    while ((i <= mid - 1) && j <= right) {
        if (arr[i] > arr[j]) {
            int remainder = arr[j] % key;
            if (remainder == 0) {
                remainder = key;
            }
            if (map.find(remainder) == map.end()) {
                map.insert(make_pair(remainder, 1));
            } else {
                map.find(remainder) -> second += 1;
                int value = map.find(remainder) -> second;
            }
            j++;
        } else {
            int remainder = arr[i] % key;
            if (map.find(key - remainder) != map.end()) {
                inv += map.find(key - remainder) -> second;
            }
            i++;
        }
    }
    while (i <= (mid - 1)) {
        int remainder = arr[i] % key;
        if (map.find(key - remainder) != map.end()) {
            inv += map.find(key - remainder) -> second;
        }
        i++;
    }
    i = left;
    j = mid;
    k = left;
    while ((i <= mid - 1) && (j <= right)) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        }
        else {
            temp[k++] = arr[j++];
        }
    }
    while (i <= (mid - 1)) {
        temp[k++] = arr[i++];
    }
    while (j <= right) {
        temp[k++] = arr[j++];
    }
    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }
    //cout << "=============" << endl;
    return inv;
}

int main() {
    int n, p;
    cin >> n >> p;
    //cout << n << endl << p << endl;
    int arr[n];
    int temp[n];

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
        //cout << arr[i] << endl;
    }

    int res = mergesort(arr, temp, 0, n - 1, p);
    for (int i = 0; i < n; i++) {
        //cout << arr[i] << endl;
    }
    cout << res << endl;
}
