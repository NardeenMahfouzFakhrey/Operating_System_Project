package com.example.os_project;

import java.util.ArrayList;

public class FCFS extends  Algorithm{

    ArrayList<Burst> burst = new ArrayList<Burst>();
    ArrayList<Process> process;
    int number_of_process;
    float avg_waiting_time = 0;
    float avg_turnaround_time = 0;

    public FCFS(int pn, ArrayList<Process> ps) {
        super(pn, ps);
        this.process= (ArrayList<Process>) ps.clone();
        this.number_of_process=pn;
    }

    @Override
    public ArrayList<Burst> schedule(String algo_Type){
        int current_time=0;
        for (int i=0 ; i<number_of_process ; i++) {
            Process p = getFirstArrival(process);
            Burst b = new Burst(p,p.getBt());
            burst.add(b);
            avg_waiting_time += current_time-p.getAr();
            current_time = current_time + p.getBt();
            avg_turnaround_time += current_time-p.getAr();
            process.remove(p);
        }
        avg_turnaround_time=avg_turnaround_time/number_of_process;
        avg_waiting_time=avg_waiting_time/number_of_process;
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

    public Process getFirstArrival(ArrayList<Process> processes) {
        int min= Integer.MAX_VALUE;
        Process minProcess=null;
        for(int i=0 ; i< processes.size() ; i++) {

            if(processes.get(i).getAr() < min ){
                min=processes.get(i).getAr();
                minProcess=processes.get(i);
            }
        }
        return minProcess;
    }

}
