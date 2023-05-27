/**
 * How to make a TLB. I.e., an array each of whose elements is a (logical_page, physical_page) pair
 */

#include <stdio.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define TLB_SIZE 16

//A struct is like an abstract data type (i.e. class) with no methods. Just instance variables.
struct tlbentry {
    unsigned char logical;
    unsigned char physical;
};

// TLB is kept track of as a circular array, with the oldest element being overwritten once the TLB is full.
struct tlbentry tlb[TLB_SIZE];
// number of inserts into TLB that have been completed. Use as tlbindex % TLB_SIZE for the index of the next TLB line to use.
int tlbindex = 0;

int max(int a, int b)
{
    if (a > b)
        return a;
    return b;
}

//Traverses the TLB
void walk_tlb() {
    int i;
    for (i = max((tlbindex - TLB_SIZE), 0); i < tlbindex; i++) {
        struct tlbentry *entry = &tlb[i % TLB_SIZE];
        printf("(%d, %d)\n", entry->logical,entry->physical);
    }

}
//Adds a pair to the TLB
void add_to_tlb(unsigned char logical, unsigned char physical) {
    struct tlbentry *entry = &tlb[tlbindex % TLB_SIZE];
    tlbindex++;
    entry->logical = logical;
    entry->physical = physical;
}

int main(int argc, const char *argv[])
{         
    add_to_tlb(1,2);//a random pair of (logical_page, physical_page)
    add_to_tlb(3,4);//a random pair of (logical_page, physical_page)
    add_to_tlb(5,6);//a random pair of (logical_page, physical_page)
    walk_tlb();
    
    return 0;
}
