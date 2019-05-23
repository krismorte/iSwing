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
public interface IPanelButton {

    public ActionListener getActionSalvar();

    public ActionListener getActionRemover();

    public ActionListener getActionCancelar();

}
