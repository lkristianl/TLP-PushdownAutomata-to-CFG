/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;

/**
 *
 * @author Kris
 */
public class cfg {
    
    
    
    // Conj de estados no terminales
    public int numeroEstadosNoTerminales;
    public String[] estadosGramatica;
    
    // Alfabeto de la gramatica de contexto libre
    public int longitudAlfabeto;
    public char[] alfabeto;
    
    // Transiciones de GLC
    public transicionesCFG[] transiciones;

    @Override
    public String toString() {
        String conjEstadosGramatica = "(";
        for (int i = 0; i < numeroEstadosNoTerminales; i++)
        {
            conjEstadosGramatica = conjEstadosGramatica + this.estadosGramatica[i] + " ";
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
        for (int i = 0; i < this.numeroEstadosNoTerminales; i++)
        {
            conjTransiciones = conjTransiciones + "T" + i + this.transiciones[i].toString();
        }
        
        return "cfg{" + "numeroEstadosNoTerminales=" + numeroEstadosNoTerminales + ", estadosGramatica=" + conjEstadosGramatica + ", longitudAlfabeto=" + longitudAlfabeto + ", alfabeto=" + conjAlfabeto +
                ", transiciones: \n" + conjTransiciones + '}';
    }
    
    
    
}
