package ecommerce.cartservice.exception;

import java.util.UUID;

public class EvaluationNotFoundException extends Throwable {
    public EvaluationNotFoundException(UUID id) {
        super("Cannot find evaluation number [" + id + "]");
    }
}
