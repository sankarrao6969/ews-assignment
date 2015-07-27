package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class ChannelNotFoundException extends Exception {

	private static final long serialVersionUID = 1859527622960687099L;

	public ChannelNotFoundException() {
	}

	public ChannelNotFoundException(String message) {
		super(message);
	}

	public ChannelNotFoundException(Throwable cause) {
		super(cause);
	}

	public ChannelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
