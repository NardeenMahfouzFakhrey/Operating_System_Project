package com.example.os_project;

import java.util.ArrayList;

public abstract class Algorithm {

    private int pn;                      //Processes Number
    public ArrayList<Process> ps;       //Process ArrayList
    private ArrayList<Burst> bs;         //Bursts ArrayList
    private float avgwt;                   //Average Waiting Time
    private float avgta;                   //Average Turn Around Time
    private boolean isComplete = false;

    public Algorithm(int pn, ArrayList<Process> ps){
        this.pn = pn;
        this.ps = ps;
    }

    /*
     * Function "schedule":
     * Compute completion and waiting time for all processes which have remaining time "rt" != 0
     * Fill up the burst ArrayList "bs"
     * Set isComplete flag
     */
    public abstract ArrayList<Burst> schedule(String algo_Type);

    /*
     * Function "add_process":
     * Adds a process in real time, then we will call schedule again with (p.at)
     * unSet isComplete flag
     */
    public void add_process(Process p){
        isComplete = false;
        ps.add(p);
    };

//    public ArrayList<Burst> getBs() {               /*Called only after scheduling*/
//        if(isComplete)
//            return bs;
//        else
//            return null;
//    }

    /*
     * Function "compute_avgwt":
     * Called after schedule function
     * It computes average waiting time after scheduling
     */
    public abstract float compute_avgwt();

    public float getAvgwt() {                         /*Called only after Compute_avgwt*/
        return avgwt;
    }

    /*
     * Function "compute_avgta":
     * Called after schedule function
     * It computes average turn around time after scheduling
     */
    public abstract float compute_avgta();

    public float getAvgta() {                         /*Called only after Compute_avgwt*/
        return avgta;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }
}