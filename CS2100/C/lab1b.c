#include <stdio.h>
#include <string.h>
#define MAX 10

int readArray(int [], int);
void printArray(int [], int);
void reverseArray(int [], size_t);
void findSize(int[]);

int main(void) {
  int array[MAX], numElements;
  int b[5] = {1, 2, 3, 4, 5};

  numElements = readArray(array, MAX);
  reverseArray(array, numElements);
  printArray(array, numElements);
  //findSize(b);
  printf("%d", sizeof(b) / sizeof(b[0]));

  return 0;
}

int readArray(int arr[], int limit) {
  int i, input;

  printf("Enter up to %d integers, terminating with a negative integer.\n", limit);
  i = 0;
  scanf("%d", &input);
  while (input >= 0) {
    arr[i] = input;
    i++;
    scanf("%d", &input);
  }
  return i;
}

void findSize(int arr[]) {
  int size = sizeof(arr)/sizeof(arr[0]);
  printf("%d", size);
}

void reverseArray(int arr[], size_t size) {
  for (int i = 0; i < size / 2; i++) {
    int *a, *b, temp;
    a = &arr[i];
    b = &arr[size - 1 - i];
    temp = *b;
    *b = *a;
    *a = temp;
  }
}

void printArray(int arr[], int size) {
  int i;

  for (i=0; i<size; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
}
