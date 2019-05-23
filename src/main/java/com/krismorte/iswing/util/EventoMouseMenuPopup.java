/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author krisnamourtscf
 */
public class EventoMouseMenuPopup implements MouseListener {

    private JPopupMenu menuFlutuante;
    private JTable tabela;

    public EventoMouseMenuPopup(JPopupMenu menuFlutuante, JTable tabela) {
        this.menuFlutuante = menuFlutuante;
        this.tabela = tabela;
    }
   

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            int col = tabela.columnAtPoint(e.getPoint());
            int row = tabela.rowAtPoint(e.getPoint());
            if (col != -1 && row != -1) {
                tabela.setColumnSelectionInterval(col, col);
                tabela.setRowSelectionInterval(row, row);
            }
            menuFlutuante.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }
}
