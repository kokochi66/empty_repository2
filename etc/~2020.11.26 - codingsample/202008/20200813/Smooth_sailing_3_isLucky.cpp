#include <stdio.h>
//������ �־�������, �� ���ݰ� �� ������ ���� ������ true, �ƴϸ� false�� ��ȯ 
bool isLucky(int n) {
	int temp=0, var=n;
	while(var>0) {
		var/=10;
		temp++;
	}
	int sumA=0;
	int sumB=0;
	for(int i=1;i<=temp;i++){
		if(i<=temp/2) sumA+=n%10;
		else sumB+=n%10;
		n/=10;
	}
	return sumA==sumB;
}
//���� ��� 


int main() {
	int a = 1230;
	int b = 239017;
	if(isLucky(b)) printf("True\n");
	else printf("False\n");
} 
