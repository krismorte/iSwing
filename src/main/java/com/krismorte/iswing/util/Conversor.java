/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author c007329
 */
public class Conversor {

    public static final String DATE_MASK_BRAZILIAN_HYPHEN = "dd-MM-YYYY";
    public static final String DATE_MASK_BRAZILIAN_BAR = "dd/MM/YYYY";
    public static final String DATE_MASK_AMERICAN_HYPHEN = "YYYY-MM-dd";
    public static final String DATE_MASK_AMERICAN_BAR = "YYYY/MM/dd";
    public static final String DATETIME_MASK_BRAZILIAN_HYPHEN = "dd-MM-YYYY HH:mm:ss";
    public static final String DATETIME_MASK_BRAZILIAN_BAR = "dd/MM/YYYY HH:mm:ss";
    public static final String DATETIME_MASK_AMERICAN_HYPHEN = "YYYY-MM-dd HH:mm:ss";
    public static final String DATETIME_MASK_AMERICAN_BAR = "YYYY/MM/dd HH:mm:ss";

    public static String stringToDoubleString(String vlr) {
        String numb = vlr.replace(".", "");
        return numb.replace(",", ".");
    }

    public static String doubleToString(Double vlr) {
        return String.format("%.2f", vlr).replace(",", ".");
    }

    public static int stringToInt(String vlr) throws Exception {
        if (isInteger(vlr)) {
            return Integer.parseInt(vlr);
        } else {
            throw new Exception(Conversor.class.getCanonicalName()+"\nNúmero Inválido! Value: " + vlr); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static Long stringToLong(String vlr) throws Exception {
        if (isInteger(vlr)) {
            return Long.parseLong(vlr);
        } else {
            throw new Exception(Conversor.class.getCanonicalName()+"\nNúmero Inválido! Value: " + vlr); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static double stringToDouble(String vlr) throws Exception {
        if (isNumeric(vlr)) {
            return Double.parseDouble(vlr);
        } else {
            throw new Exception(Conversor.class.getCanonicalName()+"\nNúmero Inválido! Value: " + vlr); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static double stringToDoubleWithoutValidation(String vlr) throws Exception {
        return Double.parseDouble(vlr);
    }



    /**
     * Convert String to LocaDate without Mask
     *
     * @param vlr
     * @return
     * @throws Exception
     */
    public static LocalDate stringToLocaDate(String vlr) throws Exception {
        String msc = getDateMask(vlr);
        return stringToLocaDate(vlr, msc);
    }

    /**
     * Convert String to LocalDateTime without Mask
     *
     * @param vlr
     * @return
     * @throws Exception
     */
    public static LocalDateTime stringToLocaDateTime(String vlr) throws Exception {
        String msc = getDateMask(vlr);
        return stringToLocaDateTime(vlr, msc);
    }


   
    /**
     * Convert String to LocaDate dd/MM/YYYY or YYYY/MM/dd or dd-MM-YYYY or
     * YYYY-MM-dd
     *
     * @param vlr
     * @param msc
     * @return
     * @throws Exception
     */
    public static LocalDate stringToLocaDate(String vlr, String msc) throws Exception {
        if (msc.equals(DATE_MASK_BRAZILIAN_BAR)) {
            int dia = Integer.parseInt(vlr.substring(0, 2));
            int mes = Integer.parseInt(vlr.substring(3, 5));
            int ano = Integer.parseInt(vlr.substring(6, 10));
            return LocalDate.of(ano, mes, dia);
            //return new DateTime(ano, mes, dia, 0, 0, 0, 0);
        } else if (msc.equals(DATE_MASK_AMERICAN_BAR)) {
            int ano = Integer.parseInt(vlr.substring(0, 4));
            int mes = Integer.parseInt(vlr.substring(5, 7));
            int dia = Integer.parseInt(vlr.substring(8));
            return LocalDate.of(ano, mes, dia);
        } else if (msc.equals(DATE_MASK_BRAZILIAN_HYPHEN)) {
            int dia = Integer.parseInt(vlr.substring(0, 2));
            int mes = Integer.parseInt(vlr.substring(3, 5));
            int ano = Integer.parseInt(vlr.substring(6, 10));
            return LocalDate.of(ano, mes, dia);
        } else if (msc.equals(DATE_MASK_AMERICAN_HYPHEN)) {
            int ano = Integer.parseInt(vlr.substring(0, 4));
            int mes = Integer.parseInt(vlr.substring(5, 7));
            int dia = Integer.parseInt(vlr.substring(8));
            return LocalDate.of(ano, mes, dia);
        } else {
            throw new Exception(Conversor.class.getCanonicalName()+"\nFormato inválido!");
        }
    }

    /**
     * Convert String to DateTime dd/MM/YYYY HH:mm:ss YYYY/MM/dd HH:mm:ss
     * dd-MM-YYYY HH:mm:ss or YYYY-MM-dd HH:mm:ss
     *
     * @param vlr
     * @param msc
     * @return
     * @throws Exception
     */
    public static LocalDateTime stringToLocaDateTime(String vlr, String msc) throws Exception {
        if (msc.equals(DATETIME_MASK_BRAZILIAN_BAR)) {
            //System.out.println(vlr.substring(14, 16));
            int dia = Integer.parseInt(vlr.substring(0, 2));
            int mes = Integer.parseInt(vlr.substring(3, 5));
            int ano = Integer.parseInt(vlr.substring(6, 10));
            int hora = Integer.parseInt(vlr.substring(11, 13));
            int minuto = Integer.parseInt(vlr.substring(14, 16));
            int segundo = Integer.parseInt(vlr.substring(17, 19));
            return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
        } else if (msc.equals(DATETIME_MASK_AMERICAN_BAR)) {
            int ano = Integer.parseInt(vlr.substring(0, 4));
            int mes = Integer.parseInt(vlr.substring(5, 7));
            int dia = Integer.parseInt(vlr.substring(8,10));
            int hora = Integer.parseInt(vlr.substring(11, 13));
            int minuto = Integer.parseInt(vlr.substring(14, 16));
            int segundo = Integer.parseInt(vlr.substring(17, 19));
            return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
        } else if (msc.equals(DATETIME_MASK_BRAZILIAN_HYPHEN)) {
            System.out.println(vlr.substring(14, 16));
            int dia = Integer.parseInt(vlr.substring(0, 2));
            int mes = Integer.parseInt(vlr.substring(3, 5));
            int ano = Integer.parseInt(vlr.substring(6, 10));
            int hora = Integer.parseInt(vlr.substring(11, 13));
            int minuto = Integer.parseInt(vlr.substring(14, 16));
            int segundo = Integer.parseInt(vlr.substring(17, 19));
            return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
        } else if (msc.equals(DATETIME_MASK_AMERICAN_HYPHEN)) {
            int ano = Integer.parseInt(vlr.substring(0, 4));
            int mes = Integer.parseInt(vlr.substring(5, 7));
            int dia = Integer.parseInt(vlr.substring(8,10));
            int hora = Integer.parseInt(vlr.substring(11, 13));
            int minuto = Integer.parseInt(vlr.substring(14, 16));
            int segundo = Integer.parseInt(vlr.substring(17, 19));
            return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
        } else {
            throw new Exception(Conversor.class.getCanonicalName()+"\nFormato inválido!");
        }
    }



    public static String getDateMask(String texto) {

        Pattern dateAmericamWithHyphen = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern dateAmericamWithBar = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
        Pattern dateTimeAmericamWithHyphen = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Pattern dateTimeAmericamWithBar = Pattern.compile("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Pattern dateBrazilianWithHyphen = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Pattern dateBrazilianWithBar = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Pattern dateTimeBrazilianWithHyphen = Pattern.compile("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}");
        Pattern dateTimeBrazilianWithBar = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");

        if (dateAmericamWithHyphen.matcher(texto).matches()) {
            return DATE_MASK_AMERICAN_HYPHEN;
        } else if (dateAmericamWithBar.matcher(texto).matches()) {
            return DATE_MASK_AMERICAN_BAR;
        } else if (dateTimeAmericamWithHyphen.matcher(texto).matches()) {
            return DATETIME_MASK_AMERICAN_HYPHEN;
        } else if (dateTimeAmericamWithBar.matcher(texto).matches()) {
            return DATETIME_MASK_AMERICAN_BAR;
        } else if (dateBrazilianWithHyphen.matcher(texto).matches()) {
            return DATE_MASK_BRAZILIAN_HYPHEN;
        } else if (dateBrazilianWithBar.matcher(texto).matches()) {
            return DATE_MASK_BRAZILIAN_BAR;
        } else if (dateTimeBrazilianWithHyphen.matcher(texto).matches()) {
            return DATETIME_MASK_BRAZILIAN_HYPHEN;
        } else if (dateTimeBrazilianWithBar.matcher(texto).matches()) {
            return DATETIME_MASK_BRAZILIAN_BAR;
        } else {
            return "err";
        }
    }

    public static boolean isValidDate(String texto) throws Exception {
        String marcara = getDateMask(texto);
        SimpleDateFormat date = new SimpleDateFormat(marcara);
        if (date.parse(texto) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDate(String texto, String mascara) throws Exception {
        SimpleDateFormat dateWithMask = new SimpleDateFormat(mascara);
        if (dateWithMask.parse(texto) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDateTime(String texto) throws Exception {
        SimpleDateFormat dateAmericamWithHyphen = new SimpleDateFormat(DATETIME_MASK_AMERICAN_HYPHEN);
        SimpleDateFormat dateAmericamWithBar = new SimpleDateFormat(DATETIME_MASK_AMERICAN_BAR);
        SimpleDateFormat dateBrazilianWithHyphen = new SimpleDateFormat(DATETIME_MASK_BRAZILIAN_HYPHEN);
        SimpleDateFormat dateBrazilianWithBar = new SimpleDateFormat(DATETIME_MASK_BRAZILIAN_BAR);
        if (dateAmericamWithHyphen.parse(texto) != null || dateAmericamWithBar.parse(texto) != null || dateBrazilianWithHyphen.parse(texto) != null || dateBrazilianWithBar.parse(texto) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDateTime(String texto, String mascara) throws Exception {
        SimpleDateFormat dateWithMask = new SimpleDateFormat(mascara);
        if (dateWithMask.parse(texto) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInteger(String texto) {
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(texto);
        return mat.matches();
    }

    public static boolean isNumeric(String texto) {
        Pattern pat = Pattern.compile("[0-9]+(\\.[0-9][0-9]?)?");
        Pattern pat2 = Pattern.compile("[0-9]+(\\.[0-9]?)?");
        Matcher mat = pat.matcher(texto);
        Matcher mat2 = pat2.matcher(texto);
        return mat.matches() || mat2.matches();
    }

    /*public static void main(String[] args) throws Exception {
        //System.out.println(stringToDate("12/10/1999 04:00:00", "YYYY/MM/dd HH:mm:ss"));
        System.out.println(stringToLocaDateTime("2017-04-06 22:51:05"));
        if (isValidDate("2015/02/02 12:12:00")) {
            System.out.println("ok");
        }

        // if (isNumeric("1.0")) {
        System.out.println("ok");
        // }
    }*/
}
