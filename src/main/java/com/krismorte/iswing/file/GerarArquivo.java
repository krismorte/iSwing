/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krismorte.iswing.file;

/**
 *
 * @author c007329
 */
public interface GerarArquivo {
    public boolean gerarArquivo(String titulo,String destino, String[] colunas, Object[][] linhas);
}
