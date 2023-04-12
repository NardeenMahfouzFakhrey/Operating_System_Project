package com.example.os_project;

import java.util.ArrayList;


public class Preemptive extends Algorithm {
    ArrayList<Burst> burst = new ArrayList<Burst>();
    ArrayList<Process> process;
    int number_of_process;
    float avg_waiting_time = 0;
    float avg_turnaround_time = 0;

    public Preemptive(int pn, ArrayList<Process> ps) {
        super(pn, ps);
        this.process=ps;
        this.number_of_process=pn;
    }


    @Override
    public ArrayList<Burst> schedule(String algoType) {
        int min;
        int current_time = 0;
        boolean flag;
        Burst b;
        int process_ID = 0;
        ArrayList<Integer> rt= new ArrayList<>();

        for (int i=0; i<process.size() ;i++){
            rt.add(process.get(i).getBt());
        }

        while (process.size() > 0){
            min = Integer.MAX_VALUE;

            for (int i=0; i < process.size(); i++){
                flag = (algoType == "s") ? (rt.get(i) < min) : (process.get(i).getPriority() < min);

                if(process.get(i).getAr() <= current_time && flag){
                    min = (algoType == "s") ? (rt.get(i)) : (process.get(i).getPriority());
                    process_ID = i;
                }
            }

            b = new Burst(process.get(process_ID),1);
            burst.add(b);
            current_time = current_time + 1;
            rt.set(process_ID,rt.get(process_ID) - 1);

            if (rt.get(process_ID) == 0){
                avg_turnaround_time += current_time-process.get(process_ID).getAr();
                avg_waiting_time += current_time - process.get(process_ID).getAr() - process.get(process_ID).getBt();
                process.remove(process.get(process_ID));
                rt.remove(process_ID);
            }

        }

        avg_turnaround_time = avg_turnaround_time / number_of_process;
        avg_waiting_time = avg_waiting_time / number_of_process;
        return burst;
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
