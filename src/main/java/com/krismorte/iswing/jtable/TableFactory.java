/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.jtable;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 * Class TableFactory extends JPanel tha contains a configurable JTable. This class
 * depends of TableModel.
 *
 * @author c007329
 */
public class TableFactory extends JPanel implements MyTable {

    private static final long serialVersionUID = 1l;
    private JTable table ;
    private String celula ;
    private TableModel model;
    private boolean ordenavel = false;
    private JScrollPane scrollPane;
    private String[] colunas;
    private Object[][] linhas;

    /**
     * Create a new instance of TableFactory
     *
     * @param colunas String[]
     * @param linhas Object[][]
     */
    private TableFactory(String[] colunas, Object[][] linhas) {
        super(new GridLayout(1, 1));
        this.colunas = colunas;
        this.linhas = linhas;
        this.setOpaque(false);
        setupTable(colunas,linhas,null,null);

    }

    private TableFactory(String[] colunas, Object[][] linhas, TableCellRenderer renderer) {
        super(new GridLayout(1, 1));
        this.colunas = colunas;
        this.linhas = linhas;
        this.setOpaque(false);
        setupTable(colunas,linhas,null,renderer);

    }

    private TableFactory(String[] colunas, Object[][] linhas, int[] col_Editaveis, TableCellRenderer renderer) {
        super(new GridLayout(1, 1));
        this.colunas = colunas;
        this.linhas = linhas;
        this.setOpaque(false);
        setupTable(colunas,linhas,col_Editaveis,renderer);
    }

    private TableFactory(String[] colunas, Object[][] linhas, int[] col_Editaveis) {
        super(new GridLayout(1, 1));
        this.colunas = colunas;
        this.linhas = linhas;
        this.setOpaque(false);
        setupTable(colunas,linhas,col_Editaveis,null);
    }

    private void setupTable(String[] colunas, Object[][] linhas,int[] col_Editaveis, TableCellRenderer renderer){
        if(col_Editaveis!=null){
            model = new TableModel(colunas, linhas,col_Editaveis);
        }else{
            model = new TableModel(colunas, linhas);
        }

        table = new JTable(model);
        table.setAutoCreateRowSorter(true);


        if(renderer !=null){
            try {
                table.setDefaultRenderer(Class.forName("java.lang.String"), renderer);
            } catch (ClassNotFoundException ex) {
                System.exit(0);
            }
        }
        table.setColumnSelectionAllowed(true);

        table.setOpaque(false);

    }

    protected void setupScroll(JPanel panel){
        Dimension bounds= new Dimension(panel.getWidth(), panel.getHeight());
        table.setSize(bounds);
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(bounds);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        this.setVisible(true);

        panel.setToolTipText("Apresentacao de dados");
        panel.removeAll();
        panel.add(this);
        panel.setLayout(new GridLayout(1, 1));
        panel.validate();

    }

    public static TableFactory getInstance(JPanel panel, String[] colunas, Object[][] dados) {

        TableFactory resultados = new TableFactory(colunas, dados);
        resultados.setupScroll(panel);

        return resultados;
    }

    public static TableFactory getInstance(JPanel panel, String[] colunas, Object[][] dados, TableCellRenderer renderer) {
        TableFactory resultados = new TableFactory(colunas, dados, renderer);
        resultados.setupScroll(panel);
        return resultados;
    }

    public static TableFactory getInstance(JPanel panel, JTextField txt, String[] colunas, Object[][] dados) {

        TableFactory resultados = new TableFactory(colunas, dados);
        resultados.setupScroll(panel);
        return resultados;
    }


    public static TableFactory getInstance(int[] num, JPanel panel, String[] colunas, Object[][] dados) {

        TableFactory resultados = new TableFactory(colunas, dados, num);
        resultados.setupScroll(panel);
        return resultados;
    }


    public static TableFactory getInstance(TableFactory resultados, int[] num, JPanel panel, String[] colunas, Object[][] dados) {

        resultados = new TableFactory(colunas, dados, num);
        resultados.setupScroll(panel);
        return resultados;
    }




    public Object[][] search(String texto) {
        Object[][] linhasTmp = null;
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object[] o : linhas) {
            for (int i = 0; i < o.length; i++) {
                if (o[i] != null) {
                    if (o[i].toString().toUpperCase().trim().contains(texto.toUpperCase().trim())) {
                        list.add(o);
                        break;
                    }
                }
            }
        }
        if (list.isEmpty()) {
            linhasTmp = new Object[0][colunas.length];
        } else {
            linhasTmp = new Object[list.size()][colunas.length];

            for (int i = 0; i < list.size(); i++) {
                for (int z = 0; z < colunas.length; z++) {

                    linhasTmp[i][z] = list.get(i)[z];
                }
            }
        }
        return linhasTmp;
    }


    public  Object[][] search(String texto, int coluna) {
        Object[][] linhasTmp = null;
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object[] o : linhas) {
            if (o[coluna] != null) {
                if (o[coluna].toString().toUpperCase().trim().contains(texto.toUpperCase().trim())) {
                    list.add(o);
                }
            }
        }
        if (list.isEmpty()) {
            linhasTmp = new Object[0][colunas.length];
        } else {

            linhasTmp = new Object[list.size()][colunas.length];

            for (int i = 0; i < list.size(); i++) {
                for (int z = 0; z < colunas.length; z++) {
                    linhasTmp[i][z] = list.get(i)[z];
                }
            }
        }
        return linhasTmp;
    }


    public void addMouseListener(MouseListener listener) {
        table.addMouseListener(listener);
    }

    /**
     * says whether JTable is sortable.
     *
     * @return ordenavel a boolean field
     */
    public boolean isOrdenavel() {
        return ordenavel;
    }

    /**
     *
     *
    public void addMouseListener() {

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setTexto(getCelula());
            }
        });
    }*/

    /**
     * set JTable sortable.
     *
     * @param _ordenavel a boolean field
     */
    public void setOrdenavel(boolean _ordenavel) {
        ordenavel = _ordenavel;
        if (_ordenavel == true) {
            // RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
            //   table.setRowSorter(sorter);
        }
    }

    private String getCelula() {
        celula = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
        if (celula.equals("")) {
            celula = null;
        }
        return celula;
    }

    public JTable getJTable(){
        return table;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("teste");
        frame.setPreferredSize(new Dimension(200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] colunas = {"Demanda", "Tipo", "Status", "Resumo"};
        Object[][] linha = {{"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}, {"Não há", "Resultados", "#null#", "#null#"}};
        int[] num = {1, 2};
        //TableFactory tb = new TableFactory(colunas, linha, num);
        //tb.setVisible(true);
        JPanel panel = new JPanel(new GridLayout(1,1));
        TableFactory tb = TableFactory.getInstance(panel,colunas, linha);

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
