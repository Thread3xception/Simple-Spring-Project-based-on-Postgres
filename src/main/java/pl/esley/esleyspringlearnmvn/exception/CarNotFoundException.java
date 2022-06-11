package pl.esley.esleyspringlearnmvn.exception;

public class CarNotFoundException extends Exception {
    public CarNotFoundException() {
    }

    public CarNotFoundException(long id) {
        super("Nie znaleziono samochodu o id: " + id);
    }
}
