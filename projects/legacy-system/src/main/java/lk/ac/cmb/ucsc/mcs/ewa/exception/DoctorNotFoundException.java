package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class DoctorNotFoundException extends Exception {

	private static final long serialVersionUID = 8027540622225534551L;

	public DoctorNotFoundException() {
	}

	public DoctorNotFoundException(String message) {
		super(message);
	}

	public DoctorNotFoundException(Throwable cause) {
		super(cause);
	}

	public DoctorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
