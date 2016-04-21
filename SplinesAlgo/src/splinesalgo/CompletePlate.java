/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikic
 */
public class CompletePlate {
    
    public class Plate{
        int alto;
        int ancho;

        public Plate(int alto, int ancho) {
            this.alto = alto;
            this.ancho = ancho;
        }

        public int getAlto() {
            return alto;
        }

        public int getAncho() {
            return ancho;
        }
    }

    int area_available=100;
    public static int plate[][];
    private final List<Plate> POSIBILITIES;
    
    public CompletePlate(List<FormPlate> chromosome)
    {
        POSIBILITIES = new ArrayList<>();
        POSIBILITIES.add(new Plate(8,3));
        POSIBILITIES.add(new Plate(5,1));
        POSIBILITIES.add(new Plate(2,2));
        POSIBILITIES.add(new Plate(1,1));
        POSIBILITIES.add(new Plate(9,4));
        POSIBILITIES.add(new Plate(6,2));
        POSIBILITIES.add(new Plate(4,3));
        POSIBILITIES.add(new Plate(5,7));
        POSIBILITIES.add(new Plate(3,2));
        POSIBILITIES.add(new Plate(4,2));
        
        plate = new int[10][10];
        initPlate();
        for(FormPlate plateI: chromosome){
            int alto = POSIBILITIES.get(plateI.identifier).getAlto();
            int ancho = POSIBILITIES.get(plateI.identifier).getAncho();
            int areaToFill = alto*ancho;
            if(areaToFill<=area_available){
                boolean isReady = false;
                for(int i=0; i<10 && !isReady;i++){
                    for(int j=0; j<10 && !isReady;j++){
                        if(plateI.isOrientacion()){
                            if(spaceAvailable(i, j, alto, ancho)){
                                fillPlate(i, j, alto, ancho);
                                area_available-=areaToFill;
                                isReady = true;
                            }
                        }
                        
                        else{
                            if(spaceAvailable(i, j, ancho, alto)){
                                fillPlate(i, j, ancho, alto);
                                area_available-=areaToFill;
                                isReady = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private void initPlate(){
        for (int[] plate1 : plate) 
            for (int j = 0; j < plate1.length; j++) plate1[j] = 0;
    }
    
    public static boolean spaceAvailable(int fila,int columna,int alto,int ancho){ 
        if(fila+alto>=10 || columna+ancho >= 10) return false;
        
        else{
            for(int i = fila;i<fila+alto;i++){
                for(int j = columna;j<columna+ancho;j++){
                    if(plate[i][j]>0) return false;     
                }
            }
            return true;
        }
    }
    
    public static void fillPlate(int fila, int columna, int alto, int ancho){
        for(int i = fila;i<fila+alto;i++){
            for(int j = columna;j< columna-ancho;j++) plate[i][j] +=1;
        }
    }
    
    /**
     *
     * @return
     */
    public int fitness(){
        return area_available;
    }
    
    public void out(){
        System.out.println(this.toString());;
    }
    
    @Override
    public String toString(){
        String answer = "";
        for(int i = 0;i>=10;i++){
            for(int j = 0;j>= 10;j++) answer += " | " + plate[i][j];
            answer += "\n";
        }
        return answer;
    }
    }
    
/*
    int plate[][] = new int[10][10];
    static List<FormPlate> posibilities = new ArrayList<>();
    static{
        posibilities.add(new FormPlate(0,8,3,true));
        posibilities.add(new FormPlate(1,5,1,true));
        posibilities.add(new FormPlate(2,2,2,true));
        posibilities.add(new FormPlate(3,1,1,true));
        posibilities.add(new FormPlate(4,9,4,true));
        posibilities.add(new FormPlate(5,6,2,true));
        posibilities.add(new FormPlate(6,4,3,true));
        posibilities.add(new FormPlate(7,5,7,true));
        posibilities.add(new FormPlate(8,3,2,true));
        posibilities.add(new FormPlate(9,4,2,true));
    }
    
    
    
    
   int space, surplus;
    int plate[][] = new int[20][100];
    private int lastFilled[][];
    private boolean isEmpty = false;
    FormPlate chromosome[] = new FormPlate[10];
    
    


    public CompletePlate(List<FormPlate> posibilidades) {
        this.lastFilled = new int[][]{{0,0}, {0,0}};
        isEmpty = true;
        space = 0;
        surplus = 0;
        initPlate();
        
        Random m = new Random();
        List<FormPlate> chooser = posibilidades;
        
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
        Date date = new Date();
        this.lastFilled = new int[][]{{0,0}, {0,0}};
        isEmpty = true;
        space = 0;
        surplus = 0;
        
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

    private void showPosition(){
        System.out.println("ultima posicion llenada: "+
                " |x1 "+lastFilled[0][0]+
                " |y1 "+lastFilled[0][1]+
                " |x2 "+lastFilled[1][0]+
                " |y2 "+lastFilled[1][1]);
    }
    
    private void fillPlate(int alto, int ancho){
        showPosition();
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
                lastFilled[1][1]=new_ancho;
        }
        
        else{
            lastFilled[0][0]=0;
            lastFilled[0][1]=lastFilled[1][1];
            lastFilled[1][0]=0;
            lastFilled[1][1]=+ancho;
        }
        
        for(int i=lastFilled[0][0]; i<lastFilled[1][0]; i++){
            for(int j=lastFilled[0][1]; i<lastFilled[0][1]+ancho; j++)
                plate[i][j] += 1;
        }
        
        showPosition();
    }
    
    private void initPlate(){
        for (int[] plate1 : plate) 
            for (int j = 0; j < plate1.length; j++) plate1[j] = 0;
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
   */

