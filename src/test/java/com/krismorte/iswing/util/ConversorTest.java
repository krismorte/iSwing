/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author krismorte
 */
public class ConversorTest {
    


    /**
     * Test of stringToDoubleString method, of class Conversor.
     */
    @Test
    public void testStringToDoubleString() {
        System.out.println("stringToDoubleString");
        String vlr = "2.000,02";
        String expResult = "2000.02";
        String result = Conversor.stringToDoubleString(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of doubleToString method, of class Conversor.
     */
    @Test
    public void testDoubleToString() {
        System.out.println("doubleToString");
        Double vlr = new Double("200.15");
        String expResult = "200.15";
        String result = Conversor.doubleToString(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToInt method, of class Conversor.
     */
    @Test
    public void testStringToInt() throws Exception {
        System.out.println("stringToInt");
        String vlr = "5";
        int expResult = 5;
        int result = Conversor.stringToInt(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLong method, of class Conversor.
     */
    @Test
    public void testStringToLong() throws Exception {
        System.out.println("stringToLong");
        String vlr = "10000";
        Long expResult = 10000L;
        Long result = Conversor.stringToLong(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDouble method, of class Conversor.
     */
    @Test
    public void testStringToDouble() throws Exception {
        System.out.println("stringToDouble");
        String vlr = "0.0";
        double expResult = 0.0;
        double result = Conversor.stringToDouble(vlr);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of stringToDoubleWithoutValidation method, of class Conversor.
     */
    @Test
    public void testStringToDoubleWithoutValidation() throws Exception {
        System.out.println("stringToDoubleWithoutValidation");
        String vlr = "0.0";
        double expResult = 0.0;
        double result = Conversor.stringToDoubleWithoutValidation(vlr);
        assertEquals(expResult, result, 0.0);
    }

 

    /**
     * Test of stringToLocaDate method, of class Conversor.
     */
    @Test
    public void testStringToLocaDate_String() throws Exception {
        System.out.println("stringToLocaDate");
        String vlr = "2019-01-01";
        LocalDate expResult = LocalDate.of(2019, Month.JANUARY, 1);
        LocalDate result = Conversor.stringToLocaDate(vlr);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of stringToLocaDateTime method, of class Conversor.
     */
    @Test
    public void testStringToLocaDateTime_String() throws Exception {
        System.out.println("stringToLocaDateTime");
        String vlr = "2019-01-01 00:00:00";
        LocalDateTime expResult = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0);
        LocalDateTime result = Conversor.stringToLocaDateTime(vlr);
        assertEquals(expResult, result);

    }


    /**
     * Test of stringToLocaDate method, of class Conversor.
     */
    @Test
    public void testStringToLocaDate_String_String() throws Exception {
        System.out.println("stringToLocaDate");
        String vlr = "";
        String msc = "";
        LocalDate expResult = null;
        LocalDate result = Conversor.stringToLocaDate(vlr, msc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToLocaDateTime method, of class Conversor.
     */
    @Test
    public void testStringToLocaDateTime_String_String() throws Exception {
        System.out.println("stringToLocaDateTime");
        String vlr = "2019-01-01 00:00:00";
        String msc = "YYYY-MM-dd hh:mm:ss";
        LocalDateTime expResult = LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0);
        LocalDateTime result = Conversor.stringToLocaDateTime(vlr, msc);
        assertEquals(expResult, result);

    }

    /**
     * Test of getDateMask method, of class Conversor.
     */
    @Test
    public void testGetDateMask() {
        System.out.println("getDateMask");
        String texto = "2019-01-01";
        String expResult = "YYYY-MM-dd";
        String result = Conversor.getDateMask(texto);
        assertEquals(expResult, result);

    }

    /**
     * Test of isValidDate method, of class Conversor.
     */
    @Test
    public void testIsValidDate_String() throws Exception {
        System.out.println("isValidDate");
        String texto = "";
        boolean expResult = false;
        boolean result = Conversor.isValidDate(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidDate method, of class Conversor.
     */
    @Test
    public void testIsValidDate_String_String() throws Exception {
        System.out.println("isValidDate");
        String texto = "";
        String mascara = "";
        boolean expResult = false;
        boolean result = Conversor.isValidDate(texto, mascara);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidDateTime method, of class Conversor.
     */
    @Test
    public void testIsValidDateTime_String() throws Exception {
        System.out.println("isValidDateTime");
        String texto = "";
        boolean expResult = false;
        boolean result = Conversor.isValidDateTime(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidDateTime method, of class Conversor.
     */
    @Test
    public void testIsValidDateTime_String_String() throws Exception {
        System.out.println("isValidDateTime");
        String texto = "";
        String mascara = "";
        boolean expResult = false;
        boolean result = Conversor.isValidDateTime(texto, mascara);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInteger method, of class Conversor.
     */
    @Test
    public void testIsInteger() {
        System.out.println("isInteger");
        String texto = "2";
        boolean expResult = true;
        boolean result = Conversor.isInteger(texto);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNumeric method, of class Conversor.
     */
    @Test
    public void testIsNumeric() {
        System.out.println("isNumeric");
        String texto = "a";
        boolean expResult = false;
        boolean result = Conversor.isNumeric(texto);
        assertEquals(expResult, result);
    }
    
}
