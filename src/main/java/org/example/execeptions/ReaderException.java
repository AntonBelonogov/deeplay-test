package org.example.execeptions;

import java.io.IOException;

public class ReaderException extends RuntimeException {
    public ReaderException(String message) {
        super(message);
    }
}
