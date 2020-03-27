package com.company;

public class ListCommand implements Command {
    private Catalog catalog = null;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void execute() throws ListCommandException {
        try {
            for (Document document : catalog.getDocuments()) {
                System.out.println(document.getId() + ": " + document.getName());
            }
        } catch (NullPointerException e) {
            throw new ListCommandException("Nu ati dat load la catalog!");
        }
    }
}
