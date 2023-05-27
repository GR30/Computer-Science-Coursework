/**
 * Read the Backing Store file
 * 
 */

//These are the includes that we will need for the entire program
#include <stdio.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define PAGES 256
#define PAGE_SIZE 256
#define MEMORY_SIZE PAGES * PAGE_SIZE

// Pointer to memory mapped backing file
signed char *backing;

int main(int argc, const char *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage ./TextFileToArray BACKING_STORE.bin addresses.txt\n");
        exit(1);
    }
    
    const char *backing_filename = argv[1]; 
    int backing_fd = open(backing_filename, O_RDONLY);
    backing = mmap(0, MEMORY_SIZE, PROT_READ, MAP_PRIVATE, backing_fd, 0); 
    
    printf("Done reading the entire backing store as an array of characters!\n");
    
    return 0;
}
