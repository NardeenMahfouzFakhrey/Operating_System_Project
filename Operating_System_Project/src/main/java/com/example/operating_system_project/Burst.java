package com.example.operating_system_project;

public class Burst {
    private int pid;     //Process ID
    private int st;      //Starting TIme
    private int ft;     //Finishing Time

    public Burst(int pid, int st, int ft){
        this.pid = pid;
        this.st = st;
        this.ft = ft;
    }

    public int getPid() {
        return pid;
    }

    public int getSt() {
        return st;
    }

    public int getFt() {
        return ft;
    }
}
