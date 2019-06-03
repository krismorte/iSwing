/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import com.krismorte.iswing.util.Conversor;
import com.krismorte.iswing.util.TelaUtil;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.DateTime;

/**
 *
 * @author c007329
 */
public class GenFieldUtil {

    /**
     * Metodo sem Objeto SWING
     *
     * @param anotacao
     * @param objeto
     * @return
     * @throws Exception
     */
    public Object getValorExibicao(GenField anotacao, Object objeto) throws Exception {
        return trataValoExibicao(anotacao, objeto);
    }

    /*
     0 - JTextField
     1 - JTextArea
     2 - JFormattedTextField
     3 - JPasswordField
     4 - JTextPane
     5 - JEditorPane
     6 - JRadioButton
     7 - JCheckBox
     8 - JComboBox
     */
    public Object getValor(GenField anotacao, Object objeto) throws Exception {
        Object objReturn = null;
        if (anotacao.fieldType() == GenField.JTEXTFIELD) {
            if (anotacao.fieldButton()) {
                String vlrTmp = ComponenteTela.extractJTextFieldFromJPanel((JPanel) objeto).getText();
                objReturn = trataValorObjeto(anotacao, vlrTmp);
            } else {
                //System.out.println("" + anotacao.fieldText());
                String vlrTmp = ((JTextField) objeto).getText();
                objReturn = trataValorObjeto(anotacao, vlrTmp);
            }
        } else if (anotacao.fieldType() == GenField.JTEXTAREA) {
            String vlrTmp = ((JTextArea) objeto).getText();
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JFORMATTEDTEXTFIELD) {
            String vlrTmp = ((JFormattedTextField) objeto).getText();
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JPASSWORDFIELD) {
            String vlrTmp = TelaUtil.getSenha((JPasswordField) objeto);
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JTEXTPANE) {
            String vlrTmp = ((JTextPane) objeto).getText();
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JEDITORPANE) {
            String vlrTmp = ((JEditorPane) objeto).getText();
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JCOMBOBOX) {
            if (anotacao.fieldButton()) {
                Object vlrTmp = null;
                vlrTmp = ComponenteTela.extractJComboBoxFromJPanel((JPanel) objeto).getSelectedItem();;
                objReturn = trataValorObjeto(anotacao, vlrTmp);
            } else {
                Object vlrTmp = ((JComboBox) objeto).getSelectedItem();
                objReturn = trataValorObjeto(anotacao, vlrTmp);
            }
        } else if (anotacao.fieldType() == GenField.JDATEPICKER) {
            Object vlrTmp = ((JXDatePicker) objeto).getDate();
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        } else if (anotacao.fieldType() == GenField.JRADIONBUTTON) {
            Component[] cmps = ((JPanel) objeto).getComponents();
            String labelEscolhido = "";
            for (Component c : cmps) {
                if (c instanceof JRadioButton) {
                    JRadioButton btnTmp = ((JRadioButton) c);
                    if (btnTmp.isSelected()) {
                        labelEscolhido = btnTmp.getText();
                    }
                }
            }
            int ind = 0;
            for (int i = 0; i < anotacao.fieldLabels().length; i++) {
                if (anotacao.fieldLabels()[1].equals(labelEscolhido)) {
                    ind = i;
                }
            }
            Object vlrTmp = anotacao.fieldOptions()[ind];
            objReturn = trataValorObjeto(anotacao, vlrTmp);
        }
        return objReturn;
    }

    public void setValor(GenField anotacao, Object objeto, Object valor) throws Exception {
        if (anotacao.fieldType() == GenField.JTEXTFIELD) {
            if (valor == null) {
                valor = "";
            }
            if (anotacao.fieldButton()) {
                ComponenteTela.extractJTextFieldFromJPanel((JPanel) objeto).setText(valor.toString());
            } else {
                ((JTextField) objeto).setText(valor.toString());
            }
        } else if (anotacao.fieldType() == GenField.JTEXTAREA) {
            if (valor == null) {
                valor = "";
            }
            ((JTextArea) objeto).setText(valor.toString());
        } else if (anotacao.fieldType() == GenField.JFORMATTEDTEXTFIELD) {
            if (valor == null) {
                valor = "";
            }
            ((JFormattedTextField) objeto).setText(valor.toString());
        } else if (anotacao.fieldType() == GenField.JPASSWORDFIELD) {
            if (valor == null) {
                valor = "";
            }
            ((JPasswordField) objeto).setText(valor.toString());
        } else if (anotacao.fieldType() == GenField.JTEXTPANE) {
            if (valor == null) {
                valor = "";
            }
            ((JTextPane) objeto).setText(valor.toString());
        } else if (anotacao.fieldType() == GenField.JEDITORPANE) {
            if (valor == null) {
                valor = "";
            }
            ((JEditorPane) objeto).setText(valor.toString());
        } else if (anotacao.fieldType() == GenField.JCOMBOBOX) {
            if (anotacao.fieldButton()) {
                ComponenteTela.extractJComboBoxFromJPanel((JPanel) objeto).setSelectedItem(valor);
            } else {
                ((JComboBox) objeto).setSelectedItem(valor);
            }
        } else if (anotacao.fieldType() == GenField.JDATEPICKER) {
            ((JXDatePicker) objeto).setDate(Conversor.stringToDate(valor.toString()));
        } else if (anotacao.fieldType() == GenField.JRADIONBUTTON) {
            Component[] cmps = ((JPanel) objeto).getComponents();
            String labelEscolhido = "";
            for (Component c : cmps) {
                if (c instanceof JRadioButton) {
                    JRadioButton btnTmp = ((JRadioButton) c);
                    if (btnTmp.isSelected()) {
                        labelEscolhido = btnTmp.getText();
                    }
                }
            }
            int ind = 0;
            for (int i = 0; i < anotacao.fieldLabels().length; i++) {
                if (anotacao.fieldLabels()[1].equals(labelEscolhido)) {
                    ind = i;
                }
            }
            Object vlrTmp = anotacao.fieldOptions()[ind];
        }
    }

    private Object trataValorObjeto(GenField anotacao, Object valor) throws Exception {
        //System.out.println("CONVERT: " + valor.toString());

        if (valor != null) {
            if (anotacao.type() == GenField.CHAR_STRING) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.CHAR_STRING_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.CHAR_STRING_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.BYTE) {
                byte[] vlr = null;
                if (valor instanceof String) {
                    vlr = extrairBytes(new File(valor.toString()));
                } else if (valor instanceof File) {
                    vlr = extrairBytes((File) valor);
                }
                verificaObjetoNulo(anotacao, vlr);
                return vlr;
            } else if (anotacao.type() == GenField.BOOLEAN_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.BYTE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.INT) {
                verificaObjetoNulo(anotacao, valor);
                if (valor instanceof Integer) {
                    return valor;
                } else {//primitive type
                    return Conversor.stringToInt(valor.toString());
                }

            } else if (anotacao.type() == GenField.INT_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.INT_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DOUBLE) {
                verificaObjetoNulo(anotacao, valor);
                if (valor instanceof Double) {
                    return valor;
                } else {//primitive type
                    return Conversor.stringToDouble(valor.toString());
                }
            } else if (anotacao.type() == GenField.DOUBLE_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DOUBLE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.LONG) {
                verificaObjetoNulo(anotacao, valor);
                return Conversor.stringToLong(valor.toString());
            } else if (anotacao.type() == GenField.LONG_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.LONG_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DATE) {

                if (valor instanceof Date) {
                    valor = (Date) valor;
                } else if (valor instanceof Calendar) {
                    valor = (Calendar) valor;
                } else if (valor instanceof LocalDate) {
                    //System.out.println("Date: " + ((Date) valor).toString());
                    //System.out.println("LocalDate: " + ((LocalDate) valor).toString());
                    //valor = dateFormat.format((LocalDate) valor);
                    valor = (LocalDate) valor;
                    //valor = Date.valueOf(((LocalDate) valor));
                } else if (valor instanceof LocalDateTime) {
                    //System.out.println("LocalDateTime: " + ((LocalDateTime) valor).toString());
                    //valor = Conversor.localDateTimeToDate((LocalDateTime) valor);
                    valor = (LocalDateTime) valor;

                } else if (valor instanceof DateTime) {
                    //System.out.println("DateTime: " + (DateTime) valor);
                    valor = (DateTime) valor;
                } else if (valor instanceof String) {
                    //System.out.println("Date: " + (String) valor);
                    valor = Conversor.stringToDate((String) valor);
                }

                verificaObjetoNulo(anotacao, valor);
                return valor;//Conversor.stringToDate(valor.toString());
            } else if (anotacao.type() == GenField.DATE_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DATE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT) {
                //System.out.println("OBJECT: " + valor.toString());
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else {
                return valor;
            }
        } else {
            return null;
        }
    }

    private Object trataValoExibicao(GenField anotacao, Object valor) throws Exception {
        //System.out.println("CONVERT: " + valor.toString());
        if (valor != null) {
            if (anotacao.type() == GenField.CHAR_STRING) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.CHAR_STRING_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.CHAR_STRING_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.BYTE) {
                byte[] vlr = null;
                if (valor instanceof String) {
                    vlr = extrairBytes(new File(valor.toString()));
                } else if (valor instanceof File) {
                    vlr = extrairBytes((File) valor);
                }
                verificaObjetoNulo(anotacao, vlr);
                return vlr;
            } else if (anotacao.type() == GenField.BOOLEAN_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.BYTE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.INT) {
                verificaObjetoNulo(anotacao, valor);
                if (valor instanceof Integer) {
                    return valor;
                } else {//primitive type
                    return Conversor.stringToInt(valor.toString());
                }

            } else if (anotacao.type() == GenField.INT_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.INT_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DOUBLE) {
                verificaObjetoNulo(anotacao, valor);
                if (valor instanceof Double) {
                    return valor;
                } else {//primitive type
                    return Conversor.stringToDouble(valor.toString());
                }
            } else if (anotacao.type() == GenField.DOUBLE_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DOUBLE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.LONG) {
                verificaObjetoNulo(anotacao, valor);
                return Conversor.stringToLong(valor.toString());
            } else if (anotacao.type() == GenField.LONG_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.LONG_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DATE) {
                SimpleDateFormat dateFormat = null;
                if (anotacao.dateTimeFormat().equals("")) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                } else {
                    dateFormat = new SimpleDateFormat(anotacao.dateTimeFormat());
                }

                if (valor instanceof Date) {
                    valor = dateFormat.format((Date) valor);
                } else if (valor instanceof Calendar) {
                    //valor = dateFormat.format(((Calendar) valor).getTime());
                    valor = dateFormat.format(Date.from(((Calendar) valor).toInstant()));
                } else if (valor instanceof LocalDate) {
                    valor = dateFormat.format(Date.from(((LocalDate) valor).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else if (valor instanceof LocalDateTime) {
                    valor = dateFormat.format(Date.from(((LocalDateTime) valor).atZone(ZoneId.systemDefault()).toInstant()));
                } else if (valor instanceof DateTime) {
                    valor = dateFormat.format(((DateTime) valor).toDate());
                } else if (valor instanceof String) {
                    valor = dateFormat.format(Conversor.stringToDate((String) valor));
                }
                verificaObjetoNulo(anotacao, valor);
                return valor;//Conversor.stringToDate(valor.toString());
            } else if (anotacao.type() == GenField.DATE_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.DATE_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT) {
                //System.out.println("OBJECT: " + valor.toString());
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT_ARRAY) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else if (anotacao.type() == GenField.OBJECT_MATRIX) {
                verificaObjetoNulo(anotacao, valor);
                return valor;
            } else {
                return valor;
            }
        } else {
            return null;
        }
    }

    private boolean verificaObjetoNulo(GenField anotacao, Object valor) throws Exception {
        if (anotacao.nullable() == false) {
            if (valor == null) {
                throw new Exception(anotacao.columnName() + " cannot be null.");
            }
        }
        return true;
    }

    public byte[] extrairBytes(File arquivo) throws IOException {
        FileInputStream put = new FileInputStream(arquivo);
        byte[] image = new byte[(int) arquivo.length()];
        put.read(image);
        return image;
    }

}
