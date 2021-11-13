package throwable.errors.error2exception;

import org.junit.Test;

public class ErrorTests {
    @Test
    public void testErrorConvertedException() {
        try {
            Throwable myError = new MyError("MyError");
            // will throw ClassCastException
            // java.lang.ClassCastException: MyError cannot be cast to MyErrorException
            MyErrorException ee = (MyErrorException) myError;
            throw ee;
        }
        catch (MyErrorException e) {
            System.out.println(e.getMessage());
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}
