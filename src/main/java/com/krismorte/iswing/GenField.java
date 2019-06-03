/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author c007329
 */
@Target(ElementType.FIELD)

@Retention(RetentionPolicy.RUNTIME)

/*
 @target{Eelement.type, elementType.method, elementType.filed, elementType.parameter}

 */
public @interface GenField {
    /*Types*/

    public final static int CHAR_STRING = 0;
    public final static int CHAR_STRING_ARRAY = 10;
    public final static int CHAR_STRING_MATRIX = 20;
    public final static int INT = 1;
    public final static int INT_ARRAY = 11;
    public final static int INT_MATRIX = 21;
    public final static int BYTE = 2;
    public final static int BYTE_ARRAY = 12;
    public final static int BYTE_MATRIX = 22;
    public final static int SHORT = 3;
    public final static int SHORT_ARRAY = 13;
    public final static int SHORT_MATRIX = 23;
    public final static int LONG = 4;
    public final static int LONG_ARRAY = 14;
    public final static int LONG_MATRIX = 24;
    public final static int FLOAT = 5;
    public final static int FLOAT_ARRAY = 15;
    public final static int FLOAT_MATRIX = 25;
    public final static int DOUBLE = 6;
    public final static int DOUBLE_ARRAY = 16;
    public final static int DOUBLE_MATRIX = 26;
    public final static int BOOLEAN = 7;
    public final static int BOOLEAN_ARRAY = 17;
    public final static int BOOLEAN_MATRIX = 27;
    public final static int OBJECT = 8;
    public final static int OBJECT_ARRAY = 18;
    public final static int OBJECT_MATRIX = 28;
    public final static int DATE = 9;
    public final static int DATE_ARRAY = 19;
    public final static int DATE_MATRIX = 29;
    /*OBJECT*/
    public final static int JTEXTFIELD = 100;
    public final static int JTEXTAREA = 101;
    public final static int JFORMATTEDTEXTFIELD = 102;
    public final static int JPASSWORDFIELD = 103;
    public final static int JTEXTPANE = 104;
    public final static int JEDITORPANE = 105;
    public final static int JRADIONBUTTON = 106;
    public final static int JCHECKBOX = 107;
    public final static int JCOMBOBOX = 108;
    public final static int JDATEPICKER = 109;

    /**
     * <table>
     * <tr><td>0 - char/String</td><td>10 - char/String[]</td><td>20 -
     * char/String[][]</td></tr>
     * <tr><td>1 - int/Integer</td><td>11 - int/Integer[]</td><td>21 -
     * int/Integer[][]</td></tr>
     * <tr><td>2 - byte</td><td>12 - byte[]</td><td>22 - byte[][]</td></tr>
     * <tr><td>3 - short</td><td>13 - short[]</td><td>23 - short[][]</td></tr>
     * <tr><td>4 - long</td><td>14 - long[]</td><td>24 - long[][]</td></tr>
     * <tr><td>5 - float</td><td>15 - float[]</td><td>25 float[][]</td></tr>
     * <tr><td>6 - double</td><td>16 - double[]</td><td>26 -
     * double[][]</td></tr>
     * <tr><td>7 - boolean</td><td>17 - boolean[]</td><td>27 -
     * boolean[][]</td></tr>
     * <tr><td>8 - Object</td><td>18 - Object[]</td><td>28 -
     * Object[][]</td><td>9 - Date</td><td>19 - Date[]</td><td>29 -
     * Date[][]</td></tr>
     * </table>
     *
     * @return
     */
    int type() default GenField.CHAR_STRING;

    int order() default -1;

    boolean nullable() default true;

    boolean showOnCRUD() default true;

    boolean isRepresentation() default false;

    String[] views() default "";

    String columnName() default "";

    String fieldText() default "";

    int fieldSize() default 5;

    int fieldTabIndex() default 0;

    /**
     * <table>
     * <tr><td>100 - JTextField</td><td>101 - JTextArea</td><td>102 -
     * JFormattedTextField</td></tr>
     * <tr><td>103 - JPasswordField</td><td>104 - JTextPane</td><td>105 -
     * JEditorPane</td></tr>
     * <tr><td>106 - JRadioButton</td><td>107 - JCheckBox</td><td>108 -
     * JComboBox</td><tr><td>109 - JDatePicker</td><td>110 -
     * LGDATEPICKER</td><td>111 - LGDATETIMEPICKER</td></tr>
     * </table>
     *
     * @return
     */
    int fieldType() default GenField.JTEXTFIELD;

    String[] fieldLabels() default "";

    String[] fieldOptions() default "";

    boolean fieldEditable() default true;

    boolean fieldEnable() default true;

    String fieldDateFormat() default "";

    String fieldTip() default "";

    boolean fieldButton() default false;
    
    String dateTimeFormat() default "";

}
