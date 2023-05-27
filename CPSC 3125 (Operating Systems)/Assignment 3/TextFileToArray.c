/**
 * Read the virtual addresses from addresses.txt
 */

//These are the includes that we will need for the entire program
#include <stdio.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

// Max number of characters per line of input file to read.
#define BUFFER_SIZE 10

int main(int argc, const char *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage ./TextFileToArray BACKING_STORE.bin addresses.txt\n");
        exit(1);
    }
       
    const char *input_filename = argv[2];
    FILE *input_fp = fopen(input_filename, "r");
    
    int total_addresses = 0;
    // Character buffer for reading lines of input file.
    char buffer[BUFFER_SIZE];
    
    while (fgets(buffer, BUFFER_SIZE, input_fp) != NULL) {
        total_addresses++;
        int logical_address = atoi(buffer);
        
        printf("Virtual address: %d\n", logical_address);
    }
    printf("Total addresses: %d\n", total_addresses);
    return 0;
}
