package com.krismorte.iswing.jtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TableFactoryTest {

    JPanel panelWithJtable;
    String[] columns;
    Object[][] lines;
    MyTable mytable;

    @BeforeEach
    void setUp() {
        panelWithJtable = new JPanel(new GridBagLayout());
        columns = new String[]{"Name","Age"};
        lines = new Object[][]{{"Mary",21},{"Jonh",35},{"Robert",45}};
        mytable = TableFactory.getInstance(panelWithJtable,columns,lines);
    }

    @Test
    void getInstance() {

        assertNotNull(mytable.getJTable());
    }

    @Test
    void search() {
        assertEquals(mytable.search("Mary").length,1);
        assertEquals(mytable.search("Peter").length,0);
    }


    @Test
    void addMouseListener() {
        MouseAdapter mouseAdapter = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        };

        mytable.addMouseListener(mouseAdapter);

        assertNotNull(mytable.getJTable().getMouseListeners());
    }
}