package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class PatientHistoryNotFoundException extends Exception {

    private static final long serialVersionUID = 140496518737963944L;

    public PatientHistoryNotFoundException() {
    }

    public PatientHistoryNotFoundException(String message) {
        super(message);
    }

    public PatientHistoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public PatientHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
