/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * JDialogPadrao.java
 *
 * Created on 03/06/2015, 22:57:21
 */
package com.krismorte.iswing;

import com.krismorte.iswing.jtable.Tabela;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author c007329
 */
public class JDialogPadrao extends javax.swing.JDialog implements ITelaPadrao {

    private JPopupMenu menuFlutuante = new JPopupMenu();
    private IPanelUtil iPanelUtil = new IPanelUtil();
    private JMenuItem[] itens;
    private IPanelDados iPanel;
    private JTable tabela;
    private String[] colunas;
    private Object[][] linhas;

    public JDialogPadrao() {
    }

    /**
     * Creates new form JDialogPadrao
     */
    public JDialogPadrao(java.awt.Frame parent, boolean modal, IPanelDados iPanel) {
        super(parent, modal);
        this.iPanel = iPanel;
        this.iPanel.setTela(this);
        initComponents();
        this.setTitle(iPanel.getTitle());
        resizeGui();
        panelDados.add(iPanel);
        panelDados.setLayout(new GridLayout(1, 1));
        inicializaTela();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /*
     Implements screen shortcuts
     */
    @Override
    protected JRootPane createRootPane() {
        ActionListener actionEsc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        };

        ActionListener actionSave = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (iPanel.getbSalvar().isEnabled()) {
                    iPanel.getbSalvar().doClick();
                }
            }
        };
        ActionListener actionRemove = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (iPanel.getbRemover().isEnabled()) {
                    iPanel.getbRemover().doClick();
                }
            }
        };
        ActionListener actionCancel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (iPanel.getbCancelar().isEnabled()) {
                    iPanel.getbCancelar().doClick();
                }
            }
        };

        JRootPane rootPane = new JRootPane();
        KeyStroke kEsc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke kSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK);
        KeyStroke kRemove = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.ALT_MASK);
        KeyStroke kCancel = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_MASK);
        rootPane.registerKeyboardAction(actionEsc, kEsc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(actionSave, kSave, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(actionRemove, kRemove, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(actionCancel, kCancel, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

    public void setColumnsRows(String[] colunas, Object[][] linhas) {
        this.colunas = colunas;
        this.linhas = linhas;
    }

    public void setJLabelTexts(String labelSearch, String buttonSeach, String labelTotal) {
        labelBuscar.setText(labelSearch);
        labTota.setText(labelTotal);
        btnBuscar.setText(buttonSeach);

    }

    private void inicializaTela() {
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);
        labTota.setFont(iPanel.getFontLabel());
        labelBuscar.setFont(iPanel.getFontLabel());
        btnBuscar.setFont(iPanel.getFontLabel());
        txtBuscar.setFont(iPanel.getFontInput());
        txtTotal.setFont(iPanel.getFontInput());

        txtBuscar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                btnBuscar.doClick();
            }
        });
        txtBuscar.addFocusListener(iPanel.getIpanelLientener().getDefaultFocusListener(txtBuscar));
        this.setLocationRelativeTo(null);
        if (iPanel.getItens() != null) {
            if (iPanel.getItens().length > 0) {
                addJMenuItens(iPanel.getItens());
            }
        }
        refreshTable();
        this.setVisible(true);
    }

    @Override
    public void resizeGui() {
        if (iPanel.getComponentes().size() > 5) {
            this.setSize(800, 700);
        }
    }

    private void refreshObjects() {
        linhas = iPanel.getRows();
        colunas = iPanel.getColumns();
    }

    @Override
    public void refreshTable() {
        refreshObjects();
        refreshTable(colunas, linhas);
        /*Tabela.preencheTabela(panelResultados, txtTotal, colunas, linhas);
         //Tabela.adicionaMouseListener(new EventoMouse());
         tabela = Tabela.table;*/
    }

    private void refreshTable(String[] colunas, Object[][] linhas) {
        this.linhas = linhas;
        Tabela.preencheTabela(panelResultados, txtTotal, colunas, linhas);
        Tabela.adicionaMouseListener(new EventoMouse());
        tabela = Tabela.table;
        tabela.getTableHeader().setFont(iPanel.getFontLabel());
        tabela.setFont(iPanel.getFontInput());
    }

    @Override
    public Object getValueOfColumn(int column) {
        int linha = tabela.getSelectedRow();
        return tabela.getValueAt(linha, column);
    }

    @Override
    public Object[] getValueOfSelectedRow() {
        int linha = tabela.getSelectedRow();
        Object[] line = new Object[colunas.length];
        for (int i = 0; i < colunas.length; i++) {
            line[i] = tabela.getValueAt(linha, i);
        }
        return line;
    }

    @Override
    public void addJMenuItens(JMenuItem... item) {
        itens = item;
        for (JMenuItem i : itens) {
            menuFlutuante.add(i);
        }
        refreshTable();
    }

    @Override
    public boolean exportToPdf(File filePath) {
        try {
            return iPanelUtil.exportToPdf(filePath, colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }

        /*if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = filePath;//getFilePath("pdf");
         PdfModel pdf = new PdfModel();
         return pdf.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public boolean exportToExcel(File filePath) {
        try {
            return iPanelUtil.exportToExcel(filePath, colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }
        /*if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = filePath;//getFilePath("xls");
         ExcelModel excel = new ExcelModel();
         *  return excel.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public boolean exportToPdf(String filename) {
        try {
            return iPanelUtil.exportToPdf(filename, colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }
        /*if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = TelaUtil.getDirectoryPath(filename);//getFilePath("pdf");
         PdfModel pdf = new PdfModel();
         return pdf.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public boolean exportToExcel(String filename) {
        try {
            return iPanelUtil.exportToExcel(filename, colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }
        /*if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = TelaUtil.getDirectoryPath(filename);//getFilePath("xls");
         ExcelModel excel = new ExcelModel();
         return excel.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public boolean exportToPdf() {
        try {
            return iPanelUtil.exportToPdf("NewFile.pdf", colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }
        /* if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = TelaUtil.getDirectoryPath("NewFile.pdf");//getFilePath("pdf");
         PdfModel pdf = new PdfModel();
         return pdf.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public boolean exportToExcel() {
        try {
            return iPanelUtil.exportToExcel("NewFile.xls", colunas, linhas, "Sem dados a exportar", "Arquivo nulo", "Resultado das Demandas");
        } catch (Exception e) {
            return false;
        }
        /*if (linhas == null || linhas.length < 0) {
         JOptionPane.showMessageDialog(null, "Sem dados a exportar");
         return false;
         } else {
         File file = TelaUtil.getDirectoryPath("NewFile.xls");//getFilePath("xls");
         ExcelModel excel = new ExcelModel();
         return excel.gerarArquivo("Resultado das Demandas", file.getAbsolutePath(), colunas, linhas);
         }*/
    }

    @Override
    public void addKeyEvent(KeyAdapter event) {
        this.addKeyListener(event);
    }

    @Override
    public void addMouseEvent(MouseAdapter event) {
        this.addMouseListener(event);
    }

    @Override
    public void addWindowsEvent(WindowAdapter event) {
        this.addWindowListener(event);
    }

    @Override
    public JTable getTable() {
        return tabela;
    }

    @Override
    public Object getParentIPanel() {
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDados = new javax.swing.JPanel();
        labelBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        labTota = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        panelResultados = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelDadosLayout = new javax.swing.GroupLayout(panelDados);
        panelDados.setLayout(panelDadosLayout);
        panelDadosLayout.setHorizontalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        panelDadosLayout.setVerticalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        labelBuscar.setText("buscar por:");

        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        labTota.setText("Total:");

        panelResultados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelResultadosLayout = new javax.swing.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                        .addComponent(labTota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(labTota)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBuscar.getText().equals("")) {
            refreshTable();
        } else {
            Object[][] linhasTmp = Tabela.buscaValores(colunas, linhas, txtBuscar.getText());
            refreshTable(colunas, linhasTmp);
        }
}//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JDialogPadrao dialog = new JDialogPadrao(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel labTota;
    private javax.swing.JLabel labelBuscar;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    public class EventoMouse implements MouseListener {

        public void mouseClicked(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (itens != null) {
                    if (itens.length > 0) {
                        menuFlutuante.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
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
}
