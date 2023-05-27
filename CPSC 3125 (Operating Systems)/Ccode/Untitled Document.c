#include <stdio.h>

int main()
{
	int ret1;
	ret1 = fork();

	if(ret1 == 0){
		int ret2 = fork();
		printf("hey there!\n");
		if(ret2 == 0) fork();
		fork();
		printf("Hey there again\n");
	}
	else if (ret1 > 0){
		int ret3 = fork();
		if(ret3 > 0) printf("hey there once again\n");
	}
}
