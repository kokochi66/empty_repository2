#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h> 

bool isIPv4Address(char *inputString) {
	int i,count=0,temp=0;
	for(i=0;i<strlen(inputString);i++){
		if(inputString[i]=='.') {
			if(i==0||i==strlen(inputString)-1) {
				printf("����1. �����̳� ���� .�� ��(Ư������)\n");
				return false;	//Ư������ó��
			}
			else if(temp==0&&inputString[i-1]!='0') {
				printf("����2. temp�� ���ǿ� ���� ����(temp�� ����ų�, ������ ��ġ���� ����)\n");
				return false;
			}
			count++;
			temp=0;
		}
		else {
			if(inputString[i]-48<0||inputString[i]-48>9) {
				printf("����4. ���ڰ� �ƴ�\n");
				return false; 
			}
			else if(inputString[i]-48==0) {
				if(i!=strlen(inputString)-1) if(inputString[i+1]!='.') return false;
				if(i!=0) if(inputString[i-1]!='.') return false;
			}
			else {
				temp*=10;
				temp+=inputString[i]-48;
				if(temp>255){
					printf("����2. temp�� ���ǿ� ���� ����(temp�� ����ų�, ������ ��ġ���� ����)\n");
					return false;
				}
			}
		}
	}
	if(count!=3) {
		printf("����5. ���� ������ ���� ����\n");
		return false;
	}
	return true;
}


int main() {
	char input[50] = "01.233.161.131";
	if(isIPv4Address(input)) printf("True\n");
	else printf("False\n");
	
	
}


