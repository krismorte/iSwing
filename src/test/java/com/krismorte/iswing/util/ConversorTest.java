/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.joda.time.DateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author krismorte
 */
public class ConversorTest {
    
    public ConversorTest() {
    }
    


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
     * Test of stringToDate method, of class Conversor.
     */
    @Test
    public void testStringToDate_String() throws Exception {
        System.out.println("stringToDate");
        String vlr = "2019-01-01";
        Date expResult = Date.from(Instant.parse("2019-01-01"));
        Date result = Conversor.stringToDate(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDateTime method, of class Conversor.
     */
    @Test
    public void testStringToDateTime_String() throws Exception {
        System.out.println("stringToDateTime");
        String vlr = "";
        DateTime expResult = null;
        DateTime result = Conversor.stringToDateTime(vlr);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLocaDate method, of class Conversor.
     */
    @Test
    public void testStringToLocaDate_String() throws Exception {
        System.out.println("stringToLocaDate");
        String vlr = "";
        LocalDate expResult = null;
        LocalDate result = Conversor.stringToLocaDate(vlr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToLocaDateTime method, of class Conversor.
     */
    @Test
    public void testStringToLocaDateTime_String() throws Exception {
        System.out.println("stringToLocaDateTime");
        String vlr = "";
        LocalDateTime expResult = null;
        LocalDateTime result = Conversor.stringToLocaDateTime(vlr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToDate method, of class Conversor.
     */
    @Test
    public void testStringToDate_String_String() throws Exception {
        System.out.println("stringToDate");
        String vlr = "";
        String msc = "";
        Date expResult = null;
        Date result = Conversor.stringToDate(vlr, msc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertDateToLocalDateTimeLastHour method, of class Conversor.
     */
    @Test
    public void testConvertDateToLocalDateTimeLastHour() {
        System.out.println("convertDateToLocalDateTimeLastHour");
        Date data = null;
        Date expResult = null;
        Date result = Conversor.convertDateToLocalDateTimeLastHour(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToDateTime method, of class Conversor.
     */
    @Test
    public void testStringToDateTime_String_String() throws Exception {
        System.out.println("stringToDateTime");
        String vlr = "";
        String msc = "";
        DateTime expResult = null;
        DateTime result = Conversor.stringToDateTime(vlr, msc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String vlr = "";
        String msc = "";
        LocalDateTime expResult = null;
        LocalDateTime result = Conversor.stringToLocaDateTime(vlr, msc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateToLocalDate method, of class Conversor.
     */
    @Test
    public void testDateToLocalDate() {
        System.out.println("dateToLocalDate");
        Date date = null;
        LocalDate expResult = null;
        LocalDate result = Conversor.dateToLocalDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateToLocalDateTime method, of class Conversor.
     */
    @Test
    public void testDateToLocalDateTime() {
        System.out.println("dateToLocalDateTime");
        Date date = null;
        LocalDateTime expResult = null;
        LocalDateTime result = Conversor.dateToLocalDateTime(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateToDateTime method, of class Conversor.
     */
    @Test
    public void testDateToDateTime() {
        System.out.println("dateToDateTime");
        Date date = null;
        DateTime expResult = null;
        DateTime result = Conversor.dateToDateTime(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of localDateToDate method, of class Conversor.
     */
    @Test
    public void testLocalDateToDate() {
        System.out.println("localDateToDate");
        LocalDate date = null;
        Date expResult = null;
        Date result = Conversor.localDateToDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of localDateTimeToDate method, of class Conversor.
     */
    @Test
    public void testLocalDateTimeToDate() {
        System.out.println("localDateTimeToDate");
        LocalDateTime date = null;
        Date expResult = null;
        Date result = Conversor.localDateTimeToDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of localDateToDateTime method, of class Conversor.
     */
    @Test
    public void testLocalDateToDateTime() {
        System.out.println("localDateToDateTime");
        LocalDate date = null;
        DateTime expResult = null;
        DateTime result = Conversor.localDateToDateTime(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of localDateTimeToDateTime method, of class Conversor.
     */
    @Test
    public void testLocalDateTimeToDateTime() {
        System.out.println("localDateTimeToDateTime");
        LocalDateTime date = null;
        DateTime expResult = null;
        DateTime result = Conversor.localDateTimeToDateTime(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateTimeToDate method, of class Conversor.
     */
    @Test
    public void testDateTimeToDate() {
        System.out.println("dateTimeToDate");
        DateTime date = null;
        Date expResult = null;
        Date result = Conversor.dateTimeToDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateMask method, of class Conversor.
     */
    @Test
    public void testGetDateMask() {
        System.out.println("getDateMask");
        String texto = "";
        String expResult = "";
        String result = Conversor.getDateMask(texto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
