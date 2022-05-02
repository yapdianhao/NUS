#include <stdio.h>
#define MAX 10

int readArray(char [], int);
int isPalindrome(char [], int);
void printArray(char [], int);

int main(void) {
	char array[MAX]; 
	int numElem, result;

	numElem = readArray(array, MAX);
	result = isPalindrome(array, numElem);
	if (result == 0) {
		printf("'%s' is not a palindrome\n", array);
	}
	else {
		printf("'%s' is a palindrome\n", array);
	}

	return 0;
}

int readArray(char arr[], int limit) {
	int i;
	char input;
	printf("Enter up to %d characters, terminating with a whitespace ' '.\n", limit);
	i = 0;
	scanf("%c", &input);
	while (input != ' ') {
		arr[i] = input;
		i++;
		scanf("%c", &input);
	}
	arr[i] = 0;
	return i;
}

int isPalindrome(char arr[], int size) {
    if (size == sizeof(arr) / 2) {
        return 1;
    } else {
        return arr[size - 1] == arr[sizeof(arr) - size] && isPalindrome(arr, size - 1);
    }
}

void printArray(char arr[], int size) {
	int i;

	for (i=0; i<size; i++) {
		printf("%c", arr[i]);
	}
	printf("\n");
}

