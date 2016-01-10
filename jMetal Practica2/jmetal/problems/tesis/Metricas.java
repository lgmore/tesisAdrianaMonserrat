/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmetal.problems.tesis;

/**
 *
 * @author monsemora
 */
public class Metricas {
    private double entropiaOrig;
    private double entropiaClahe;
    private double ltg;

    public Metricas(double entropiaOrig, double entropiaClahe, double ltg) {
        this.entropiaOrig = entropiaOrig;
        this.entropiaClahe = entropiaClahe;
        this.ltg = ltg;
    }

    @Override
    public String toString() {
        return "Metricas{" + "entropia original = " + entropiaOrig + " \n entropia clahe = "
                + "" + entropiaClahe + " \n ltg = " + ltg + '}';
    }
}
