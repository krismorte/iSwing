package com.krismorte.iswing.jtable;

import javax.swing.*;
import java.awt.event.MouseListener;

public interface MyTable {

    public JTable getJTable();

    public void addMouseListener(MouseListener listener);

    public Object[][] search(String text);
    public  Object[][] search(String text, int column);

}
