package com.gebvlad.algorythmgenerator;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        AlgorythmGenerator ag = new AlgorythmGenerator(5, 20, 10, 10);
        AlgTree alg = ag.createAlg();
        System.out.println();
        alg.printProgram();
        System.out.println();
        alg.printProgramToFile("C:\\AlgorythmGenerator\\");
        alg.printProgramXML();
        alg.printProgramXMLToFile("C:\\AlgorythmGenerator\\");
        LinkedList<Integer[][]> li = ag.getNativeAddressingMicroprogram(alg);
        ag.printMicroprogram(li);
        System.out.println();
        HashMap<Integer, Integer[][]> mp = ag.getForcedAddressingMicroprogram(alg);
        ag.printMicroprogram(mp);
    }
}
