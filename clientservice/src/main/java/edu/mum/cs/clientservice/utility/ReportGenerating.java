package edu.mum.cs.clientservice.utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import java.io.ByteArrayInputStream;

public class ReportGenerating {

    public static ByteArrayInputStream receipt(final List<ProductOrder> productOrders, Order order, User user) {

        Document document = new Document(PageSize.A4, 36, 36, 20, 72);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        SimpleDateFormat format = new SimpleDateFormat("MMM yyyy , dd");
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.##");

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            BaseColor color = new BaseColor(190, 198, 226);

            Image headerImage = Image.getInstance("images/Maharshi-logo.jpg");
            headerImage.scaleToFit(100f, 100f);

            PdfPTable docHeader_2 = new PdfPTable(1);
            docHeader_2.setWidthPercentage(100);

            PdfPCell cell_2_1 = new PdfPCell();
            Paragraph para1_2 = new Paragraph();
            headerImage.scalePercent(40);

            para1_2.add(new Chunk(headerImage, 0, 0, true));
            para1_2.setSpacingAfter(-40f);
            cell_2_1.addElement(para1_2);

            Paragraph letterHeader_2 = new Paragraph();
            letterHeader_2.add(new Phrase("Order Details\n", font));
            letterHeader_2.setAlignment(Paragraph.ALIGN_CENTER);

            cell_2_1.addElement(letterHeader_2);
            cell_2_1.setBorder(Rectangle.BOTTOM);
            docHeader_2.addCell(cell_2_1);
            document.add(docHeader_2);

            document.add(new Paragraph(" "));


            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingAfter(1f);
            table.setWidths(new float[]{1, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell cell = new PdfPCell(new Phrase("CODE", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(10);
            cell.setBackgroundColor(color);
            table.addCell(cell);

            PdfPCell inside = new PdfPCell(new Phrase(order.getOrderNumber().toUpperCase(),headFont));
            inside.setPadding(7f);
            table.addCell(inside);

            PdfPCell cell1 = new PdfPCell(new Phrase("NAME", headFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPadding(10);
            cell1.setBackgroundColor(color);
            table.addCell(cell1);

            PdfPCell inside1 = new PdfPCell(new Phrase(user.getName()+" "+user.getLastName(),headFont));
            inside1.setPadding(7f);
            table.addCell(inside1);


            PdfPCell cell2 = new PdfPCell(new Phrase("DATE", headFont));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setPadding(10);
            cell2.setBackgroundColor(color);
            table.addCell(cell2);

            PdfPCell inside2 = new PdfPCell(new Phrase(format.format(new Date()),headFont));
            inside2.setPadding(7f);
            table.addCell(inside2);

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph header = new Paragraph();
            header.add(new Phrase("ORDER ITEMS", font));
            header.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(header);
            document.add(new Paragraph(" "));

            PdfPTable table1 = new PdfPTable(4);
            table1.setWidthPercentage(100);

            PdfPCell cell5 = new PdfPCell(new Phrase("ITEM NAME", headFont));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5.setPadding(10f);
            cell5.setBackgroundColor(color);
            table1.addCell(cell5);

            PdfPCell cell6 = new PdfPCell(new Phrase("UNIT PRICE", headFont));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell6.setPadding(10f);
            cell6.setBackgroundColor(color);
            table1.addCell(cell6);

            PdfPCell cell3 = new PdfPCell(new Phrase("QUANTITY", headFont));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setPadding(10f);
            cell3.setBackgroundColor(color);
            table1.addCell(cell3);

            PdfPCell overdue = new PdfPCell(new Phrase("TOTAL PRICE", headFont));
            overdue.setHorizontalAlignment(Element.ALIGN_CENTER);
            overdue.setVerticalAlignment(Element.ALIGN_MIDDLE);
            overdue.setPadding(10f);
            overdue.setBackgroundColor(color);
            table1.addCell(overdue);





            for (ProductOrder productOrder : productOrders) {
                PdfPCell res;

                res = new PdfPCell(new Phrase(productOrder.getProduct().getName()));
                res.setVerticalAlignment(Element.ALIGN_MIDDLE);
                res.setHorizontalAlignment(Element.ALIGN_CENTER);
                res.setPadding(7f);
                table1.addCell(res);

                res = new PdfPCell(new Phrase("$ "+productOrder.getProduct().getPrice()));
                res.setVerticalAlignment(Element.ALIGN_MIDDLE);
                res.setHorizontalAlignment(Element.ALIGN_CENTER);
                res.setPadding(7f);
                table1.addCell(res);

                res = new PdfPCell(new Phrase(productOrder.getQuantity()+" "));
                res.setVerticalAlignment(Element.ALIGN_MIDDLE);
                res.setHorizontalAlignment(Element.ALIGN_CENTER);
                res.setPadding(7f);
                table1.addCell(res);

                double total = productOrder.getQuantity() * productOrder.getProduct().getPrice();
                inside = new PdfPCell(new Phrase("$ "+total));
                inside.setVerticalAlignment(Element.ALIGN_MIDDLE);
                inside.setHorizontalAlignment(Element.ALIGN_CENTER);
                inside.setPadding(7f);
                table1.addCell(inside);
            }

            document.add(table1);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());

    }
}
