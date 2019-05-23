/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.file;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;


/**
 *
 * @author c007329
 */
public class ExcelModel implements GerarArquivo {

    public boolean gerarArquivo(String titulo, String destino, String[] colunas, Object[][] linhas) {
        try {
            FileOutputStream stream = new FileOutputStream(destino);
            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet1 = wb.createSheet(titulo);

            HSSFRow row = sheet1.createRow(0);
            int tam = colunas.length;
            for (int i = 0; i < tam; i++) {
                Cell c = row.createCell(i, Cell.CELL_TYPE_STRING);
                c.setCellValue(colunas[i]);
                HSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                c.setCellStyle(cellStyle);
            }
            for (int i = 0; i < linhas.length; i++) {
                HSSFRow rows = sheet1.createRow(i + 1);
                for (int z = 0; z < colunas.length; z++) {
                    Cell c = rows.createCell(z, Cell.CELL_TYPE_STRING);
                    c.setCellValue(linhas[i][z].toString());
                }
            }


            wb.write(stream);
            stream.flush();
            stream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
