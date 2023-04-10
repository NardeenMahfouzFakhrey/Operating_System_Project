package com.example.operating_system_project;

    public interface Scheduling_Algorithm {
        public void schedule(int start_time);   //schedule: Compute completion and waiting time for all processes that have arrival time equal or greater than start_time
        public void add_process(Process p);     //adding a process in real time, then we will call schedule again with (p.at)
        public int compute_avgwt();             //It computes average waiting time after scheduling
        public int compute_avgta();             //It computes average turn around time after scheduling
        public Burst[] getSortedBursts();       //for GUI
    }


