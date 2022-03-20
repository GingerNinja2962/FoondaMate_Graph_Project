package Grapher.ApiDataRetriever.Exceptions;

/** An Exception class used to build an Exception,
 * with an Exception code and message.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-16
 */
public class ClientApiUrlException extends Exception {
    private String exceptionCode;

    //region ====[ Constructions ]====
    /** The constructor of ClientApiUrlException that builds off the Exception class
     * by supering the message and adding the Exception code.
     *
     * @param code The Exception code as a String.
     * @param message The Exception message to be displayed as a String.
     * @see Exception
     */
    public ClientApiUrlException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    /** The constructor of ClientApiUrlException that builds off the Exception class
     * by supering the message and the cause then adding the Exception code.
     *
     * @param code The Exception code as a String.
     * @param message The Exception message to be displayed as a String.
     * @param cause The cause of the Exception.
     * @see Exception
     */
    public ClientApiUrlException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }
    //endregion

    //region ====[ Getters ]====
    /** The getter for the Exception code of the ClientApiUrlException.
     *
     * @return String containing the Exception code.
     * @see Exception
     */
    public String getCode() {
        return exceptionCode;
    }
    //endregion

    //region ====[ Setters ]====
    /** The setter for the Exception code of the ClientApiUrlException.
     *
     * @param code A String containing the Exception code.
     * @return Nothing.
     * @see Exception
     */
    public void setCode(String code) {
        this.exceptionCode = code;
    }
    //endregion
}
