package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Shell {
    private Catalog catalog;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String comanda;
        scanner.useDelimiter(" ");
        boolean ok = true;
        comenziDisponibile();
        while (ok) {
            if (scanner.hasNextLine()) {
                comanda = scanner.nextLine();
                switch (verificareComanda(comanda)) {
                    case "load":
                        LoadCommand loadCommand = new LoadCommand();
                        try {
                            loadCommand.execute();
                        } catch (InvalidCatalogException e) {
                            System.out.println(e.getMessage());
                        }
                        catalog = loadCommand.getCatalog();
                        break;
                    case "view":
                        ViewCommand viewCommand = new ViewCommand(catalog, prelucrareComanda(comanda));
                        try {
                            viewCommand.execute();
                        } catch (ViewCommandException1 e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "list":
                        ListCommand listCommand = new ListCommand(catalog);
                        try {
                            listCommand.execute();
                        } catch (ListCommandException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "report html":
                        ReportCommand reportCommand = new ReportCommand(catalog);
                        try {
                            reportCommand.execute();
                        } catch (ReportCommandException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "quit":
                        ok = false;
                        break;
                    default:
                        System.out.println("Comanda introdusa este gresita!");
                }
            }
        }
    }

    private void comenziDisponibile() {
        System.out.println("Comenzile disponibile:");
        System.out.println("load");
        System.out.println("list");
        System.out.println("view");
        System.out.println("report html");
        System.out.println("quit");
    }

    private String verificareComanda(String comanda) {
        if (comanda.startsWith("view")) {
            return "view";
        } else if (comanda.startsWith("list")) {
            return "list";
        } else if (comanda.startsWith("load")) {
            return "load";
        } else if (comanda.startsWith("report")) {
            return "report html";
        } else if (comanda.startsWith("quit")) {
            return "quit";
        }
        return "fail";
    }

    private String prelucrareComanda(String comanda) {
        if (comanda.length() > 5) {
            return comanda.substring(5);
        }
        return null;
    }
}
