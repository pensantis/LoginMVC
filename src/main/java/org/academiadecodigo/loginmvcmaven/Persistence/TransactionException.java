package org.academiadecodigo.loginmvcmaven.Persistence;

/**
 * Created by codecadet on 01/12/16.
 */
public class TransactionException extends RuntimeException{
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
