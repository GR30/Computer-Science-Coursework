p
public class memory_manager {
//Our virtual memory has room for 16 pages, numbered 0 thru 15
    static int[] VIRTUAL_MEMORY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//Our physical memory (RAM) has room for 4 pages. -1 means that the frame is unmapped.
    static int[] PHYSICAL_MEMORY = {-1, -1, -1, -1};
//Each entry at index i in the following array will hold the clock tick 
//at which the frame at index i was mapped to a new virtual page.
    static int[] LOAD = {0, 0, 0, 0};
//Each entry at index i in the following array will hold the amount of time 
//that the frame at index 'i' has spent holding the virtual page that it is currently mapped to.
    static int[] TIME_IN_RAM = {0, 0, 0, 0};
//Each entry at index i in the following array will hold the number of times that 
//the virtual page currently held by the frame at index 'i' 
//was referenced.
    static int[] REFERENCES = {0, 0, 0, 0};
//initialize your page requests
    static int[] page_requests = {1, 1, 3, 1, 7, 3, 1, 0, 8, 0, 12, 6};

    public static void main(String[] args) {
        memory_manager m = new memory_manager();
        m.Go();
    }
//This method handles page faults. Takes as input the number of the offending virtual page
//as well as the current clock tick.
//The current implementation of page_fault simply replaces the page at location 0 in RAM.
    static void page_fault(int page_requested, int clock_tick) {
        System.out.println("YOU HAVE JUST REFERENCED VIRTUAL PAGE " + page_requested + ". NOT IN RAM-->PAGE FAULT!");
        System.out.println("LOADING PAGE " + page_requested + "...");
        PHYSICAL_MEMORY[0] = page_requested;
        LOAD[0] = clock_tick;//timestamp it

        TIME_IN_RAM[0] = 0;//time in RAM for this fresh page is 0

        REFERENCES[0] = 0;//reference count for this fresh page is 0
    }
//This method runs our simulation.
    public static void Go() {
        System.out.println("\n\nSTARTING THE SIMULATOR....\n\n");
        System.out.println(ram_contents());
//At each clock tick issue a page request
        for (int clock_tick = 0; clock_tick < page_requests.length; clock_tick++) {
//Just in case the referenced virtual page does not exist.
            if (page_requests[clock_tick] < 0 || page_requests[clock_tick] >= VIRTUAL_MEMORY.length) {
                System.out.println("FATAL ERROR!! \nTHE REFERENCED VIRTUAL PAGE (Page " + page_requests[clock_tick] +
                        ") DOES NOT EXIST.\nEXITING THE SIMULATOR.");
                System.exit(-1);//the program exits if the requested virtual page does not exist.

            }
//load the requested page. clock_tick is the current timestamp
            int mapped = load_page(page_requests[clock_tick], clock_tick);
//if no page faults occured, then update the reference count for this page 
// and then output the new contents of RAM.
            if (mapped >= 0) {
                REFERENCES[mapped]++;
                System.out.println(ram_contents());
            } else {//PAGE FAULT!

                page_fault(page_requests[clock_tick], clock_tick);
                System.out.println(ram_contents());
            }
            update_time_stamps();
        }
    }
//At each clock tick, this method will be called to update 
//the timestamps of the virtual pages that are currently in RAM 
    static void update_time_stamps() {
        for (int i = 0; i < PHYSICAL_MEMORY.length; i++) {
//If the current page is mapped, update its timestamp.
            if (PHYSICAL_MEMORY[i] != -1) {
                TIME_IN_RAM[i]++;
            }
        }
    }
//This method returns the index of the physical page holding the requested
//virtual page.
//returns -1 if the requested virtual page is unmapped.
    static int load_page(int page_requested, int clock_tick) {
        for (int i = 0; i < PHYSICAL_MEMORY.length; i++) {
//If the requested page is already in RAM. just return.
            if (PHYSICAL_MEMORY[i] == page_requested) {
                System.out.println("YOU HAVE JUST REFERENCED VIRTUAL PAGE " + page_requested + ". THIS PAGE IS ALREADY IN RAM.");
                return i;
            }
        }
//If there is an empty frame in RAM, place the page_requested there. 
        for (int i = 0; i < PHYSICAL_MEMORY.length; i++) {
            if (PHYSICAL_MEMORY[i] == -1) {
                System.out.println("YOU HAVE JUST REFERENCED VIRTUAL PAGE " + page_requested + ". NOT IN RAM-->PAGE FAULT!");
                System.out.println("LOADING PAGE " + page_requested + "...");
                PHYSICAL_MEMORY[i] = page_requested;
                LOAD[i] = clock_tick;//timestamp it

                TIME_IN_RAM[i] = 0;//time in RAM for this fresh page is 0

                REFERENCES[i] = 0;//reference count for this fresh page is 0

                return i;
            }
        }
        return -1;//No empty frames found! RAM is full!

    }
//This method returns true if and only if the page being requested is already in RAM. 
    static boolean is_in_ram(int page_requested) {
        for (int i = 0; i < PHYSICAL_MEMORY.length; i++) {
            if (PHYSICAL_MEMORY[i] == page_requested) {
                return true;
            }
        }
        return false;
    }
//This method returns a string description of the current content of RAM (page table):
//1. Virtual pages currently in RAM
//2. When was the page loaded
//3. How many times was the page referenced
//4. How long has the page been in RAM 
    static String ram_contents() {
        String ram_content = "VIRTUAL PAGES NOW IN RAM:\n";
        for (int i = 0; i < PHYSICAL_MEMORY.length; i++) {
            if (PHYSICAL_MEMORY[i] >= 0) {
                ram_content += "P" + PHYSICAL_MEMORY[i] + "\t";
            } else {
                ram_content += "--\t";
            }
        }
        ram_content += "<-- Virtual Page Number ('--' if the frame is unmapped)\n";
        for (int i = 0; i < LOAD.length; i++) {
            ram_content += LOAD[i] + "\t";
        }
        ram_content += "<-- When was this page loaded? (0th clock tick, 1st clock tick, 2nd clock tick, etc.)\n";
        for (int i = 0; i < REFERENCES.length; i++) {
            ram_content += REFERENCES[i] + "\t";
        }
        ram_content += "<-- How many times has this page been referenced?\n";
        for (int i = 0; i < TIME_IN_RAM.length; i++) {
            ram_content += TIME_IN_RAM[i] + "\t";
        }
        ram_content += "<-- How long has this page been in RAM? (# of clock ticks since the page was loaded.)\n\n";
        return ram_content;
    }
}
