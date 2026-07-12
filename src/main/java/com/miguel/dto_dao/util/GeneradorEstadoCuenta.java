package com.miguel.dto_dao.util;

import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Component
public class GeneradorEstadoCuenta {

  public byte[] generarEstadoCuentaPdf() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try (PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf)) {

      // 1. Título y datos del cliente
      document.add(new Paragraph("ESTADO DE CUENTA").setBold().setFontSize(20));
      document.add(new Paragraph("Cliente: Juan Pérez"));
      document.add(new Paragraph("Cuenta: **** 1234"));
      document.add(new Paragraph("Periodo: Julio 2026\n\n"));

      // 2. Tabla de Movimientos
      float[] columnWidths = { 150f, 250f, 100f, 100f };
      Table table = new Table(columnWidths);

      // Encabezados
      table.addCell(new Cell().add(new Paragraph("Fecha")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
      table.addCell(
          new Cell().add(new Paragraph("Descripción")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
      table.addCell(new Cell().add(new Paragraph("Monto")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
      table.addCell(new Cell().add(new Paragraph("Saldo")).setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));

      // Datos de ejemplo
      table.addCell("01/07/2026");
      table.addCell("Depósito de Nómina");
      table.addCell("$5,000.00");
      table.addCell("$5,000.00");

      table.addCell("05/07/2026");
      table.addCell("Supermercado");
      table.addCell("-$1,250.00");
      table.addCell("$3,750.00");

      document.add(table);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return baos.toByteArray();
  }

  public byte[] getPDF() {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    try (PdfWriter writer = new PdfWriter(bos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf)) {

      // Colores personalizados
      DeviceRgb primaryColor = new DeviceRgb(0, 51, 102); // Azul marino
      // DeviceRgb secondaryColor = new DeviceRgb(240, 240, 240); // Gris claro

      // 1. Encabezado
      Paragraph title = new Paragraph("ESTADO DE CUENTA")
          .setFontSize(20)
          .setFontColor(primaryColor)
          .setBold()
          .setTextAlignment(TextAlignment.RIGHT);
      document.add(title);

      Paragraph bankInfo = new Paragraph("Banco Finanzas Global\nAv. Principal 123, Ciudad\nTel: 800-123-4567")
          .setFontSize(10)
          .setFontColor(ColorConstants.GRAY);
      document.add(bankInfo);

      document.add(new Paragraph("\n")); // Espacio

      // 2. Información del Cliente
      Table clientTable = new Table(new float[] { 1, 1 });
      clientTable.setWidth(UnitValue.createPercentValue(100));
      clientTable.addCell(new Cell().add(new Paragraph("Cliente: Juan Pérez")));
      clientTable.addCell(new Cell().add(new Paragraph("Cuenta: **1234")));
      clientTable.addCell(new Cell().add(new Paragraph("Dirección: Calle Falsa 123")));
      clientTable.addCell(new Cell().add(new Paragraph("Periodo: Junio 2026")));

      document.add(clientTable);
      document.add(new Paragraph("\n"));

      // 3. Tabla de Resumen
      Paragraph summaryTitle = new Paragraph("Resumen del Periodo").setFontSize(12);
      document.add(summaryTitle);

      Table summaryTable = new Table(new float[] { 2, 2, 2, 2 });
      summaryTable.setWidth(UnitValue.createPercentValue(100));

      summaryTable.addHeaderCell(new Cell().add(new Paragraph("Saldo Anterior").setBold()));
      summaryTable.addHeaderCell(new Cell().add(new Paragraph("Total Cargos").setBold()));
      summaryTable.addHeaderCell(new Cell().add(new Paragraph("Total Abonos").setBold()));
      summaryTable.addHeaderCell(new Cell().add(new Paragraph("Saldo Actual").setBold()));

      summaryTable.addCell("$500.00");
      summaryTable.addCell("$1,200.00");
      summaryTable.addCell("$800.00");
      summaryTable.addCell("$900.00");

      document.add(summaryTable);
      document.add(new Paragraph("\n"));

      // 4. Tabla de Movimientos Detallados
      Paragraph movementsTitle = new Paragraph("Movimientos del Mes").setFontSize(12);
      document.add(movementsTitle);

      Table movementsTable = new Table(new float[] { 2, 4, 2, 2 });
      movementsTable.setWidth(UnitValue.createPercentValue(100));

      // Encabezados de tabla
      movementsTable.addHeaderCell(new Cell().setBackgroundColor(primaryColor).setBold()
          .add(new Paragraph("Fecha").setFontColor(ColorConstants.WHITE).setBold()));
      movementsTable.addHeaderCell(new Cell().setBackgroundColor(primaryColor).setBold()
          .add(new Paragraph("Descripción").setFontColor(ColorConstants.WHITE).setBold()));
      movementsTable.addHeaderCell(new Cell().setBackgroundColor(primaryColor).setBold()
          .add(new Paragraph("Cargos").setFontColor(ColorConstants.WHITE).setBold()));
      movementsTable.addHeaderCell(new Cell().setBackgroundColor(primaryColor).setBold()
          .add(new Paragraph("Abonos").setFontColor(ColorConstants.WHITE).setBold()));

      // Fila 1
      movementsTable.addCell("01/06/2026");
      movementsTable.addCell("Depósito de Nómina");
      movementsTable.addCell("-");
      movementsTable.addCell("$800.00");

      // Fila 2
      movementsTable.addCell("05/06/2026");
      movementsTable.addCell("Pago en Supermercado");
      movementsTable.addCell("$150.00");
      movementsTable.addCell("-");

      // Fila 3
      movementsTable.addCell("12/06/2026");
      movementsTable.addCell("Pago de Servicios");
      movementsTable.addCell("$50.00");
      movementsTable.addCell("-");

      // PdfWriter.getInstance(document, bos);
      document.add(movementsTable);

      // Cerrar documento
      document.close();
      System.out.println("PDF generado correctamente.");

    } catch (Exception e) {
      e.printStackTrace();
    }

    return bos.toByteArray();

  }
}
