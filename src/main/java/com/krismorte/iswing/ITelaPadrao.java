/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JTable;

/**
 *
 * @author c007329
 */
public interface ITelaPadrao {

    public void refreshTable();

    public boolean exportToPdf();

    public boolean exportToExcel();

    public boolean exportToPdf(String filename);

    public boolean exportToExcel(String filename);

    public boolean exportToPdf(File file);

    public boolean exportToExcel(File file);

    public void addKeyEvent(KeyAdapter event);

    public void addMouseEvent(MouseAdapter event);

    public void addWindowsEvent(WindowAdapter event);

    public void addJMenuItens(JMenuItem... item);

    public JTable getTable();

    public Object getValueOfColumn(int column);

    public Object getParentIPanel();

    public Object[] getValueOfSelectedRow();
    
    public void resizeGui();

}
