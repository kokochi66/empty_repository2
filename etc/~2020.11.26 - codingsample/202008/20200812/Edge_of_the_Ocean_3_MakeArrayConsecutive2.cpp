#include <stdio.h>
//�־��� �����迭�� ������� ���� ��, ���� ������ ������ ��ȯ 

int makeArrayConsecutive2(arr_integer s) {
    int max = s.arr[0];
    int min = s.arr[0];
    for(int i = 1; i < s.size; i++) {
        if(max < s.arr[i]) max = s.arr[i];
        if(min > s.arr[i]) min = s.arr[i];
    }
    return max - min - s.size + 1;
}

//C���� �Լ���ü������ �迭�� ���̸� ���ϴ� ����� ���ѵǾ� �����Ұ�
