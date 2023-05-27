/**
 * Convert a virtual address to a physical address
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

#define PAGE_MASK 255
#define PAGE_SIZE 256

#define OFFSET_BITS 8
#define OFFSET_MASK 255

#define MEMORY_SIZE PAGES * PAGE_SIZE

// pagetable[logical_page] is the physical page number for logical page. Value is -1 if that logical page isn't yet in the table.
int pagetable[PAGES];

int main(int argc, const char *argv[])
{
    if (argc != 3) {
        fprintf(stderr, "Usage ./TextFileToArray BACKING_STORE.bin addresses.txt\n");
        exit(1);
    }
    
      
    // Fill page table entries with -1 for initially empty table.
    int i;
    for (i = 0; i < PAGES; i++) {
        pagetable[i] = -1;
    }
     
    
        int logical_address = 2135;//Some integer that we pulled off addresses.txt
        int offset = logical_address & OFFSET_MASK;
        int logical_page = (logical_address >> OFFSET_BITS) & PAGE_MASK;
        
        printf("The virtual page number for this address is %d\n", logical_page);
        printf("The offset for this address is %d\n", offset);

        int physical_page = 8;
       //8 is some random number. When doing the simulation we should try physical_page = pagetable[logical_page]
       //If pagetable[logical_page] is -1, we should not do the assignment, we should instead look in the backing store
        //Now that we know what the physical page number is, we compute the physical address by appending the offset
        int physical_address = (physical_page << OFFSET_BITS) | offset;
        
        printf("The corresponding physical address is: %d\n", physical_address);
    
    return 0;
}
