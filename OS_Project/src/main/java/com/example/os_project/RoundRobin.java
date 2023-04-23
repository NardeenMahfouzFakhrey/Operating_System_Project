package com.example.os_project;

import java.util.ArrayList;

public class RoundRobin extends Algorithm {

    ArrayList<Burst> burst = new ArrayList<Burst>();
    ArrayList<Process> process=new ArrayList<>();
    int number_of_process;
    float avg_waiting_time = 0;
    float avg_turnaround_time = 0;
    int quantumTime;

    public RoundRobin(int pn, ArrayList<Process> ps, int quantumTime) {
        super(pn, ps);
        this.quantumTime=quantumTime;
        for(int i=0 ; i< ps.size() ; i++) {
            this.process.add((Process) ps.get(i).clone());
        }
        this.number_of_process=pn;
    }

    @Override
    public ArrayList<Burst> schedule(String algo_Type){
        int currentTime=0;
        int actualQuantum=quantumTime;
        boolean flag=false;
        while (process.size()>0) {
            for (int i = 0; i < process.size(); i++) {

                if (process.get(i).getAr() <= currentTime ) {
                    flag=true;
                    if (process.get(i).getRt() >= quantumTime) {

                        process.get(i).setRt(process.get(i).getRt() - quantumTime);
                        Burst b= new Burst(ps.get(i),quantumTime);
                        burst.add(b);

                        if (process.get(i).getRt() == 0) {
                            avg_waiting_time += currentTime- process.get(i).getAr() - process.get(i).getBt()+quantumTime;
                            avg_turnaround_time+=currentTime-process.get(i).getAr()+quantumTime;
                            process.remove(process.get(i));
                            i--;
                        }
                    }
                    else {
                        actualQuantum = process.get(i).getRt();
                        Burst b= new Burst(ps.get(i),actualQuantum);
                        burst.add(b);

                        avg_waiting_time += currentTime- process.get(i).getAr() - process.get(i).getBt()+actualQuantum;
                        avg_turnaround_time+=currentTime-process.get(i).getAr()+actualQuantum;

                        process.remove(process.get(i));
                        i--;
                    }
                    currentTime = currentTime + actualQuantum;
                    actualQuantum = quantumTime;
                }

            }
            if(flag==false){
                currentTime++;
            }
        }
        return burst;
    }

    @Override
    public float compute_avgwt() {
        return avg_waiting_time/number_of_process;
    }

    @Override
    public float compute_avgta() {
        return avg_turnaround_time/number_of_process;
    }
}

