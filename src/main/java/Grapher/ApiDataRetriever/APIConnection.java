package Grapher.ApiDataRetriever;

import Grapher.ApiDataRetriever.JSONHandler.JsonBodyHandler;
import Grapher.ApiDataRetriever.JSONHandler.DataStructure;
import Grapher.ApiDataRetriever.Exceptions.ClientApiUrlException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

/** An API Connection class used to Handle Connections to the API and make requests.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-02-16
 */
public class APIConnection {
    private final HttpClient ClientAPI;
    private final String ClientURL;
    private HttpRequest APIRequest;

    //region ====[ Constructions ]====
    /** The Constructor for APIConnection that sets up the HttpClient and Client's URL.
     *
     * @param clientAPIUrl The URL of the targeted APi as a String.
     */
    public APIConnection(String clientAPIUrl) {
        this.ClientAPI = HttpClient.newHttpClient();
        this.ClientURL = clientAPIUrl;
    }
    //endregion

    //region ====[ Send Request ]====
    /** This is used to send a request to the API web services,
     * and get the data needed as an object of the DataStructure class.
     *
     * @return DataStructure object that has the request body data mapped to it.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws ClientApiUrlException
     * @see InterruptedException
     * @see ExecutionException
     * @see TimeoutException
     * @see ClientApiUrlException
     */
    public DataStructure SendRequest()
            throws InterruptedException, ExecutionException, TimeoutException, ClientApiUrlException {
        if (this.ClientURL == null) throw new ClientApiUrlException("NullValue", "No up stream API URL");
        return this.SendRequest(this.ClientURL, "60");
    }

    /** This is used to send a request to the API web services,
     * and get the data needed as an object of the DataStructure class.
     *
     * @param UpStreamURL The API URL to request data from as a String.
     * @return DataStructure object that has the request body data mapped to it.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws ClientApiUrlException
     * @see InterruptedException
     * @see ExecutionException
     * @see TimeoutException
     * @see ClientApiUrlException
     */
    public DataStructure SendRequest(String UpStreamURL)
            throws InterruptedException, ExecutionException, TimeoutException {
        return this.SendRequest(UpStreamURL, "60");
    }

    /** This is used to send a request to the API web services,
     * and get the data needed as an object of the DataStructure class.
     *
     * @param UpStreamURL The API URL to request data from as a String.
     * @param timeoutInMinutes The amount of time taken to fail a request in minutes as a String.
     * @return DataStructure object that has the request body data mapped to it.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws ClientApiUrlException
     * @see InterruptedException
     * @see ExecutionException
     * @see TimeoutException
     * @see ClientApiUrlException
     */
    public DataStructure SendRequest(String UpStreamURL, String timeoutInMinutes)
            throws InterruptedException, ExecutionException, TimeoutException {
        BuildRequest(UpStreamURL);

        return ClientAPI.sendAsync(
            APIRequest, new JsonBodyHandler<>(DataStructure.class)
        ).get(
            Long.parseLong(timeoutInMinutes), TimeUnit.MINUTES
        ).body().get();
    }
    //endregion

    //region ====[ Private Functions ]====
    /** The BuildRequest method builds out a HttpRequest using the ApiURL,
     * it has a static header of [ accept - application/json ].
     *
     * @param ApiURL The URL of the API web service as a String.
     * @return Nothing.
     * @see HttpRequest
     */
    private void BuildRequest(String ApiURL) {
        APIRequest = HttpRequest.newBuilder(
                URI.create(ApiURL)
        ).header(
                "accept", "application/json"
        ).build();
    }
    //endregion
}
