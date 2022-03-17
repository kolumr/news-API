package exceptions;
//RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java Virtual Machine.
//i.e. when program is in use
public class ApiException extends RuntimeException {
    private final int statusCode; //property for error status code

    public ApiException (int statusCode, String msg){
        super(msg); //super keyword is for calling the parent class constructor i.e RuntimeException , inorder to instantiate an unnamed object here
        this.statusCode = statusCode; //getting status code from the parent class
    }

    public int getStatusCode() {
        return statusCode;
    }  //returning the status code
}
