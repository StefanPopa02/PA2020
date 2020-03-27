package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CatalogUtilBonus {
    public static void save(Catalog catalog) throws IOException {
        try (FileWriter catalogSaver = new FileWriter("catalogdata.txt")) {
            catalogSaver.write(catalog.getName() + "\n" + catalog.getPath() + "\n");
            List<Document> documents = catalog.getDocuments();
            for (Document document : documents) {
                catalogSaver.write(document.getId() + "," + document.getName() + "," + document.getLocation() + "\n");
            }
        }
    }

    public static Catalog load() throws IOException {
        Scanner scanner = null;

        try {
            Catalog catalog;
            scanner = new Scanner(new BufferedReader(new FileReader("catalogdata.txt")));
            scanner.useDelimiter(",");
            String catalogName = scanner.nextLine();
            String catalogPath = scanner.nextLine();
            catalog = new Catalog(catalogName, catalogPath);
            while (scanner.hasNextLine()) {
                String id = scanner.next();
                String name = scanner.next();
                scanner.skip(scanner.delimiter());
                String location = scanner.nextLine();
                catalog.add(new Document(id, name, location));
            }
            return catalog;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
