/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splinesalgo;

/**
 *
 * @author nikic
 */
public class FormPlate {
    int identifier;
    boolean orientacion;
    
    /**
     * 
     * @param numberOfPiece numero de la pieza
     * @param w tamaño del ancho
     * @param l tamaño del largo
     * @param t orientacion 1 = horizontal, 0 = vertical
     */
    public FormPlate(int numberOfPiece, boolean t){
        identifier = numberOfPiece;
        orientacion = t;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public boolean isOrientacion() {
        return orientacion;
    }

    public void invOrientacion() {
        this.orientacion = !orientacion;
    }

    @Override
    public String toString() {
        return "FormPlate{" + "identifier=" + identifier + " orientacion=" + orientacion + '}';
    }
}
