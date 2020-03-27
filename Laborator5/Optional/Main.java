package com.company;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadView();
            app.optionalCreateSave();
            app.optionalLoad();
            app.startShell();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }

    private void startShell(){
        Shell shell = new Shell();
        shell.run();
    }
    private void optionalCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("Java Resources", "D:/Cursuri/Anul II/Semestrul II/Programare avansata/Laborator/catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document doc2 = new Document("java2","director","d:/");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        catalog.add(doc2);
        CatalogUtilBonus.save(catalog);
    }

    private void optionalLoad()throws IOException{
        Catalog catalog = CatalogUtilBonus.load();
        System.out.println("Catalog name: "+catalog.getName());
        System.out.println("Catalog path: "+catalog.getPath());
        System.out.println("Documents of the catalog: ");
        for(Document document:catalog.getDocuments()){
            System.out.println(document.getId()+" "+document.getName()+" "+document.getLocation());
            //CatalogUtil.view(document);
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
        //CatalogUtil.view(doc);
        //CatalogUtil.view(doc2);
    }
}
