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
            p.setWt( current_time-p.getAr());
            current_time = current_time + p.getBt();
            p.setTa(current_time-p.getAr());
            burst.add(b);
            process.remove(p);
        }
        return burst;
    }

    @Override
    public float compute_avgwt() {
     for(int i=0 ; i < number_of_process ; i++) {
         avg_waiting_time+=burst.get(i).getP().getWt();
     }
        return avg_waiting_time/number_of_process;
    }

    @Override
    public float compute_avgta() {
        for(int i=0 ; i < number_of_process ; i++) {
            avg_turnaround_time+=burst.get(i).getP().getTa();
        }
        return avg_turnaround_time/number_of_process;
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
