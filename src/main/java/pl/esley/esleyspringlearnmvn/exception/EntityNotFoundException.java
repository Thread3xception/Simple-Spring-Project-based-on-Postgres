package pl.esley.esleyspringlearnmvn.exception;

public class EntityNotFoundException extends RuntimeException {

    private Class type;
    private long id;
    private String brand;

    public EntityNotFoundException(Class type, long id) {
        super("Nie znaleziono obiektu " + type.getSimpleName() + " o id: " + id);
    }

    public EntityNotFoundException(Class type, String brand) {
        super("Nie znaleziono obiektu " + type.getSimpleName() + " o marce: " + brand);
    }
}
