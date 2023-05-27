#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>

#define BUFFER_SIZE 25
#define READ_END 0
#define WRITE_END 1

int main(void){
    char write_msg[BUFFER_SIZE] = "Thomas";
    char read_msg[BUFFER_SIZE];
    char read_msg_flip[BUFFER_SIZE];
    int fd[2];
    int fd2[2];
    pid_t pid;

    if (pipe(fd) == 1){ 
        fprintf(stderr, "Pipe Failed"); 
        return 1;
    }
    
    if (pipe(fd2) == 1){ 
        fprintf(stderr, "Pipe Failed"); 
        return 1;
    }

    pid = fork();

    if (pid < 0){
        fprintf(stderr, "Fork Failed");
        return 1;
    }

    if (pid > 0){
        printf("String: %s\n", write_msg);
        write(fd[WRITE_END], write_msg, strlen(write_msg)+1 );
        wait(NULL);
        close(fd[WRITE_END]);
    }
    else{
        
        read(fd[READ_END], read_msg, BUFFER_SIZE);
        close(fd[READ_END]);

        for (int s=0; read_msg[s] != '\0'; s++){
            if (read_msg[s] < 97){ 
                read_msg_flip[s] = read_msg[s] + 32; 
            }
            else
            { 
                read_msg_flip[s] = read_msg[s] - 32; 
            }
        }

        write(fd2[WRITE_END], read_msg_flip, strlen(read_msg_flip)+1 );
        close(fd2[WRITE_END]);
    }

    if (pid > 0){
        read(fd2[READ_END], read_msg_flip, BUFFER_SIZE);
        printf("Result: %s", read_msg_flip);
        close(fd2[READ_END]);
    }

    return 0;
}