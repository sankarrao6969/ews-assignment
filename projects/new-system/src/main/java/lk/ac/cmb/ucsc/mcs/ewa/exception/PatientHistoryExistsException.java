package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class PatientHistoryExistsException extends Exception {

    private static final long serialVersionUID = 140496518737963944L;

    public PatientHistoryExistsException() {
    }

    public PatientHistoryExistsException(String message) {
        super(message);
    }

    public PatientHistoryExistsException(Throwable cause) {
        super(cause);
    }

    public PatientHistoryExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
