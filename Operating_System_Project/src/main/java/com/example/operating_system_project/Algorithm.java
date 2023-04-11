package com.example.operating_system_project;
import java.util.ArrayList;

public abstract class Algorithm {

    private int pn;                      //processes number
    private ArrayList<Process> ps;       //process array
    private ArrayList<Burst> bs;
    private int avgwt;                   //Average waiting time
    private int avgta;                   //Average turn around time
    private boolean isComplete = false;

    public Algorithm(int pn, ArrayList<Process> ps){
        this.pn = pn;
        this.ps = ps;
    }

    /*
    * Function "schedule":
    * Compute completion and waiting time for all uncompleted processes at "start_time"
    * Fill up the burst ArrayList "bs"
    * Set isComplete flag
    */
    public abstract void schedule(int start_time);

    /*
     * Function "add_process":
     * Adds a process in real time, then we will call schedule again with (p.at)
     * unSet isComplete flag
     */
    public void add_process(Process p){
        isComplete = false;
        ps.add(p);
    };

    public ArrayList<Burst> getBs() {               /*Called only after scheduling*/
        if(isComplete)
            return bs;
        else
            return null;
    }

    /*
    * Function "compute_avgwt":
    * Called after schedule function
    * It computes average waiting time after scheduling
     */
    public abstract void compute_avgwt();

    public int getAvgwt() {                         /*Called only after Compute_avgwt*/
        return avgwt;
    }

    /*
     * Function "compute_avgta":
     * Called after schedule function
     * It computes average turn around time after scheduling
     */
    public abstract void compute_avgta();

    public int getAvgta() {                         /*Called only after Compute_avgwt*/
        return avgta;
    }

    public int getPn() {
        return pn;
    }
}
