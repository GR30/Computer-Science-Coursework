/**
 * decompose a 32 bit integers (i.e., an address) into 4 bytes. Then take the second byte from the right (i.e., your  virtual page number)
 */

//These are the includes that we will need for the entire program
#include <stdio.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define PAGE_MASK 255
#define OFFSET_BITS 8
#define OFFSET_MASK 255

int main(int argc, const char *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage ./TextFileToArray BACKING_STORE.bin addresses.txt\n");
        exit(1);
    }
    
    int logical_address = 2135;//Some integer that we pulled off addresses.txt
    int offset = logical_address & OFFSET_MASK;
    int logical_page = (logical_address >> OFFSET_BITS) & PAGE_MASK;
     
    printf("The virtual page number for this address is %d\n", logical_page);
    printf("The offset for this address is %d\n", offset);
    return 0;
}
