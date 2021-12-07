
public class MyException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Undefined int for object";
	
	public MyException () {
		super(MESSAGE);
	}

}
