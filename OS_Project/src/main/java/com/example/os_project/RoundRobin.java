package com.example.os_project;

import java.util.ArrayList;

public class RoundRobin extends Algorithm {

    float avg_waiting_time = 0;
    float avg_turnaround_time = 0;
    public RoundRobin(int pn, ArrayList<Process> ps) {
        super(pn, ps);
    }

    @Override
    public ArrayList<Burst> schedule(String algo_Type){

        return null;
    }

    @Override
    public float compute_avgwt() {
        return avg_waiting_time;
    }

    @Override
    public float compute_avgta() {
        return avg_turnaround_time;
    }
}
