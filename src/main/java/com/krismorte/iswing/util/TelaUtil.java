/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.util;

import com.krismorte.iswing.IPanelListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;


/**
 *
 * @author c007329
 */
public class TelaUtil {

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, Object[] lista) {
        box.removeAllItems();
        for (Object o : lista) {
            box.addItem(o);
        }
    }

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @primeiro a String to put first
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, String primeiro, Object[] lista) {
        box.removeAllItems();
        box.addItem(primeiro);
        for (Object o : lista) {
            box.addItem(o);
        }

    }

    /**
     * Method to fulfill JComboBox
     *
     * @box a instance of JComboBox to fulfill
     * @primeiro a Object to put select
     * @lista a Object[] list with data
     */
    public static void preencheBox(JComboBox box, Object primeiro, Object[] lista) {
        box.removeAllItems();
        for (Object o : lista) {
            box.addItem(o);
        }
        box.setSelectedItem(primeiro);
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @lstBtn a list of AbstractButton to initiate
     */
    public static void iniciaAbstractButton(AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @param selecFirst
     * @param lstBtn
     */
    public static void iniciaAbstractButton(boolean selecFirst, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        lstBtn[0].setSelected(true);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
    }

    /**
     * Method thats initiate Radio and Check Button
     *
     * @param selectedButton
     * @param doClick
     * @param lstBtn
     */
    public static void iniciaAbstractButton(AbstractButton selectedButton, boolean doClick, AbstractButton... lstBtn) {
        ButtonGroup grp = new ButtonGroup();
        grp.add(selectedButton);
        for (AbstractButton a : lstBtn) {
            grp.add(a);
        }
        selectedButton.doClick();
    }

    /**
     * Return 1 if JRadioButton is selected
     *
     * @param btn
     * @return
     */
    public static int valueRadioToInt(JRadioButton btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Recupera Texto do JPasswordField
     *
     * @param pass
     * @return
     */
    public static String getSenha(JPasswordField pass) {
        char[] c = pass.getPassword();
        String str = "";
        for (char c1 : c) {
            str += c1;
        }
        return str;
    }

    /**
     * *
     * Compara senhas vindas do JPasswordField
     *
     * @param pass1
     * @param pass2
     * @return
     */
    public static boolean verificaSenhas(JPasswordField pass1, JPasswordField pass2) {
        String senha = getSenha(pass1);
        String senhaNovamente = getSenha(pass2);
        if (senha.equals("") || senhaNovamente.equals("")) {
            return false;
        } else if (senha.equals(senhaNovamente)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * Compara senhas vindas String
     *
     * @param pass1
     * @param pass2
     * @return
     */
    public static boolean verificaSenhas(String pass1, String pass2) {
        if (pass1.equals("") || pass2.equals("")) {
            return false;
        } else if (pass1.equals(pass2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return 1 if JCheckBox is selected
     *
     * @param btn
     * @return
     */
    public static int valueCheckToInt(JCheckBox btn) {
        if (btn.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Verify if btnMan or btnWon match the the value. The JRadioButton
     * represents Male and Female sex respective
     *
     * @param btnMan
     * @param btnWon
     * @param value
     */
    public static void valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon, String value) {
        if (value.toUpperCase().equals("F")) {
            btnWon.setSelected(true);
        } else if (value.toUpperCase().equals("M")) {
            btnMan.setSelected(true);
        }
    }

    /**
     * Return M if btnMan is selected and F to btnWon. The JRadioButton
     * represents Male and Female sex respective
     *
     * @param btnMan
     * @param btnWon
     * @return
     */
    public static String valueRadioForSex(JRadioButton btnMan, JRadioButton btnWon) {
        if (btnWon.isSelected()) {
            return "F";
        } else if (btnMan.isSelected()) {
            return "M";
        } else {
            return "E";
        }
    }

    /**
     * Verify is a JTextComponent is empty
     *
     * @param componente
     * @param errorMsg
     * @param txt
     * @return
     */
    public static boolean verificaCamposVazios(JComponent componente, String errorMsg, JTextComponent... txt) {
        boolean retorno = true;
        for (JTextComponent t : txt) {
            if (t.getText().equals("")) {
                retorno = false;
            }
        }
        if (!retorno) {
            JOptionPane.showMessageDialog(componente, errorMsg);
        }
        return retorno;
    }

    public static File getFilePath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();

        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = arquivo.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static File[] getFilesPath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();

        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }

        arquivo.setMultiSelectionEnabled(true);
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File[] arquivoNome = arquivo.getSelectedFiles();

            return arquivoNome;
        } else {
            return null;
        }
    }

    /**
     * Gera um ImageIcon comomo recurso
     *
     * exemplo de caminho "/image/imagename.png"
     *
     * @param classe
     * @param caminho
     * @return
     */
    public static ImageIcon getImageIcon(Class classe, String caminho) {
        return new javax.swing.ImageIcon(classe.getResource(caminho));
    }

    /**
     * alter fonte da tela
     *
     * @param JFrame that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean alteraFonteTela(JFrame frame, Font fonte) {
        alteraFonteTela((JPanel) frame.getContentPane(), fonte);
        return true;
    }

    /**
     * alter fonte da tela
     *
     * @param JDialog that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean alteraFonteTela(JDialog dialog, Font fonte) {
        alteraFonteTela((JPanel) dialog.getContentPane(), fonte);
        return true;
    }

    /**
     * alter fonte da tela
     *
     * @param JPanel that will be extract all componets.
     * @return boolean
     * @exception Throws a Exception.
     * @author Krisnamourt
     */
    public static boolean alteraFonteTela(JPanel panel, Font fonte) {
        if (panel.getBorder() instanceof TitledBorder) {
            ((TitledBorder) panel.getBorder()).setTitleFont(fonte);
        }
        Component componentes[] = panel.getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setFont(fonte);
            } else if (c instanceof JLabel) {
                ((JLabel) c).setFont(fonte);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setFont(fonte);
            } else if (c instanceof JList) {
                ((JList) c).setFont(fonte);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setFont(fonte);
            } else if (c instanceof JRadioButton) {
                ((JRadioButton) c).setFont(fonte);
            } else if (c instanceof JPanel) {
                alteraFonteTela((JPanel) c, fonte);
            }
        }
        return true;
    }

    /**
     * *
     * Habilita os objetos de tela
     *
     * @param frame
     * @param habilita
     * @return
     */
    public static boolean habilitaCompentesDeTela(JFrame frame, boolean habilita) {
        habilitaCompentesDeTela((JPanel) frame.getContentPane(), habilita);
        return true;
    }

    /**
     * *
     * Habilita os objetos de tela
     *
     * @param dialog
     * @param habilita
     * @return
     */
    public static boolean habilitaCompentesDeTela(JDialog dialog, boolean habilita) {
        habilitaCompentesDeTela((JPanel) dialog.getContentPane(), habilita);
        return true;
    }

    /**
     * *
     * Habilita os objetos de tela
     *
     * @param panel
     * @param habilita
     * @return
     */
    public static boolean habilitaCompentesDeTela(JPanel panel, boolean habilita) {

        Component componentes[] = panel.getComponents();
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setEnabled(habilita);
            } else if (c instanceof JLabel) {
                ((JLabel) c).setEnabled(habilita);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setEnabled(habilita);
            } else if (c instanceof JList) {
                ((JList) c).setEnabled(habilita);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setEnabled(habilita);
            } else if (c instanceof JRadioButton) {
                ((JRadioButton) c).setEnabled(habilita);
            } else if (c instanceof JPanel) {
                habilitaCompentesDeTela((JPanel) c, habilita);
            }
        }
        return true;
    }

    /**
     * *
     * Habilita os objetos de tela
     *
     * @param habilita
     * @param componentes
     * @return
     */
    public static boolean habilitaCompentesDeTela(boolean habilita, Component... componentes) {
        for (Component c : componentes) {
            if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                ((JTextComponent) c).setEnabled(habilita);
            } else if (c instanceof JLabel) {
                ((JLabel) c).setEnabled(habilita);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setEnabled(habilita);
            } else if (c instanceof JList) {
                ((JList) c).setEnabled(habilita);
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setEnabled(habilita);
            } else if (c instanceof JRadioButton) {
                ((JRadioButton) c).setEnabled(habilita);
            } else if (c instanceof JPanel) {
                habilitaCompentesDeTela((JPanel) c, habilita);
            }
        }
        return true;
    }

    /**
     * *
     * Alterar orientação de do texto
     *
     * @param orientacao
     * @param componentes
     * @return
     */
    public static boolean alteraOrientacaoCampoTexto(int orientacao, Component... componentes) {
        for (Component c : componentes) {
            if (c instanceof JTextField) {
                ((JTextField) c).setHorizontalAlignment(orientacao);
            } else if (c instanceof JLabel) {
                ((JLabel) c).setHorizontalAlignment(orientacao);
            } else if (c instanceof JPanel) {
                alteraOrientacaoCampoTexto(orientacao, c);
            }
        }
        return true;
    }

    /**
     * *
     * Adicionar Focus Listener od objetos texto da tela
     *
     * @param orientacao
     * @param componentes
     * @return
     */
    public static boolean adicionaFocusListenerCampoTexto(Component... componentes) {
        IPanelListener iPanelListener = new IPanelListener();
        for (Component c : componentes) {
            if (c instanceof JTextComponent) {
                ((JTextComponent) c).addFocusListener(iPanelListener.getDefaultFocusListener((JTextComponent) c));
            } else if (c instanceof JFrame) {
                adicionaFocusListenerCampoTexto(((JPanel) ((JFrame) c).getContentPane()).getComponents());
            } else if (c instanceof JDialog) {
                adicionaFocusListenerCampoTexto(((JPanel) ((JDialog) c).getContentPane()).getComponents());
            } else if (c instanceof JPanel) {
                adicionaFocusListenerCampoTexto(((JPanel) c).getComponents());
            }
        }
        return true;
    }

    /**
     * Adicionar Focus Listener od objetos texto da tela para cor definida
     *
     * @param newest
     * @param componentes
     * @return
     */
    public static boolean adicionaFocusListenerCampoTexto(Color newest, Component... componentes) {
        IPanelListener iPanelListener = new IPanelListener();
        for (Component c : componentes) {
            if (c instanceof JTextComponent) {
                ((JTextComponent) c).addFocusListener(iPanelListener.getFocusListener((JTextComponent) c, newest));
            } else if (c instanceof JFrame) {
                adicionaFocusListenerCampoTexto(newest, ((JPanel) ((JFrame) c).getContentPane()).getComponents());
            } else if (c instanceof JDialog) {
                adicionaFocusListenerCampoTexto(newest, ((JPanel) ((JDialog) c).getContentPane()).getComponents());
            } else if (c instanceof JPanel) {
                adicionaFocusListenerCampoTexto(newest, ((JPanel) c).getComponents());
            }
        }
        return true;
    }

    public static File getDirectoryPath(String file) {

        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();
        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        int resultadoArq = arquivo.showSaveDialog(null);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = arquivo.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public static void addEventsToJMenuItens(JMenu menu, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            menu.add(i);
        }
    }

    public static void addEventsToJMenuItens(JMenu menu, int[] indexSeparetor, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        int contador = 0;
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            for (int ind : indexSeparetor) {
                if (contador == ind) {
                    menu.addSeparator();
                }
            }
            menu.add(i);
            contador++;
        }
    }

    public static void addEventsToJMenuItens(JPopupMenu menu, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            menu.add(i);
        }

    }

    public static void addEventsToJMenuItens(JPopupMenu menu, int[] indexSeparetor, ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        int contador = 0;
        for (JMenuItem i : itens) {
            i.addActionListener(events);
            for (int ind : indexSeparetor) {
                if (contador == ind) {
                    menu.addSeparator();
                }
            }
            menu.add(i);
            contador++;
        }

    }

    public static void addEventsToJMenuItens(ActionListener events, JMenuItem... itens) {
        //EventosJMenuItem events = new EventosJMenuItem();
        for (JMenuItem i : itens) {
            i.addActionListener(events);
        }
    }
}
