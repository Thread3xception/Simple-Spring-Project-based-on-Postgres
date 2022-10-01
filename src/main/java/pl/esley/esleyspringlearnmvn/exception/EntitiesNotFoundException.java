package pl.esley.esleyspringlearnmvn.exception;

public class EntitiesNotFoundException extends RuntimeException {

    private Class type;
    private String nickname;

    public EntitiesNotFoundException(String nickname) {
        super("Nie znaleziono obiektów dla gracza: " + nickname);
    }
}
