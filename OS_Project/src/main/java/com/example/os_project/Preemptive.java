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
        this.process= (ArrayList<Process>) ps.clone();
        this.number_of_process=pn;
    }


    @Override
    public ArrayList<Burst> schedule(String algoType) {
        int min;
        int current_time = 0;
        int count = 1;
        boolean flag;
        Burst b;
        int process_ID = 0;
        ArrayList<Integer> rt= new ArrayList<>();
        ArrayList<Burst> result = new ArrayList<>();


        for (int i=0; i<process.size() ;i++){
            rt.add(process.get(i).getBt());
        }

        while (process.size() > 0){
            min = Integer.MAX_VALUE;
            for (int i=0; i < process.size(); i++){
                flag = (algoType == "SJF Preemptive") ? (rt.get(i) < min) : (process.get(i).getPriority() < min);
                if(process.get(i).getAr() <= current_time && flag){
                    min = (algoType == "SJF Preemptive") ? (rt.get(i)) : (process.get(i).getPriority());
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

        for (int i=0 ; i<burst.size() ; i++ ){
            int cur = burst.get(i).getP().getPid();
            for(int j=i+1 ; j<burst.size() ; j++){
                if (cur == burst.get(j).getP().getPid() ){
                    count = count + 1;
                    i=i+1;
                    if (j == burst.size()-1){
                        result.add(new Burst(burst.get(i).getP(),count));
                    }
                }else {
                    result.add(new Burst(burst.get(i).getP(),count));
                    count = 1;
                    break;
                }
            }
        }

        avg_turnaround_time = avg_turnaround_time / number_of_process;
        avg_waiting_time = avg_waiting_time / number_of_process;
        return result;
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
