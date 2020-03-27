package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class ReportCommand implements Command {

    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws ReportCommandException {
        try (FileWriter reportHTML = new FileWriter("reportHTML.html")) {
            reportHTML.write(
                    "<html>\n" +
                            "<head>\n" +
                            "<title>\n" +
                            "Report Catalog\n" +
                            "</title>\n" +
                            "</head>\n" +
                            "<body>" +
                            "Numele Catalogului: " +
                            catalog.getName() +
                            "<br>" +
                            "Calea: " +
                            catalog.getPath() +
                            "<br>" +
                            "Documente:" +
                            "<br>"
            );
            List<Document> documents = catalog.getDocuments();
            for (Document document : documents) {
                reportHTML.write(document.getId() + "," + document.getName() + "," + document.getLocation() + "\n" + "<br>" + "\n");
            }
            reportHTML.write("</body>" +
                    "\n" +
                    "</html>");
        } catch (IOException e) {
            throw new ReportCommandException("Nu se poate crea fisierul HTML");
        } catch (NullPointerException e){
            throw new ReportCommandException("Nu ati dat load la catalog!");
        }
    }
}
