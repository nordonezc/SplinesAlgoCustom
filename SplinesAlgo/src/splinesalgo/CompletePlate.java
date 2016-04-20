/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author nikic
 */
public class CompletePlate {
    int space, surplus;
    int plate[][] = new int[20][20];
    private int lastFilled[][];
    private boolean isEmpty = false;
    FormPlate chromosome[] = new FormPlate[10];

    public CompletePlate(FormPlate[] posibilidades) {
        this.lastFilled = new int[][]{{0,0}, {0,0}};
        isEmpty = true;
        space = 0;
        surplus = 0;
        initPlate();
        
        Random m = new Random();
        ArrayList<FormPlate> chooser = new ArrayList<>();
        chooser.addAll(Arrays.asList(posibilidades));
        
        for(int i=0; chooser.size()>0; i++){
            int seleccion = m.nextInt(chooser.size());
            chromosome[i] = chooser.get(seleccion);
            chromosome[i].setOrientacion(m.nextBoolean());
            chooser.remove(i);
        }
        
        for (FormPlate chromosome1 : chromosome) {
            if (chromosome1.isOrientacion()) 
                fillPlate(chromosome1.getAncho(), chromosome1.getAlto());
            else
                fillPlate(chromosome1.getAlto(), chromosome1.getAncho()); 
        }
        initSpace();
    }
    
    public CompletePlate(FormPlate[] candidate, int knows) {
        this.lastFilled = new int[][]{{0,0}, {0,0}};
        isEmpty = true;
        space = 0;
        surplus = 0;
        initPlate();
        
        for(int i=0; i<candidate.length; i++){
            chromosome[i] = candidate[i];
        }
        
        for (FormPlate chromosome1 : chromosome) {
            if (chromosome1.isOrientacion()) 
                fillPlate(chromosome1.getAncho(), chromosome1.getAlto());
            else
                fillPlate(chromosome1.getAlto(), chromosome1.getAncho()); 
        }
        initSpace();
    }

    private void fillPlate(int alto, int ancho){
        if(isEmpty == true){
            isEmpty = false;
            lastFilled[0][0]=0;
            lastFilled[0][1]=0;
            lastFilled[1][0]=lastFilled[0][0]+alto;
            lastFilled[1][1]=lastFilled[0][1]+ancho;
        }
        
        if(lastFilled[1][0]<9){
            int new_ancho = lastFilled[0][1]+ancho;
            lastFilled[0][0]=lastFilled[1][0];
            lastFilled[0][1]=lastFilled[0][1];
            lastFilled[1][0]=+alto;
            if(new_ancho > lastFilled[1][1])
                lastFilled[1][1]=lastFilled[0][1]+ancho;
        }
        
        else{
            lastFilled[0][0]=0;
            lastFilled[0][1]=lastFilled[1][1];
            lastFilled[1][0]=0;
            lastFilled[1][1]=+ancho;
        }
        
        for(int i=lastFilled[1][0]; i<lastFilled[1][0]+alto; i++){
            for(int j=lastFilled[0][1]; i<lastFilled[0][1]+ancho; j++)
                plate[i][j] += 1;
        }
    }
    
    private void initPlate(){
        for (int[] plate1 : plate) 
            for (int j = 0; j < plate1.length; j++) plate1[j] += 0;
    }
    
    private void initSpace(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(plate[i][j]!=1)
                    space++;
            }
        }

        for(int i=10; i<20; i++){
            for(int j=10; j<20; j++){
                if(plate[i][j]>1)
                    surplus+=1;
            }
        }
    }
    
    public int getSpace() {
        return space;
    }

    public int[][] getPlate() {
        return plate;
    }

    public FormPlate[] getChromosome() {
        return chromosome;
    }

    @Override
    public String toString() {
        return "CompletePlate{" + "plate=" + Arrays.toString(plate) + ", chromosome=" + Arrays.toString(chromosome) + '}';
    }
    
    public String candidateToString(){
        return surplus + " " + space;
    }
   
}
