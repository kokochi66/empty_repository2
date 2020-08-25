#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct arr_string {
   int size;
   char *arr;
 } arr_string;

arr_string alloc_arr_string(int len) {
   arr_string a = {len, len > 0 ? malloc(sizeof(char*) * len) : NULL};
   return a;
 }

bool stringsRearrangement(arr_string inputArray) {

}

arr_string insertstring(arr_string a, char b[][4]){
	int len = a.size,i;
	for(i=0;i<len;i++) {
		a.arr[i] = malloc((sizeof(char)*strlen(b[i]))+1);
		a.arr[i] = b[i];
		printf("%s\n",b[i]); 
	}
	return a;
}


int main() {
	char inpt[3][4] = {"aba", "bbb", "ba"};
	printf("%d\n",strlen(inpt[2]));
	arr_string inp = alloc_arr_string(4);
	insertstring(inp,inpt);
	printf("%s\n",inp.arr[0]);
	
}


