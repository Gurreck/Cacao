/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_servidor.clases;

import java.io.Serializable;

/**
 *
 * @author adria
 */
public class Globales {
    
    public Transferencia transferencia = new Transferencia();
    private static Globales INSTANCE = null;
   
    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (Globales.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Globales();
                }
            }
        }
    }

    public static Globales getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }
    
}
