#include <stdio.h>
//�� ���ڿ��� �־�������, �� ���ڿ����� ���� ���ڼ��� ��ȯ�ϴ� �Լ� 
int commonCharacterCount(char * s1, char * s2) {
	int s1_size , s2_size;
	int empty[100] = {0,};
	int output=0;
	
	for(int i=0;s1[i]!='\0';i++){
		for(int j=0;s2[j]!='\0';j++){
			if(s1[i]==s2[j] && empty[j]==0) {
				output++;
				empty[j]=1;
				break;
			}
		}
	}
	return output;
}
//���� ��� 

int commonCharacterCount2(char * s1, char * s2) {
    int count = 0;
    for(int i = 0; i < strlen(s1); i++){
        for(int j = 0; j < strlen(s2); j++){
            if(s1[i] == s2[j]){
                s1[i] = '#';
                s2[j] = '#';
                count++;
                break;
            }
        }
    }
    return count;
}
//������
//����Ʈ
//���ڿ� ��ü���� �ɷ������� empty������ ���������൵�Ǵ±��� 

int main() {
	char a[10] = "aabcc";
	char b[6] = "adcaa";
	int c = commonCharacterCount(a,b);
	printf("%d\n",c);
} 
