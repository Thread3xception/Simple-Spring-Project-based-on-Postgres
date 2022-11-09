package pl.esley.esleyspringlearnmvn.exception;

public class EntityNotFoundException extends RuntimeException {

    private Class type;
    private Long id;
    private String brand;

    public EntityNotFoundException(Class type, Long id) {
        super("Nie znaleziono obiektu " + type.getSimpleName() + " o id: " + id);
    }

    public EntityNotFoundException(Class type, String brand) {
        super("Nie znaleziono obiektu " + type.getSimpleName() + " o marce: " + brand);
    }
}
