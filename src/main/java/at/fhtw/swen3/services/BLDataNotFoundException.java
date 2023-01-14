package at.fhtw.swen3.services;

public class BLDataNotFoundException extends BLException {
    public BLDataNotFoundException( Exception innerException, String errorMessage) {
        super( innerException, errorMessage);
    }
}
