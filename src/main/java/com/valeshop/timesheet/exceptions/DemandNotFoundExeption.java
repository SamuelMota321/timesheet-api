package com.valeshop.timesheet.exceptions;

public class DemandNotFoundExeption extends RuntimeException {
    public DemandNotFoundExeption() {
        super("Demanda não encontrada.");
    }

    public DemandNotFoundExeption(String message) {
        super(message);
    }
}
