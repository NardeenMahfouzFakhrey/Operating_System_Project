package com.example.os_project;

import java.util.ArrayList;

public class Non_Preemptive extends Algorithm {
    ArrayList<Burst> burst = new ArrayList<Burst>();
    ArrayList<Process> process;
    int number_of_process;
    float avg_waiting_time = 0;
    float avg_turnaround_time = 0;

    public Non_Preemptive(int pn, ArrayList<Process> ps) {
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

        for (int i=0; i<number_of_process ;i++){
            if ( (process.get(i).getRt() < process.get(i).getBt()) && (process.get(i).getRt() != 0)){
                burst.add(new Burst(process.get(i),process.get(i).getRt()));
                process.get(i).setRt(0);

            }
        }


        for (int i=0; i<number_of_process ;i++){
            if (process.get(i).getRt() == 0){
                process.remove(process.get(i));
            }
        }


        while (process.size() > 0){
            min = Integer.MAX_VALUE;

            for (int i=0; i < process.size(); i++){
                flag = (algoType == "s") ? (process.get(i).getBt() < min) : (process.get(i).getPriority() < min);

                if(process.get(i).getAr() <= current_time && flag){
                    min = (algoType == "s") ? (process.get(i).getBt()) : (process.get(i).getPriority());
                    process_ID = i;
                }
            }

            b = new Burst(process.get(process_ID),process.get(process_ID).getBt());
            burst.add(b);
            avg_waiting_time += current_time-process.get(process_ID).getAr();
            current_time = current_time + process.get(process_ID).getBt();
            avg_turnaround_time += current_time-process.get(process_ID).getAr();
            process.remove(process.get(process_ID));

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
