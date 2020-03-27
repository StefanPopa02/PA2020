package com.company;

import java.io.IOException;

public class ViewCommand implements Command {
    private Catalog catalog;
    private String id;

    public ViewCommand(Catalog catalog, String id) {
        this.catalog = catalog;
        this.id = id;
    }

    public void execute()throws ViewCommandException1{
        Document doc=null;
        try{
            doc=catalog.findById(id);
            CatalogUtil.view(doc);
        }catch(NullPointerException e){
            throw new ViewCommandException1("Nu exista acest id in lista!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
