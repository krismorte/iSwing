/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * IPanelDados.java
 *
 * Created on 03/06/2015, 20:22:20
 */
package com.krismorte.iswing;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.krismorte.iswing.util.LimpaCampos;
import com.krismorte.iswing.util.TelaUtil;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author c007329
 */
public abstract class IPanelDados extends javax.swing.JPanel {

    private String title;
    private Class classe;
    private ITelaPadrao tela;
    private List<ComponenteTela> componentes = new ArrayList<>();
    private List<GenField> anotacoes = new ArrayList<>();
    private List<Field> fields = new ArrayList<>();
    private List<IPanelList> lists = new ArrayList<>();
    private String[] colunas;
    private String view;
    private JButton bSalvar = new JButton("salvar");
    private JButton bRemover = new JButton("remover");
    private JButton bCancelar = new JButton("cancelar");
    private Font fontLabel = new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 14);
    private Font fontInput = new Font(Font.SANS_SERIF, Font.ITALIC, 14);
    private GridBagConstraints bag;
    private IPanelUtil ipanelUtil = new IPanelUtil();
    private IPanelListener ipanelLientener = new IPanelListener();

    /**
     * Creates new form IPanelDados
     */
    public IPanelDados(String title,/* IPanelButton iPanelButton,*/ Class c, String view) {
        initComponents();
        this.title = title;
        this.view = view;
        this.classe = c;
        iniciaTela(c, view, true);
    }

    /**
     * Creates new form IPanelDados
     */
    public IPanelDados(String title,/* IPanelButton iPanelButton,*/ Class c, String view, boolean addBRemover) {
        initComponents();
        this.title = title;
        this.view = view;
        this.classe = c;
        iniciaTela(c, view, addBRemover);
    }

    /**
     * Creates new form IPanelDados
     */
    public IPanelDados(String title, Class c, String view, Font fontLabel, Font fontInput, boolean addBRemover) {
        initComponents();
        this.title = title;
        this.view = view;
        this.classe = c;
        this.fontLabel = fontLabel;
        this.fontInput = fontInput;
        iniciaTela(c, view, addBRemover);
    }

    private void iniciaTela(Class c, String view, boolean addBRemover) {
        loadAttributes(c);
        colunas = ipanelUtil.loadColumnsByView(componentes, view);

        int linhas = anotacoes.size() + 1;
        //this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(new GridBagLayout());
        bag = new GridBagConstraints();
        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 0.5;
        bag.ipady = 8;

        //for (GenField g : anotacoes) {
        int linhaComponentes = 0;
        for (ComponenteTela comp : componentes) {
            //Caso campo não deva aparecer no cadastro
            if (comp.getAnotacao().showOnCRUD() == false) {
                continue;
            }
            if (comp.getAnotacao().fieldType() == GenField.JTEXTFIELD) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                if (comp.getAnotacao().fieldButton()) {
                    JPanel p2 = new JPanel();
                    p2.setLayout(new GridBagLayout());
                    bag.weightx = 0.9;
                    JTextField o = getJTextField(comp.getAnotacao(), linhaComponentes);
                    p2.add(o, bag);
                    JButton jb = new JButton("...");
                    jb.setVisible(true);
                    bag.weightx = 0.1;
                    p2.add(jb, bag);
                    bag.gridy = linhaComponentes;
                    bag.weightx = 0.5;
                    this.add(p2, bag);
                    comp.setObjeto(p2);
                } else {
                    JTextField o = getJTextField(comp.getAnotacao(), linhaComponentes);

                    this.add(o, bag);
                    comp.setObjeto(o);
                }
            } else if (comp.getAnotacao().fieldType() == GenField.JTEXTAREA) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                JTextArea o = getJTextArea(comp.getAnotacao(), linhaComponentes);
                JScrollPane scroll = new JScrollPane(o);
                this.add(scroll, bag);
                comp.setObjeto(o);
                bag.ipady = 8;
            } else if (comp.getAnotacao().fieldType() == GenField.JPASSWORDFIELD) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                JPasswordField o = getJPasswordField(comp.getAnotacao(), linhaComponentes);

                this.add(o, bag);
                //componentes.add(new ComponenteTela(g, o));
                comp.setObjeto(o);
            } else if (comp.getAnotacao().fieldType() == GenField.JDATEPICKER) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                JXDatePicker o = getJXDatePicker(comp.getAnotacao(), linhaComponentes);

                this.add(o, bag);
                //componentes.add(new ComponenteTela(comp.getAnotacao(), o));
                comp.setObjeto(o);
            } else if (comp.getAnotacao().fieldType() == GenField.LGDATEPICKER) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                DatePicker o = getDatePicker(comp.getAnotacao(), linhaComponentes);

                this.add(o, bag);
                comp.setObjeto(o);
            } else if (comp.getAnotacao().fieldType() == GenField.LGDATETIMEPICKER) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                DateTimePicker o = getDateTimePicker(comp.getAnotacao(), linhaComponentes);

                this.add(o, bag);
                comp.setObjeto(o);
            } else if (comp.getAnotacao().fieldType() == GenField.JCOMBOBOX) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                if (comp.getAnotacao().fieldButton()) {
                    JPanel p2 = new JPanel();
                    p2.setLayout(new GridBagLayout());
                    bag.weightx = 0.9;
                    JComboBox o = getJComboBox(comp.getAnotacao(), linhaComponentes);
                    p2.add(o, bag);
                    JButton jb = new JButton("...");
                    jb.setVisible(true);
                    bag.weightx = 0.1;
                    p2.add(jb, bag);
                    bag.gridy = linhaComponentes;
                    bag.weightx = 0.5;
                    this.add(p2, bag);
                    comp.setObjeto(p2);
                } else {
                    JComboBox o = getJComboBox(comp.getAnotacao(), linhaComponentes);
                    this.add(o, bag);
                    comp.setObjeto(o);
                }
            } else if (comp.getAnotacao().fieldType() == GenField.JRADIONBUTTON) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                JPanel p2 = new JPanel();
                p2.setLayout(new GridLayout(1, comp.getAnotacao().fieldLabels().length));
                ButtonGroup grp = new ButtonGroup();
                //JRadioButton[] lst = new JRadioButton[comp.getAnotacao().fieldLabels().length];
                for (int i = 0; i < comp.getAnotacao().fieldLabels().length; i++) {
                    if (i == 0) {
                        getJRadioButton(p2, grp, comp.getAnotacao(), i).setSelected(true);
                    } else {
                        getJRadioButton(p2, grp, comp.getAnotacao(), i);
                    }

                }

                bag.gridy = linhaComponentes;

                this.add(p2, bag);

                comp.setObjeto(p2);
            } else if (comp.getAnotacao().fieldType() == GenField.JCHECKBOX) {
                this.add(getJLabel(comp.getAnotacao().fieldText(), linhaComponentes), bag);
                JPanel p2 = new JPanel();
                p2.setLayout(new GridLayout(1, comp.getAnotacao().fieldLabels().length));
                JCheckBox[] lst = new JCheckBox[comp.getAnotacao().fieldLabels().length];
                for (int i = 0; i < comp.getAnotacao().fieldLabels().length; i++) {
                    lst[i] = new JCheckBox(comp.getAnotacao().fieldLabels()[i]);
                    p2.add(lst[i]);
                }
                //bag.gridx = 1;
                bag.gridy = linhaComponentes;
                //bag.ipady = 8;
                this.add(p2, bag);
                //componentes.add(new ComponenteTela(g, p2));
                comp.setObjeto(p2);
            }
            linhaComponentes++;
        }
        configureJButtons(linhaComponentes, addBRemover);

        this.setSize(new Dimension(this.getWidth() - 12, linhas * 30));
        this.setVisible(true);
        this.validate();
    }

    private void configureJButtons(int linhaComponentes, boolean addBRemover) {
        bRemover.setFont(fontLabel);
        bCancelar.setFont(fontLabel);
        bSalvar.setFont(fontLabel);
        JPanel pButton = new JPanel();
        pButton.setLayout(new GridLayout(1, 2));
        pButton.add(bRemover);
        pButton.add(bCancelar);
        //bag.gridx = 0;
        bag.gridy = linhaComponentes;
        bag.ipady = 10;
        this.add(bSalvar, bag);
        bag.gridx = 1;
        if (addBRemover) {
            this.add(pButton, bag);
        } else {
            this.add(bCancelar, bag);
        }
    }

    private JLabel getJLabel(String texto, int linhaComponentes) {
        //bag.gridx = 0;
        bag.gridy = linhaComponentes;
        // bag.ipady = 8;
        JLabel j = new JLabel(" " + texto + ":");
        j.setFont(fontLabel);
        return j;
    }

    private JTextField getJTextField(GenField anotacao, int linhaComponentes) {
        JTextField j = new JTextField(5);
        j.setFont(fontInput);
        j.addFocusListener(ipanelLientener.getDefaultFocusListener(j));
        if (anotacao.type() == GenField.INT || anotacao.type() == GenField.LONG || anotacao.type() == GenField.SHORT || anotacao.type() == GenField.DOUBLE) {
            j.setHorizontalAlignment(JTextField.RIGHT);
        }
        j.setEditable(anotacao.fieldEditable());
        j.setEnabled(anotacao.fieldEnable());
        j.setToolTipText(anotacao.fieldTip());
        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        //bag.ipady = 8;
        return j;
    }

    private JComboBox getJComboBox(GenField anotacao, int linhaComponentes) {
        JComboBox j = new JComboBox();
        j.setFont(fontInput);
        j.setEditable(anotacao.fieldEditable());
        j.setEnabled(anotacao.fieldEnable());
        j.setToolTipText(anotacao.fieldTip());
        if (anotacao.fieldOptions().length > 1) {
            TelaUtil.preencheBox(j, anotacao.fieldOptions());
        }
        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        //bag.ipady = 8;
        return j;
    }

    public void addComboBoxList(String fieldText, List list) {
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().fieldText().equals(fieldText)) {
                IPanelList ip = IPanelList.getIPanelList(lists, comp.getAnotacao());
                if (ip != null) {
                    lists.remove(ip);
                    lists.add(new IPanelList(comp.getAnotacao(), list));
                } else {
                    lists.add(new IPanelList(comp.getAnotacao(), list));
                }
                break;
            }
        }
    }

    public void addFieldButtonAction(String fieldText, ActionListener action) {
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().fieldText().equals(fieldText)) {
                comp.extractFieldButtonFromJPanel().addActionListener(action);
                break;
            }
        }
    }

    public void refreshBox(String fieldText) {
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().fieldType() == GenField.JCOMBOBOX) {
                if (comp.getAnotacao().fieldText().equals(fieldText)) {
                    JComboBox o = null;
                    if (comp.getAnotacao().fieldButton()) {
                        o = comp.extractJComboBoxFromJPanel();
                    } else {
                        o = (JComboBox) comp.getObjeto();
                    }

                    o.removeAllItems();
                    if (comp.getAnotacao().fieldOptions().length > 1) {//Simple List
                        TelaUtil.preencheBox(o, comp.getAnotacao().fieldOptions());
                    } else {//Object List
                        List l = IPanelList.getList(lists, comp.getAnotacao());
                        if (l != null) {
                            TelaUtil.preencheBox(o, l.toArray());
                        }
                    }
                    break;
                }
            }
        }
    }

    private JTextArea getJTextArea(GenField anotacao, int linhaComponentes) {
        JTextArea j = new JTextArea(4, 40);

        j.addFocusListener(ipanelLientener.getDefaultFocusListener(j));
        j.setToolTipText(anotacao.fieldTip());
        j.setEditable(anotacao.fieldEditable());
        j.setEnabled(anotacao.fieldEnable());
        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        bag.ipady = 45;
        return j;
    }

    private JXDatePicker getJXDatePicker(GenField anotacao, int linhaComponentes) {
        JXDatePicker j = new JXDatePicker();
        j.setFont(fontInput);
        j.addFocusListener(ipanelLientener.getDefaultFocusListener(j.getEditor()));
        j.setEditable(anotacao.fieldEditable());
        j.setEnabled(anotacao.fieldEnable());
        j.setToolTipText(anotacao.fieldTip());
        if (!anotacao.fieldDateFormat().equals("")) {
            j.setFormats(anotacao.fieldDateFormat());
        }

        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        //bag.ipady = 8;
        return j;
    }

    private DatePicker getDatePicker(GenField anotacao, int linhaComponentes) {
        DatePicker j = new DatePicker();
        j.setFont(fontInput);
        //j.addFocusListener(ipanelLientener.getDefaultFocusListener(j.get));
        j.getComponentDateTextField().addKeyListener(ipanelLientener.getKeyListenerDatePicker(j));
        j.setToolTipText(anotacao.fieldTip());
        j.setEnabled(anotacao.fieldEnable());

        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        // bag.ipady = 8;
        return j;
    }

    private DateTimePicker getDateTimePicker(GenField anotacao, int linhaComponentes) {
        DateTimePicker j = new DateTimePicker();
        j.setFont(fontInput);;
        j.setEnabled(anotacao.fieldEnable());
        j.getDatePicker().addKeyListener(ipanelLientener.getKeyListenerDatePicker(j.getDatePicker()));
        j.getTimePicker().getComponentTimeTextField().addKeyListener(ipanelLientener.getKeyListenerTimePicker(j.getTimePicker()));
        j.setToolTipText(anotacao.fieldTip());
        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        //bag.ipady = 8;
        return j;
    }

    private JPasswordField getJPasswordField(GenField anotacao, int linhaComponentes) {
        JPasswordField j = new JPasswordField();
        j.addFocusListener(ipanelLientener.getDefaultFocusListener(j));
        j.setEditable(anotacao.fieldEditable());
        j.setEnabled(anotacao.fieldEnable());
        j.setToolTipText(anotacao.fieldTip());
        //bag.gridx = 1;
        bag.gridy = linhaComponentes;
        // bag.ipady = 8;
        return j;
    }

    private JRadioButton getJRadioButton(JPanel panel, ButtonGroup grp, GenField gen, int ind) {
        JRadioButton j = new JRadioButton(gen.fieldLabels()[ind]);
        j.setFont(fontLabel);
        j.setToolTipText(gen.fieldLabels()[ind]);
        grp.add(j);
        panel.add(j);
        return j;
    }

    /**
     * @param iPanelButton the iPanelButton to set
     */
    public void setiPanelButton(IPanelButtonAction iPanelButton) {
        bSalvar.addActionListener(iPanelButton.getActionSalvar());
        bRemover.addActionListener(iPanelButton.getActionRemover());
        bCancelar.addActionListener(iPanelButton.getActionCancelar());
    }

    public Object createNewInstance() throws Exception {
        /*Object obj = classe.newInstance();
         for (ComponenteTela comp : componentes) {
         if (comp.getAnotacao().fieldEnable()) {
         Field f2 = obj.getClass().getDeclaredField(comp.getCampo().getName());
         f2.setAccessible(true);
         f2.set(obj, comp.getValor());
         }
         }
         return obj;*/
        return ipanelUtil.createNewInstance(componentes, classe);
    }

    public Object updateInstance(Object obj) throws Exception {
        return ipanelUtil.updateInstance(componentes, obj);
    }

    public void showAttributes(Object obj) throws Exception {
        ipanelUtil.showAttributes(componentes, obj);
    }

    public void clearScreenX() {
        LimpaCampos.limpaCamposX(this);
    }

    public void clearScreen() {
        LimpaCampos.limpaCampos(this);
    }

    public List<ComponenteTela> getComponentes() {
        return componentes;
    }

    public Object[][] loadLines(List list) throws Exception {
        return ipanelUtil.loadLinesByView(componentes, colunas, list, view);
    }

    public void loadAttributes(Class C) {
        componentes.clear();
        anotacoes.clear();
        fields.clear();
        for (Field f : C.getDeclaredFields()) {
            if (f.isAnnotationPresent(GenField.class)) {
                GenField anotacao = f.getAnnotation(GenField.class);
                componentes.add(new ComponenteTela(anotacao, f, null));
                anotacoes.add(anotacao);
                fields.add(f);

            }
        }

    }

    public String[] getColumns() {
        return colunas;
    }

    public int getColumnWithObjectRepresentation() {
        return ipanelUtil.getColumnWithObjectRepresentation(componentes, view);
    }

    public Object getObjectFromTable() {
        int column = getColumnWithObjectRepresentation();
        return getTela().getValueOfColumn(column);
    }

    public abstract Object[][] getRows();

    public abstract JMenuItem[] getItens();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the tela
     */
    public ITelaPadrao getTela() {
        return tela;
    }

    /**
     * @param tela the tela to set
     */
    public void setTela(ITelaPadrao tela) {
        this.tela = tela;
    }

    /**
     * @return the bSalvar
     */
    public JButton getbSalvar() {
        return bSalvar;
    }

    /**
     * @return the bRemover
     */
    public JButton getbRemover() {
        return bRemover;
    }

    /**
     * @return the bCancelar
     */
    public JButton getbCancelar() {
        return bCancelar;
    }

    /**
     * @return the fontInput
     */
    public Font getFontInput() {
        return fontInput;
    }

    /**
     * @return the fontInput
     */
    public Font getFontLabel() {
        return fontLabel;
    }

    /**
     * @return the ipanelLientener
     */
    public IPanelListener getIpanelLientener() {
        return ipanelLientener;
    }

}
