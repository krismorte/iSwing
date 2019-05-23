/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.awt.event.ActionListener;

/**
 *
 * @author c007329
 */
public abstract class IPanelButtonAction {

    private IPanelDados iPanel;

    public IPanelButtonAction(IPanelDados iPanel) {
        this.iPanel = iPanel;
        iPanel.setiPanelButton(this);
    }

    public Object getInstancia() throws Exception {
        return iPanel.createNewInstance();
    }

    public Object updateInstancia(Object obj) throws Exception {
        return iPanel.updateInstance(obj);
    }

    public void showAttributes(Object obj) throws Exception {
        iPanel.showAttributes(obj);
    }
    
    public Object getInstanciaFromTable() throws Exception {
        return iPanel.getObjectFromTable();
    }

    public void clearScreen() {
        iPanel.clearScreen();
    }

    public abstract ActionListener getActionSalvar();

    public abstract ActionListener getActionRemover();

    public abstract ActionListener getActionCancelar();

}
