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
    int ancho;
    int alto;
    boolean orientacion;
    
    /**
     * 
     * @param numberOfPiece numero de la pieza
     * @param w tamaño del ancho
     * @param l tamaño del largo
     * @param t orientacion 1 = horizontal, 0 = vertical
     */
    public FormPlate(int numberOfPiece, int w, int l, boolean t){
        identifier = numberOfPiece;
        ancho = w;
        alto = l;
        orientacion = t;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public boolean isOrientacion() {
        return orientacion;
    }

    public void setOrientacion(boolean orientacion) {
        this.orientacion = orientacion;
    }

    @Override
    public String toString() {
        return "FormPlate{" + "identifier=" + identifier + ", ancho=" + ancho + ", largo=" + alto + ", orientacion=" + orientacion + '}';
    }
}
