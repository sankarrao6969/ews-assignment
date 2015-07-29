package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class ChannelRecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1859527622960687099L;

	public ChannelRecordNotFoundException() {
	}

	public ChannelRecordNotFoundException(String message) {
		super(message);
	}

	public ChannelRecordNotFoundException(Throwable cause) {
		super(cause);
	}

	public ChannelRecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
