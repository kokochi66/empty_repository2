#include <stdio.h>
//���ڿ� �迭�� ���� �� ���ڿ��� �迭���� ����� �Լ� 
//C��� ��ü�� ������ ������ ����ü�� �̿��� ����� ���
// Arrays are already defined with this interface:
// typedef struct arr_##name {
//   int size;
//   type *arr;
// } arr_##name;
//
// arr_##name alloc_arr_##name(int len) {
//   arr_##name a = {len, len > 0 ? malloc(sizeof(type) * len) : NULL};
//   return a;
// }
//
// 
arr_string allLongestStrings(arr_string inputArray) {
    int max = 0, over=0, var=0;
    while(over<inputArray.size){
        printf("throgh %s %d\n", inputArray.arr[over], strlen(inputArray.arr[over]));
        if(max<strlen(inputArray.arr[over])){
            printf("max over %s\n",inputArray.arr[over]);
            max=strlen(inputArray.arr[over]);
            var=0;
            for(int i=over;i<inputArray.size;i++){
                inputArray.arr[var]=inputArray.arr[i];
                var++;
            }
            inputArray.size-=over;
            over=0;
        }
        else if(max>strlen(inputArray.arr[over])) {
            printf("small delete %s\n",inputArray.arr[over]);
            for(int i=over;i<inputArray.size-1;i++){
                inputArray.arr[i]=inputArray.arr[i+1];
            }
            inputArray.size-=1;
            over--;
        }
        over++;
    }
    return inputArray;
}
// ���� ���
arr_string allLongestStrings(arr_string inputArray) {
    arr_string res=alloc_arr_string(inputArray.size);
    int i,j=0,max=0;
    for(i=0;i<inputArray.size;i++)
        if(max<strlen(inputArray.arr[i])) max=strlen(inputArray.arr[i]);
    for(i=0;i<inputArray.size;i++)
        if(strlen(inputArray.arr[i])==max) {
            res.arr[j++]=inputArray.arr[i];
        }
    res.size=j;
    return res;
}
// ��� ���
//üũ�ؾ� �� �κ�
//���� ������ �ִ� inputArray�� �״�� ������ ä��, �迭�� ������ ���̴� ����� �����Ͽ�����,
//���ο� �迭�� �Ҵ��Ͽ� �� �迭�� ���ǿ� �����ϴ� ���� �߰��ϴ� ����� �ڵ��� �� �����ϰ� �̷���� �� ����. 
 
