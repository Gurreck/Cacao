package org.una.cacao_cliente.clases;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Esteban Vargas
 */
public class Jugadores {
    
    private String nombre;
    private Date fechaNacimiento;
    private String color;
    private boolean aceptarPartida;
    private int cacaos;
    private int monedas;
    private int puntosRio;
    private List<LosetasRecolectores> losetasRecolectores;
    
    public Jugadores(){
    }

    public Jugadores(String nombre, Date fechaNacimiento, String color,
        List<LosetasRecolectores> losetasRecolectores, int cacaos, int monedas, int puntosRio, boolean aceptarPartida) {
        
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.color = color;
        this.losetasRecolectores = losetasRecolectores;
        this.cacaos = cacaos;
        this.monedas = monedas;
        this.puntosRio = puntosRio;
        this.aceptarPartida = aceptarPartida;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCacaos(int cacaos) {
        this.cacaos = cacaos;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public void setPuntosRio(int puntosRio) {
        this.puntosRio = puntosRio;
    }

    public void setLosetasRecolectores(List<LosetasRecolectores> losetasRecolectores) {
        this.losetasRecolectores = losetasRecolectores;
    }

    public void setAceptarPartida(boolean aceptarPartida) {
        this.aceptarPartida = aceptarPartida;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getColor() {
        return color;
    }

    public int getCacaos() {
        return cacaos;
    }

    public int getMonedas() {
        return monedas;
    }

    public int getPuntosRio() {
        return puntosRio;
    }

    public boolean isAceptarPartida() {
        return aceptarPartida;
    }

    public List<LosetasRecolectores> getLosetasRecolectores() {
        return losetasRecolectores;
    }

    
}
