package Exceptions;

public class ArchiveException extends Exception {
    public ArchiveException(String message) {
        super(message);
    }

    public ArchiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchiveException(Throwable cause) {
        super(cause);
    }

    public ArchiveException() {
        super();
    }
}
