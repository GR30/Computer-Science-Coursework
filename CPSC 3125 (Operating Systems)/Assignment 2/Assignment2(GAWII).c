/*
Error:
undefined reference to `pthread_create'
Error:
undefined reference to `pthread_join'

We eliminate these errors by compling the program with the following command line
gcc -pthread -o BasicPthread BasicPthread.c

Error:
warning: implicit declaration of function ‘atoi’

#include<stdlib.h>

To run the program (with input 8):
./BasicPthread 8
*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int available[3];
int max[5][3];
int allocated[5][3];
int need[5][3];
int finish[5] = {0, 0, 0, 0, 0};  

void *runner(void *id); //The child thread will run this function
int main(int argc, char *argv[]);
int request(int id, int req[]);

pthread_mutex_t mutex;


//When we run this program at the command prompt in Linux
//We pass it an argument to start with
//For example 
//Compile 
//$ gcc -pthread -o BasicPthread BasicPthread.c
//Run
//./BasicPthread 8 
//To find the sum of all the numbers between 1 and 8

int release(int id, int release[]){
    int r[3];
    r[0] = release[0];
    r[1] = release[1];
    r[2] = release[2];

    printf("\nprocess number: %d", id);
    printf("\nIn release function:");
    printf("Released: [%d, %d, %d]", release[0],release[1],release[2]);
    
    allocated[id][0] = allocated[id][0] - r[0];
    allocated[id][1] = allocated[id][1] - r[1];
    allocated[id][2] = allocated[id][2] - r[2];

    available[0] = available[0] + r[0];
    available[1] = available[1] + r[1];
    available[2] = available[2] + r[2];

    printf("Allocated: [%d, %d, %d]", *allocated[0],*allocated[1],*allocated[2]);
    printf("Available: [%d, %d, %d]", available[0],available[1],available[2]);
    
    finish[id] = 1;
	
	return 0;
}

int request(int id, int req[]){
    if ( (req[0] > available[0]) || (req[1] > available[1]) || (req[2] > available[2]) ){
        printf("Resource(s) Unavalable");
        return -1;
    }else{
        available[0] = available[0] - req[0];
        available[1] = available[1] - req[1];
        available[2] = available[2] - req[2];
        
        allocated[id][0] = req[0] + allocated[id][0];
        allocated[id][1] = req[1] + allocated[id][1];
        allocated[id][2] = req[2] + allocated[id][2];

        need[id][0] = need[id][0] - req[0];
        need[id][1] = need[id][1] - req[1];
        need[id][2] = need[id][2] - req[2];

        printf("\nprocess number: %d", id);
        printf("\nIn request function:");
        printf("\nAvailable: [%d, %d, %d]", available[0], available[1], available[2]);
        printf("\nAllocated: [%d, %d, %d]", allocated[id][0], allocated[id][1], allocated[id][2]);
        printf("\nMax: [%d, %d, %d]", max[id][0], max[id][1], max[id][2]);
        printf("\nNeed: [%d, %d, %d]", need[id][0], need[id][1], need[id][2]);

        int *rel[3];
        rel[0] = allocated[id][0] - (rand() % allocated[id][0]);
        rel[1] = allocated[id][0] - (rand() % allocated[id][0]);
        rel[2] = allocated[id][0] - (rand() % allocated[id][0]);

        release(id, rel);
    }

    return 0;
}

void *runner(void *id){
    int i = (int)id;

    while(1){
        pthread_mutex_lock(&mutex);

        need[i][0] = rand() % ((max[i][0] - allocated[i][0]) + allocated[i][0]);
        need[i][1] = rand() % ((max[i][1] - allocated[i][1]) + allocated[i][1]);
        need[i][2] = rand() % ((max[i][2] - allocated[i][2]) + allocated[i][2]);
        
        printf("in runner:\nNeed: [%d, %d, %d]", need[i][0], need[i][1], need[i][2]);

        int success = request(i, need[i]);

        if (success == 0){ printf("Request Successful"); }
        else { printf("Request Denied"); }

        pthread_mutex_unlock(&mutex);
    }
    
    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
    //srand(time(NULL));

    int customerNum_1 = 0;
    int customerNum_2 = 1;
    int customerNum_3 = 2;
    int customerNum_4 = 3;
    int customerNum_5 = 4; 

    pthread_t tid_1, tid_2, tid_3, tid_4, tid_5;

    pthread_attr_t attr;
    pthread_attr_init(&attr);
    
    pthread_mutex_init(&mutex,NULL);

    if (argc != 4){
        printf("Invalid Input");
        return -1;
    }else{
        //while((finish[0] == 0) || (finish[1] == 0) || (finish[2] == 0) || (finish[3] == 0) || (finish[4] == 0)){
        for (int x = 0; x<2; x++){
            available[0] = atoi(argv[1]);
            available[1] = atoi(argv[2]);
            available[2] = atoi(argv[3]);

            for (int x=0; x<5; x++){
                for (int y=0; y<3; y++){
                    max[x][y] = rand()% available[y]+1;
                }
            }

            //runner(0);
            //Create a separate thread
            //This is like doing fork() to create a child process
            //tid will hold the id of the child thread
            //attr will hold the attributes of the child thread
            //runner is the function that the child thread must execute (below)
            //argv1[1] is the input to the child thread

            pthread_create(&tid_1, &attr, runner, (void *)customerNum_1);
            pthread_create(&tid_2, &attr, runner, (void *)customerNum_2);
            pthread_create(&tid_3, &attr, runner, (void *)customerNum_3);
            pthread_create(&tid_4, &attr, runner, (void *)customerNum_4);
            pthread_create(&tid_5, &attr, runner, (void *)customerNum_5);

            pthread_join(tid_1, NULL);
            pthread_join(tid_2, NULL);
            pthread_join(tid_3, NULL);
            pthread_join(tid_4, NULL);
            pthread_join(tid_5, NULL);
        }
    }

    pthread_mutex_destroy(&mutex);
}