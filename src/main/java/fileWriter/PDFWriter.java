package fileWriter;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.AcceptedOffer;
import entity.Route;
import exception.MainException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * Create table from list of accepted offers and put it into file.
 *
 * @author D. Kuliha
 */
public class PDFWriter {

    private static final Logger LOG = Logger.getLogger(PDFWriter.class);

//    private static final Font FONT = FontFactory.getFont("FreeSans.ttf", "UTF-8", true);

    /**
     * Main method, creates full or short table depending on user role, creates file by filePath
     * @param filePath path for creation file
     * @param acceptedOffers list of accepted offers
     * @param userRole driver, admin or dispatcher
     */
    public static void createFile(List<AcceptedOffer> acceptedOffers, String userRole, String filePath) {

        PdfPTable table;

        try {
            if (userRole.equals("driver"))
                table = createShortTable(acceptedOffers);
            else
                table = createFullTable(acceptedOffers);

            writeTableToFile(table, filePath);
        } catch (FileNotFoundException e) {
            LOG.error("Cannot find file " + filePath, e);
            throw new MainException("Cannot find file", e);
        } catch (DocumentException | IOException e) {
            LOG.error("Something happened with document.", e);
            throw new MainException("Something happened with document.", e);
        }
    }


    private static void writeTableToFile(PdfPTable table, String file) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.B3);
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(table);
        document.close();
    }
    /**
     * Full table creation from offers including user's first name and last name
     *  @param acceptedOffers contains all accepted offers
     */
    private static PdfPTable createFullTable(List<AcceptedOffer> acceptedOffers) throws IOException, DocumentException {

        BaseFont bf = BaseFont.createFont("FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font font = new Font(bf);
        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Departure Date");
        table.addCell("Departure Date Date");
        table.addCell("Departure Point");
        table.addCell("Destination Point");
        table.addCell("Description");
        table.addCell("Driver First Name");
        table.addCell("Driver Last Name");
        table.addCell("Car Plate Number");
        table.addCell("Car Model");
        table.addCell("Status");
        table.setHeaderRows(1);

        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (AcceptedOffer offer : acceptedOffers) {
            Route route = offer.getRoute();
            table.addCell(route.getDepartureDate().toString());
            table.addCell(route.getArrivalDate().toString());
            table.addCell(new PdfPCell(new Phrase(route.getDeparturePoint(), font)));
            table.addCell(new PdfPCell(new Phrase(route.getDestinationPoint(), font)));
            table.addCell(new PdfPCell(new Phrase(route.getDescription(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getUser().getFirstName(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getUser().getLastName(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getCar().getPlateNumber(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getCar().getModel(), font)));
            table.addCell(route.getStatus());
        }
        return table;
    }


    /**
     * Table creation from offers excluding user's first name and last name.
     * @param acceptedOffers need to be accepted for only one user.
     */
    private static PdfPTable createShortTable(List<AcceptedOffer> acceptedOffers) throws IOException, DocumentException {

        BaseFont bf = BaseFont.createFont("FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font font = new Font(bf);
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Departure Date");
        table.addCell("Departure Date Date");
        table.addCell("Departure Point");
        table.addCell("Destination Point");
        table.addCell("Description");
        table.addCell("Car Plate Number");
        table.addCell("Car Model");
        table.addCell("Status");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (AcceptedOffer offer : acceptedOffers) {
            Route route = offer.getRoute();
            table.addCell(route.getDepartureDate().toString());
            table.addCell(route.getArrivalDate().toString());
            table.addCell(new PdfPCell(new Phrase(route.getDeparturePoint(), font)));
            table.addCell(new PdfPCell(new Phrase(route.getDestinationPoint(), font)));
            table.addCell(new PdfPCell(new Phrase(route.getDescription(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getCar().getPlateNumber(), font)));
            table.addCell(new PdfPCell(new Phrase(offer.getCar().getModel(), font)));
            table.addCell(route.getStatus());
        }
        return table;
    }
}
