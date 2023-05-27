#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
    int start = atoi(argv[1]);
    pid_t pid;

    pid = fork();
    if (pid < 0){
        fprintf(stderr, "Fork Failed");
        return 1;
    }
    
    if (pid > 0){
        printf("MAIN PROCESS START\n");
        wait(NULL);
    }
    else{
        printf("CHILD PROCESS START\n");

        int n = start;
        if (n<1){ 
            printf("Cannot use 0 or negative integers. \nPlease restart and input 1 or more.\n");
            return 1; 
        }
        else{
            while (n != 1){ 
                if (n % 2 == 0){ 
                    n = n / 2; 
                    printf("%d ", n);
                }
                else if (n % 2 == 1){ 
                    n = (3 * n) + 1;
                    printf("%d ", n);
                }
            }

            printf("\nCHILD PROCESS END\n");
        }
    }

    if (pid > 0){ printf("MAIN PROCESS END\n"); }

    return 0;
}
