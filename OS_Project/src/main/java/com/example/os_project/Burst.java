package com.example.os_project;

public class Burst {
    private int pid;     //Process id
    private int qt;      //Quantum Time

    public Burst(int pid, int qt){
        this.pid = pid;
        this.qt = qt;
    }

    public int getPid() {
        return pid;
    }

    public int getQt() {
        return qt;
    }
}