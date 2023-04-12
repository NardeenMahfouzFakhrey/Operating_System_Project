package com.example.operating_system_project;

public class Burst {
    private Process p;     //Process
    private int qt;      //Quantum Time

    public Burst(Process p, int qt){
        this.p = p;
        this.qt = qt;
    }

    public Process getP() {
        return p;
    }

    public int getQt() {
        return qt;
    }
}
