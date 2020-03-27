package com.company;

import java.io.InvalidClassException;

public class LoadCommand implements Command {
    Catalog catalog;

    public void execute() throws InvalidCatalogException {
            catalog = CatalogUtil.load("D:/Cursuri/Anul II/Semestrul II/Programare avansata/Laborator/catalog.ser");
    }

    public Catalog getCatalog() {
        return catalog;
    }
}
