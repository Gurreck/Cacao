package org.una.cacao_servidor.clases;

/**
 *
 * @author Esteban Vargas
 */
public class Losetas {
    
    private String tipo;
    private String clasificacion;
    private String color;
    private double angulo;
    private int arriba;
    private int abajo;
    private int izquierda;
    private int derecha;

    public Losetas() {
    }

    public Losetas(String tipo, String clasificacion) {
        this.tipo = tipo;
        this.clasificacion = clasificacion;
    }
    
    public Losetas(String tipo, String clasificacion, String color, double angulo, int arriba, int abajo, int izquierda, int derecha) {
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.color = color;
        this.angulo = angulo;
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
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

    public String getClasificacion() {
        return clasificacion;
    }

    public String getColor() {
        return color;
    }

    public double getAngulo() {
        return angulo;
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
