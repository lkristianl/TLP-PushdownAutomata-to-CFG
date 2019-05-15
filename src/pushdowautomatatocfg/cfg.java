/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;

import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class cfg {
    
    public cfg (int numeroT, int longitudA)
    {
        this.numeroTransiciones = numeroT;
        this.transiciones = new transicionesCFG[numeroTransiciones];
        for (int i = 0; i < numeroTransiciones; i++)
        {
            this.transiciones[i] = new transicionesCFG();
        }
        
        this.longitudAlfabeto = longitudA;
        this.alfabeto = new char [longitudA];
    }
    
    
    // Conj de estados no terminales
    public int numeroEstadosNoTerminales = 0;
    public ArrayList<String> estadosGramatica = new ArrayList<>(); 
    
    // Alfabeto de la gramatica de contexto libre
    public int longitudAlfabeto;
    public char[] alfabeto;
    
    // Transiciones de GLC
    public int numeroTransiciones;
    public transicionesCFG[] transiciones;

    @Override
    public String toString() {
        String conjEstadosGramatica = "(";
        for (int i = 0; i < estadosGramatica.size(); i++)
        {
            conjEstadosGramatica = conjEstadosGramatica + estadosGramatica.get(i) + " ";
        }
        conjEstadosGramatica = conjEstadosGramatica + ")";
        
        String conjAlfabeto;
        conjAlfabeto= "{ ";
        for (int i = 0; i < longitudAlfabeto; i++)
        {
            conjAlfabeto = conjAlfabeto + alfabeto[i] + " ";
        }
        conjAlfabeto = conjAlfabeto + "}";
        
        String conjTransiciones = "";
        for (int i = 0; i < this.numeroTransiciones; i++)
        {
            conjTransiciones = conjTransiciones + "T" + i + ": " + this.transiciones[i].toString();
        }
        
        return "cfg{" + "estadosNoTerminales=" + conjEstadosGramatica + ", longitudAlfabeto=" + longitudAlfabeto + ", alfabeto=" + conjAlfabeto +
                ", transiciones: \n" + conjTransiciones + '}';
    }
    
    
    
}
