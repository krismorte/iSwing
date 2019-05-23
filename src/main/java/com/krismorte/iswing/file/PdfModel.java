/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing.file;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author c007329
 */
public class PdfModel implements GerarArquivo {

    private List<PdfPTable> tabelas = new ArrayList<PdfPTable>();
    private Document doc = null;
    private OutputStream os = null;
    private int colunaDiff=0;
    private float tamColDiff=0.8f;
    private float tamPad=0.4f;

    public PdfModel() {
    }

    public PdfModel(String destino) {
        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4,0, 0, 30, 30);

            //cria a stream de saída
            os = new FileOutputStream(destino);

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setFont(){

    }

    public void addTabela(String titulo, String[] colunas, Object[][] linhas) {
        try {
            int tam = colunas.length;

            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Font f2 = new Font(FontFamily.COURIER, 12, Font.NORMAL);
            Font f3 = new Font(FontFamily.COURIER, 10, Font.NORMAL);
            f.setColor(BaseColor.RED);
            
            Paragraph p = new Paragraph(titulo, f);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingBefore(5);
            doc.add(p);
            Paragraph p1 = new Paragraph(" ");
            Paragraph p2 = new Paragraph(" ");
            doc.add(p1);
            doc.add(p2);
            //PdfPTable table = new PdfPTable(tam);
            float[] tamanhoColunas = new float[tam];
            for (int i = 0; i < tam; i++) {
                if(i==colunaDiff){
                    tamanhoColunas[i] = tamColDiff;
                }else{
                    tamanhoColunas[i] = tamPad;
                }


            }
            PdfPTable table = new PdfPTable(tamanhoColunas);
            for (int i = 0; i < tam; i++) {
                PdfPCell header = new PdfPCell(new Paragraph(colunas[i],f2));
                header.setBackgroundColor(BaseColor.GRAY);
                table.addCell(header);
            }
            for (int i = 0; i < linhas.length; i++) {
                for (int z = 0; z < colunas.length; z++) {
                    table.addCell(new Paragraph(linhas[i][z].toString(),f3));
                }
            }
            table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.setWidthPercentage(85);
            //tabelas.add(table);
            doc.add(table);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean geraArquivo() {
        try {

            doc.close();
            os.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean gerarArquivo(String titulo, String destino, String[] colunas, Object[][] linhas) {
        Document doc = null;
        OutputStream os = null;
        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream(destino);

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

            int tam = colunas.length;
            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            f.setColor(BaseColor.RED);
            Paragraph p = new Paragraph(titulo, f);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingBefore(5);
            doc.add(p);
            Paragraph p1 = new Paragraph(" ");
            Paragraph p2 = new Paragraph(" ");
            doc.add(p1);
            doc.add(p2);
            PdfPTable table = new PdfPTable(tam);
            for (int i = 0; i < tam; i++) {
                PdfPCell header = new PdfPCell(new Paragraph(colunas[i]));
                header.setBackgroundColor(BaseColor.GRAY);
                table.addCell(header);
            }
            for (int i = 0; i < linhas.length; i++) {
                for (int z = 0; z < colunas.length; z++) {
                    table.addCell(new Paragraph(linhas[i][z].toString()));
                }
            }
            table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            doc.add(table);
            doc.close();
            os.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * @param colunaDiff the colunaDiff to set
     */
    public void setColunaDiff(int colunaDiff) {
        this.colunaDiff = colunaDiff;
    }

    /**
     * @param tamColDiff the tamColDiff to set
     */
    public void setTamColDiff(float tamColDiff) {
        this.tamColDiff = tamColDiff;
    }

    /**
     * @param tamPad the tamPad to set
     */
    public void setTamPad(float tamPad) {
        this.tamPad = tamPad;
    }
}
