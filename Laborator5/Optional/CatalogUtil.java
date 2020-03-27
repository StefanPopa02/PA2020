package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static java.net.URI.create;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException {
        Catalog catalog = null;
        try (var ois = new ObjectInputStream(
                new FileInputStream(path))) {
            catalog = (Catalog) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new InvalidCatalogException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return catalog;
    }

    public static void view(Document doc) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        String uriOrFile = doc.getLocation();
        URI URIweb = null;
        try {
            URIweb = new URI(uriOrFile);
            desktop.browse(URIweb);
        } catch (URISyntaxException e) {
            System.out.println("It's a file!");
        }
        try {
            desktop.open(new File(uriOrFile));
        } catch (IllegalArgumentException e){
            System.out.println("It's an URL");
        }
    }
}
