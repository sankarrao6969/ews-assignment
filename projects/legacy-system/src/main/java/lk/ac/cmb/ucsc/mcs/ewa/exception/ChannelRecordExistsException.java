package lk.ac.cmb.ucsc.mcs.ewa.exception;

public class ChannelRecordExistsException extends Exception {

    private static final long serialVersionUID = 5838719634749042938L;

    public ChannelRecordExistsException() {
    }

    public ChannelRecordExistsException(String message) {
        super(message);
    }

    public ChannelRecordExistsException(Throwable cause) {
        super(cause);
    }

    public ChannelRecordExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
