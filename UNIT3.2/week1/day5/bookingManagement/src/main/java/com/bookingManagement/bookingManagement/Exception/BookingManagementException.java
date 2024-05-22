package com.bookingManagement.bookingManagement.Exception;

public class BookingManagementException extends Exception {
    public BookingManagementException(String message) {
        super(message);
    }

    public BookingManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingManagementException(Throwable cause) {
        super(cause);
    }

    public BookingManagementException() {
        super();
    }
}
