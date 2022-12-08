package com.cherimoya.cherimoya.error;

import java.util.concurrent.ExecutionException;

public class AlreadyExistsError extends Exception {
    public AlreadyExistsError(String messege) {
        super(messege);
    }
}
