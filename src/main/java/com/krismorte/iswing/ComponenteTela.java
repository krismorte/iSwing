/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.awt.Component;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author c007329
 */
public class ComponenteTela {

    private GenField anotacao;
    private Field campo;
    private Object objeto;
    private GenFieldUtil util = new GenFieldUtil();

    public ComponenteTela(GenField anotacao, Field campo, Object objeto) {
        this.anotacao = anotacao;
        this.campo = campo;
        this.objeto = objeto;
    }

    public JButton extractFieldButtonFromJPanel() {
        JPanel p = (JPanel) objeto;
        JButton button = null;
        for (Component c : p.getComponents()) {
            if (c instanceof JButton) {
                button = (JButton) c;
                break;
            }
        }
        return button;
    }

    public JComboBox extractJComboBoxFromJPanel() {
        JPanel p = (JPanel) objeto;
        JComboBox combo = null;
        for (Component c : p.getComponents()) {
            if (c instanceof JComboBox) {
                combo = (JComboBox) c;
                break;
            }
        }
        return combo;
    }

    public static JComboBox extractJComboBoxFromJPanel(JPanel p) {
        JComboBox combo = null;
        for (Component c : p.getComponents()) {
            if (c instanceof JComboBox) {
                combo = (JComboBox) c;
                break;
            }
        }
        return combo;
    }

    public static JTextField extractJTextFieldFromJPanel(JPanel p) {
        JTextField combo = null;
        for (Component c : p.getComponents()) {
            if (c instanceof JTextField) {
                combo = (JTextField) c;
                break;
            }
        }
        return combo;
    }

    /**
     * @return the anotacao
     */
    public GenField getAnotacao() {
        return anotacao;
    }

    /**
     * @param anotacao the anotacao to set
     */
    public void setAnotacao(GenField anotacao) {
        this.anotacao = anotacao;
    }

    /**
     * @return the campo
     */
    public Field getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(Field campo) {
        this.campo = campo;
    }

    /**
     * @return the objeto
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public Object getValor() throws Exception {
        return util.getValor(anotacao, objeto);
    }
    
    public Object getValor(Object value) throws Exception {
        return util.getValor(anotacao, value);
    }

    public void setValor(Object obj) throws Exception {
        util.setValor(anotacao, objeto, obj);
    }

}
