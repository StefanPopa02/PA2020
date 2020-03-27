package com.company;

public interface Command {
    public void execute() throws ListCommandException,InvalidCatalogException,ViewCommandException1,ReportCommandException;
}
