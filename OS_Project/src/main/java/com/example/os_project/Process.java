package com.example.os_project;

public class Process {
    private int pid;            //Process ID
    private int priority;       //Process Priority
    private int ar;             //Arrival Time
    private int bt;             //Burst Time
    private int ct;             //Completion Time
    private int rt;             //Remaining Time
    private int ta;             //Turn Around Time
    private int wt;             //Waiting Time
    public String color;

    public Process(int pid, int ar, int bt){
        this.pid = pid;
        this.ar = ar;
        this.bt = bt;
        this.rt = bt;
        this.priority = 0;
    }

    public Process(int pid, int ar, int bt, int priority){
        this.pid = pid;
        this.ar = ar;
        this.bt = bt;
        this.rt = bt;
        this.priority = priority;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getTa() {
        return ta;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public int getPid() {
        return pid;
    }

    public int getPriority() {
        return priority;
    }

    public int getAr() {
        return ar;
    }

    public int getBt() {
        return bt;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public void decrementRt(){
        rt--;
    }
}