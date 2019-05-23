/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import com.krismorte.iswing.jtable.Tabela;
import com.krismorte.iswing.util.EventoMouseMenuPopup;
import com.krismorte.iswing.util.TelaUtil;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;


/**
 *
 * @author krisnamourtscf
 */
public class TelaConsultas extends javax.swing.JDialog implements iTelaConsulta {

    private IPanelUtil ipanelUtil = new IPanelUtil();
    private List<ComponenteTela> listaComp;
    private String[] colunas;
    private Object[][] linhas;
    private Object[][] linhasExport;//linhas para exportar em excel
    private List lista;
    private iBusiness business;
    private JTable tabela;
    private MenuPopup menu;

    public TelaConsultas() {

    }

    /**
     * Creates new form TelaConsultas
     */
    public TelaConsultas(String titulo, Font font, Class c, iBusiness business, MenuPopup menu) {
        super(new java.awt.Frame(), true);
        initComponents();
        this.business = business;
        this.menu = menu;
        setTitle(titulo);
        iniciaTela(c);
        if (menu != null) {
            this.menu.setTela(this);
        }
    }

    private void iniciaTela(Class c) {
        listaComp = ipanelUtil.loadAttributes(c);
        colunas = ipanelUtil.loadColumnsByView(listaComp, "");
        TelaUtil.preencheBox(boxColunas, "Todas", colunas);
        iniciaRadio();
        atualizaTabela();
        iniciaMenuExport();
        iniciaTexto();
        iniciaAtalho();
    }

    private void iniciaRadio() {
        TelaUtil.iniciaAbstractButton(radAtivos, radTodos);
        radAtivos.setSelected(true);
    }

    private void iniciaMenuExport() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Arquivo");
        JMenuItem export = new JMenuItem("Excel");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarExcel();
            }
        });
        menu.add(export);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void iniciaTexto() {
        IPanelListener iPanelListener = new IPanelListener();
        txtFiltro.addFocusListener(iPanelListener.getDefaultFocusListener(txtFiltro));
        txtFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFiltro.doClick();
            }
        });
        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (txtFiltro.getText().equals("")) {
                        btnFiltro.doClick();
                    }
                }
            }
        });
    }

    private void iniciaAtalho() {
        ActionListener actionCarregar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnCarregar.doClick();
            }
        };

        JRootPane rootPane = new JRootPane();
        KeyStroke kCarregar = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_MASK);
        rootPane.registerKeyboardAction(actionCarregar, kCarregar, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public int getColumn() {
        return ipanelUtil.getColumnWithObjectRepresentation(listaComp, "");
    }

    public JTable getJTable() {
        return tabela;
    }

    private void atualizaTabela() {
        try {
            if (radTodos.isSelected()) {
                lista = business.selecionarTodos(null);
            } else {
                lista = business.selecionarTodos(true);
            }
            linhas = ipanelUtil.loadLinesByView(listaComp, colunas, lista, "");
            linhasExport = linhas;
            Tabela.preencheTabela(panelResultados, colunas, linhas);
            tabela = Tabela.table;
            if (menu != null) {
                Tabela.adicionaMouseListener(new EventoMouseMenuPopup(menu.getPopup(), tabela));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void atualizaTabela(Object[][] linhas) {
        try {
            linhasExport = linhas;
            Tabela.preencheTabela(panelResultados, colunas, linhas);
            tabela = Tabela.table;
            if (menu != null) {
                Tabela.adicionaMouseListener(new EventoMouseMenuPopup(menu.getPopup(), tabela));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean exportarExcel() {
        try {
            return ipanelUtil.exportToExcel("NewFile.xls", colunas, linhasExport, "Sem dados a exportar", "Arquivo nulo", "Resultado das Consulta");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radTodos = new javax.swing.JRadioButton();
        radAtivos = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        boxColunas = new javax.swing.JComboBox<>();
        btnCarregar = new javax.swing.JButton();
        panelResultados = new javax.swing.JPanel();
        btnFiltro = new javax.swing.JButton();
        txtFiltro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        radTodos.setText("Todos");

        radAtivos.setText("Somente ativos");

        jLabel1.setText("Colunas:");

        btnCarregar.setText("carregar");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        panelResultados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelResultadosLayout = new javax.swing.GroupLayout(panelResultados);
        panelResultados.setLayout(panelResultadosLayout);
        panelResultadosLayout.setHorizontalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelResultadosLayout.setVerticalGroup(
            panelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        btnFiltro.setText("filtrar");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radAtivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCarregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxColunas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltro)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radTodos)
                    .addComponent(radAtivos)
                    .addComponent(btnCarregar)
                    .addComponent(jLabel1)
                    .addComponent(boxColunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltro)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        atualizaTabela();
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        if (txtFiltro.getText().equals("")) {
            atualizaTabela();
        } else {
            Object[][] linhasTmp = null;
            if (boxColunas.getSelectedIndex() == 0) {
                linhasTmp = Tabela.buscaValores(colunas, linhas, txtFiltro.getText());
            } else {
                linhasTmp = Tabela.buscaValores(colunas, linhas, txtFiltro.getText(), boxColunas.getSelectedIndex() - 1);
            }
            atualizaTabela(linhasTmp);
        }
    }//GEN-LAST:event_btnFiltroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaConsultas dialog = new TelaConsultas();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxColunas;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JRadioButton radAtivos;
    private javax.swing.JRadioButton radTodos;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
