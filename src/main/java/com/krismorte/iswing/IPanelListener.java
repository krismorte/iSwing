/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

/**
 *
 * @author c007329
 */
public class IPanelListener {
    
    public FocusListener getDefaultFocusListener(JTextComponent txt) {
        Color old = txt.getBackground();
        FocusListener f = new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent e) {
                //j.setBackground(new Color(255, 228, 225));
                txt.setBackground(new Color(255, 250, 205));
                
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                txt.setBackground(old);
            }
        };
        return f;
        
    }
    
    public FocusListener getFocusListener(JTextComponent txt, Color newest) {
        Color old = txt.getBackground();
        return new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent e) {
                //j.setBackground(new Color(255, 228, 225));
                txt.setBackground(newest);
                
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                txt.setBackground(old);
            }
        };
        
    }
    
    /*public FocusListener getFocusListener(JComponent txt) {
        Color old = txt.getBackground();
        return new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent e) {
                UIManager.put("ComboBox.background", new Color(255, 250, 205));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                UIManager.put("ComboBox.background", old);
            }
        };
        
    }*/
    
    public FocusListener getFocusListener(JComponent txt, Color newest) {
        Color old = txt.getBackground();
        return new FocusListener() {
            
            @Override
            public void focusGained(FocusEvent e) {
                //j.setBackground(new Color(255, 228, 225));
                UIManager.put("ComboBox.background", newest);
                
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                UIManager.put("ComboBox.background", old);
            }
        };
        
    }
    
    
}
