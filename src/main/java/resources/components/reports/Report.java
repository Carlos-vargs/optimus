/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.components.reports;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import raven.toast.Notifications;

/**
 *
 * @author carlosvargas
 */
public class Report {

    protected DefaultTableModel model;

    public Report(DefaultTableModel model) {
        this.model = model;
    }

    public void exportPDF() throws BadElementException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                Document document = new Document();
                setPageSizeAndWriter(document, filePath);
                addContentToDocument(document);
                document.close();

                openPDF(filePath);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "El reporte se ha exportado exitosamente");
            } catch (DocumentException | FileNotFoundException ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Se ha producido un error al generar el reporte");
                ex.printStackTrace();
            }
        }

    }

    private void setPageSizeAndWriter(Document document, String filePath) throws DocumentException, FileNotFoundException {
        document.setPageSize(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
    }

    private void addContentToDocument(Document document) throws DocumentException, BadElementException, IOException {
        addLogoToDocument(document);
        addReportTitleToDocument(document);
        addTableToDocument(document);
    }

    private void addLogoToDocument(Document document) throws BadElementException, IOException, DocumentException {
        String logoPath = System.getProperty("user.dir") + "/src/main/java/resources/images/Logo.png";
        Image logo = Image.getInstance(logoPath);
        logo.scaleAbsolute(260, 143);
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);
    }

    private void addReportTitleToDocument(Document document) throws DocumentException {
        Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        Paragraph centeredParagraph = new Paragraph();
        centeredParagraph.setAlignment(Element.ALIGN_CENTER);
        Chunk chunk = new Chunk("reporte general".toUpperCase(), font);
        centeredParagraph.add(chunk);
        document.add(centeredParagraph);
        document.add(new Chunk("\n"));
    }

    private void addTableToDocument(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(model.getColumnCount());
        table.setHeaderRows(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        for (int i = 0; i < model.getColumnCount(); i++) {
            PdfPCell headerCell = new PdfPCell(new Phrase(model.getColumnName(i)));
            headerCell.setBackgroundColor(new BaseColor(32, 136, 203));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(headerCell);
        }

        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                table.addCell(model.getValueAt(row, column).toString());
            }
        }

        document.add(table);
    }

    private boolean openPDF(String filePath) {
        File pdfFile = new File(filePath);
        if (pdfFile.exists()) {
            try {
                Desktop.getDesktop().open(pdfFile);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
