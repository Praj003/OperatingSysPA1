package org.example;
//importing random num dep
import java.util.Random;

class userMemAllocation {
    // pages are 160mb, the mem is in 100 pages, starting adress is 2000
    private static final int page_size = 160;
    private static final int total_pages = 100;
    private static final int starting_address = 2000;

    // track IDs from memory and the next address
    private static final int[] mem = new int[total_pages];
    private static int curr_address = starting_address;

    public static void main(String[] args) {
        allocate_memory(); // calling allocate_memory
    }


    public static void allocate_memory() {
        Random rand = new Random();
        int process_id = 1;
        // printing titles
        System.out.printf("%-15s %-25s %-25s %-15s\n", "Process Id", "Starting Memory Address", "Size of the Process mb", "Unused Space mb");

        while (true) {
            int num_pages = rand.nextInt(30) + 1; // random pages from 1-30
            int process_size = num_pages * 80; // pages have 80MB
            int pages_needed = (int) Math.ceil((double) process_size / page_size); // memory pages
            int total_memory_mb = pages_needed * page_size;
            int unused_space = total_memory_mb - process_size;

            int free_pages = 0;// checks memory / ends process
            for (int page : mem) {
                if (page == 0) free_pages++;}
            if (free_pages < pages_needed) {
                break;
            }

            int allocated = 0; // fills memory if not full
            for (int i = 0; i < total_pages && allocated < pages_needed; i++) {
                if (mem[i] == 0) {
                    mem[i] = process_id;
                    allocated++;
                }
            }
            // printing digits
            System.out.printf("%-15s %-25s %-25s %-15s\n", process_id, curr_address, process_size, unused_space);
            curr_address += total_memory_mb; // goes into memory
            process_id++;
        }
    }
}
