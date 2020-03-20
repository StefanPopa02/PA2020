package com.company;

import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("Java Resources", "D:/Cursuri/Anul II/Semestrul II/Programare avansata/Laborator/catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc2 = new Document("java2","director","d:/");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        catalog.add(doc2);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("D:/Cursuri/Anul II/Semestrul II/Programare avansata/Laborator/catalog.ser");
        Document doc = catalog.findById("java1");
        Document doc2 = catalog.findById("java2");
        CatalogUtil.view(doc);
        CatalogUtil.view(doc2);
    }
}
