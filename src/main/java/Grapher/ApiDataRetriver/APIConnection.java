package Grapher.ApiDataRetriver;

import Grapher.ApiDataRetriver.JSONHandler.JsonBodyHandler;
import Grapher.ApiDataRetriver.APIDataRecord.APIDataStructure;
import Grapher.ApiDataRetriver.Exceptions.ClientApiUrlException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

public class APIConnection {
    private final HttpClient ClientAPI;
    private final String ClientURL;
    private HttpRequest APIRequest;

    //region ====[ Constructions ]====
    public APIConnection(String clientAPIUrl) {
        this.ClientAPI = HttpClient.newHttpClient();
        this.ClientURL = clientAPIUrl;
    }
    //endregion

    //region ====[ Send Request ]====
    /**
     * <p>This is used to send a request to the up stream API and get the data needed as a class format.<//>
     *
     * @return HttpResponse - Supplier (APIDataStructure).
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @throws ClientApiUrlException
     */
    public APIDataStructure SendRequest()
            throws InterruptedException, ExecutionException, TimeoutException, ClientApiUrlException {
        if (this.ClientURL == null) throw new ClientApiUrlException("NullValue", "No up stream API URL");
        return this.SendRequest(this.ClientURL, "60");
    }

    /**
     * <p>This is used to send a request to the up stream API and get the data needed as a class format.<//>
     *
     * @param UpStreamURL as String.
     * @return HttpResponse - Supplier (APIDataStructure).
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public APIDataStructure SendRequest(String UpStreamURL)
            throws InterruptedException, ExecutionException, TimeoutException {
        return this.SendRequest(UpStreamURL, "60");
    }

    /**
     * <p>This is used to send a request to the up stream API and get the data needed as a class format.<//>
     *
     * @param UpStreamURL as String.
     * @param timeoutInMinutes as String.
     * @return HttpResponse with the Supplier APIDataStructure.
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public APIDataStructure SendRequest(String UpStreamURL, String timeoutInMinutes)
            throws InterruptedException, ExecutionException, TimeoutException {
        BuildRequest(UpStreamURL);

        return ClientAPI.sendAsync(
            APIRequest, new JsonBodyHandler<>(APIDataStructure.class)
        ).get(
            Long.parseLong(timeoutInMinutes), TimeUnit.MINUTES
        ).body().get();
    }
    //endregion

    //region ====[ Private Functions ]====
    private void BuildRequest(String UpStreamURL) {
        APIRequest = HttpRequest.newBuilder(
                URI.create(UpStreamURL)
        ).header(
                "accept", "application/json"
        ).build();
    }
    //endregion
}
