package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class PatientNotFoundException extends Exception {

    private static final long serialVersionUID = 140496518737963944L;

    public PatientNotFoundException() {
    }

    public PatientNotFoundException(String message) {
        super(message);
    }

    public PatientNotFoundException(Throwable cause) {
        super(cause);
    }

    public PatientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
