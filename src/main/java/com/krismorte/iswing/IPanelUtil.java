/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import com.krismorte.iswing.file.ExcelModel;
import com.krismorte.iswing.file.PdfModel;
import com.krismorte.iswing.util.TelaUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author c007329
 */
public class IPanelUtil {

    private GenFieldUtil genFieldUtil = new GenFieldUtil();

    public Object[][] loadLines(List<ComponenteTela> componentes, String[] colunas, List list) throws Exception {

        Object[][] linhas = new Object[list.size()][colunas.length];
        int lin = 0;
        int col = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                col = 0;
                for (ComponenteTela c : componentes) {
                    Field f = obj.getClass().getDeclaredField(c.getCampo().getName());
                    f.setAccessible(true);
                    //linhas[lin][col] = f.get(obj);
                    linhas[lin][col] = genFieldUtil.getValorExibicao(c.getAnotacao(), f.get(obj));
                    col++;
                }
                lin++;
            }

        }

        return linhas;
    }

    public Object[][] loadLinesByView(List<ComponenteTela> componentes, String[] colunas, List list, String view) throws Exception {
        Object[][] linhas = new Object[list.size()][colunas.length];
        int lin = 0;
        int col = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                col = 0;
                for (ComponenteTela c : componentes) {
                    for (String v : c.getAnotacao().views()) {
                        if (v.toUpperCase().equals(view.toUpperCase())) {
                            if (c.getAnotacao().isRepresentation()) {//the toString() method is mandatory
                                linhas[lin][col] = obj;
                            } else {
                                Field f = obj.getClass().getDeclaredField(c.getCampo().getName());
                                f.setAccessible(true);
                                //linhas[lin][col] = f.get(obj);
                                linhas[lin][col] = genFieldUtil.getValorExibicao(c.getAnotacao(), f.get(obj));
                            }
                            col++;
                        }
                    }
                }
                lin++;
            }
        }

        return linhas;
    }

    public String[] loadColumns(List<ComponenteTela> componentes) {

        List<String> colTmp = new ArrayList<>();
        for (ComponenteTela c : componentes) {
            if (c.getAnotacao().columnName().equals("")) {
                colTmp.add(c.getCampo().getName());
            } else {
                colTmp.add(c.getAnotacao().columnName());
            }
        }
        String colunas[] = new String[colTmp.size()];
        for (int i = 0; i < colTmp.size(); i++) {
            colunas[i] = colTmp.get(i);
        }
        return colunas;
    }

    public String[] loadColumnsByView(List<ComponenteTela> componentes, String view) {

        List<String> colTmp = new ArrayList<>();
        for (ComponenteTela c : componentes) {
            for (String v : c.getAnotacao().views()) {
                if (v.toUpperCase().equals(view.toUpperCase())) {
                    if (c.getAnotacao().columnName().equals("")) {
                        colTmp.add(c.getCampo().getName());
                    } else {
                        colTmp.add(c.getAnotacao().columnName());
                    }
                }
            }
        }
        String colunas[] = new String[colTmp.size()];
        for (int i = 0; i < colTmp.size(); i++) {
            colunas[i] = colTmp.get(i);
        }
        return colunas;
    }

    public int getColumnWithObjectRepresentation(List<ComponenteTela> componentes, String view) {
        int column = 0;
        comList:
        for (ComponenteTela c : componentes) {
            for (String v : c.getAnotacao().views()) {
                if (v.toUpperCase().equals(view.toUpperCase())) {
                    if (c.getAnotacao().isRepresentation()) {
                        break comList;
                    } else {
                        column++;
                    }
                }
            }
        }
        return column;
    }

    public Object createNewInstance(List<ComponenteTela> componentes, Class c) throws Exception {
        Object obj = c.newInstance();
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().fieldEnable() && comp.getAnotacao().showOnCRUD()) {
                Field f2 = obj.getClass().getDeclaredField(comp.getCampo().getName());
                f2.setAccessible(true);
                f2.set(obj, comp.getValor());
            }
        }
        return obj;
    }

    public Object updateInstance(List<ComponenteTela> componentes, Object obj) throws Exception {
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().fieldEnable() && comp.getAnotacao().showOnCRUD()) {
                Field f2 = obj.getClass().getDeclaredField(comp.getCampo().getName());
                f2.setAccessible(true);
                f2.set(obj, comp.getValor());
            }
        }
        return obj;
    }

    public Object updateAttributeInstance(ComponenteTela componente, Object obj) throws Exception {
        if (componente.getAnotacao().fieldEnable() && componente.getAnotacao().showOnCRUD()) {
            Field f2 = obj.getClass().getDeclaredField(componente.getCampo().getName());
            f2.setAccessible(true);
            f2.set(obj, componente.getValor());
        }
        return obj;
    }

    public Object updateAttributeInstance(ComponenteTela componente, Object instance, Object value) throws Exception {
        Field f2 = instance.getClass().getDeclaredField(componente.getCampo().getName());
        f2.setAccessible(true);
        f2.set(instance, componente.getValor(value));
        return instance;
    }

    public Object updateAttributeInstance(List<ComponenteTela> componentes, Object instance, Object value) throws Exception {
        for (ComponenteTela comp : componentes) {
            Field f2 = instance.getClass().getDeclaredField(comp.getCampo().getName());
            f2.setAccessible(true);
            f2.set(instance, comp.getValor(value));
        }
        return instance;
    }

    public void showAttributes(List<ComponenteTela> componentes, Object obj) throws Exception {
        for (ComponenteTela comp : componentes) {
            if (comp.getAnotacao().showOnCRUD()) {
                Field f2 = obj.getClass().getDeclaredField(comp.getCampo().getName());
                f2.setAccessible(true);
                comp.setValor(f2.get(obj));
            }
        }
    }

    public List<ComponenteTela> loadAttributes(Class C) {
        List<ComponenteTela> componentes = new ArrayList<>();
        for (Field f : C.getDeclaredFields()) {
            if (f.isAnnotationPresent(GenField.class)) {
                GenField anotacao = f.getAnnotation(GenField.class);
                componentes.add(new ComponenteTela(anotacao, f, null));
            }
        }
        return componentes;
    }

    public boolean exportToExcel(File filePath, String[] colunas, Object[][] linhas, String errorMsg, String errorMsg2, String fileName) throws Exception {
        if (linhas == null || linhas.length < 0) {
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        } else {
            File file = TelaUtil.getDirectoryPath(filePath.getAbsolutePath());
            if (file == null) {
                JOptionPane.showMessageDialog(null, errorMsg2);
            }
            ExcelModel excel = new ExcelModel();
            return excel.gerarArquivo(fileName, file.getAbsolutePath() + "\\" + fileName, colunas, linhas);
        }
    }

    public boolean exportToPdf(File filePath, String[] colunas, Object[][] linhas, String errorMsg, String errorMsg2, String fileName) throws Exception {
        if (linhas == null || linhas.length < 0) {
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        } else {
            File file = TelaUtil.getDirectoryPath(filePath.getAbsolutePath());//getFilePath("pdf");
            if (file == null) {
                JOptionPane.showMessageDialog(null, errorMsg2);
            }
            PdfModel pdf = new PdfModel();
            return pdf.gerarArquivo(fileName, file.getAbsolutePath() + "\\" + fileName, colunas, linhas);
        }
    }

    public boolean exportToExcel(String filePath, String[] colunas, Object[][] linhas, String errorMsg, String errorMsg2, String fileName) throws Exception {
        return exportToExcel(new File(filePath), colunas, linhas, errorMsg, errorMsg2, fileName);
    }

    public boolean exportToPdf(String filePath, String[] colunas, Object[][] linhas, String errorMsg, String errorMsg2, String fileName) throws Exception {
        return exportToPdf(new File(filePath), colunas, linhas, errorMsg, errorMsg2, fileName);
    }

}
