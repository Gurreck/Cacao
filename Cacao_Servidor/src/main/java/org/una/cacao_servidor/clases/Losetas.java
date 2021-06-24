package org.una.cacao_servidor.clases;

/**
 *
 * @author Esteban Vargas
 */
public class Losetas {
    
    private String tipo;
    private String color;
    private int arriba;
    private int abajo;
    private int izquierda;
    private int derecha;

    public Losetas() {
    }

    public Losetas(String tipo) {
        this.tipo = tipo;
    }
    
    public Losetas(String tipo, String color, int arriba, int abajo, int izquierda, int derecha) {
        this.tipo = tipo;
        this.color = color;
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setArriba(int arriba) {
        this.arriba = arriba;
    }

    public void setAbajo(int abajo) {
        this.abajo = abajo;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public int getArriba() {
        return arriba;
    }

    public int getAbajo() {
        return abajo;
    }

    public int getIzquierda() {
        return izquierda;
    }

    public int getDerecha() {
        return derecha;
    }
    
    
}
