package APIDataRetriver;

import Grapher.ApiDataRetriever.APIConnection;
import Grapher.ApiDataRetriever.Exceptions.ClientApiUrlException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class APIDataRetrieverTest {
    //region ====[ Setup ]====
    //region ====[ Streams ]====
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    //endregion

    //region ====[ Mocked Service ]====
    private final WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(2962));
    //endregion

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

        wireMockServer.start();
        mockAPIEndPoint();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();

        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    private void mockAPIEndPoint() {
        configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(get(urlEqualTo("/testing"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(
                "{\n" +
                "    \"01-01-2022\":300,\n" +
                "    \"02-01-2022\":500,\n" +
                "    \"03-01-2022\":700,\n" +
                "    \"04-01-2022\":1300,\n" +
                "    \"05-01-2022\":2000,\n" +
                "    \"06-01-2022\":3000,\n" +
                "    \"07-01-2022\":3500,\n" +
                "    \"08-01-2022\":4000,\n" +
                "    \"09-01-2022\":4500,\n" +
                "    \"10-01-2022\":5000,\n" +
                "    \"11-01-2022\":20000,\n" +
                "    \"12-01-2022\":35000,\n" +
                "    \"13-01-2022\":46000,\n" +
                "    \"14-01-2022\":70000,\n" +
                "    \"15-01-2022\":90000\n" +
                "}")));
    }
    //endregion

    //region ====[ Test mock is working ]====
    @Test
    void MockIsWorking() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:2962/testing");
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = convertResponseToString(httpResponse);

        verify(getRequestedFor(urlEqualTo("/testing")));
        assertEquals("{\n" +
                "    \"01-01-2022\":300,\n" +
                "    \"02-01-2022\":500,\n" +
                "    \"03-01-2022\":700,\n" +
                "    \"04-01-2022\":1300,\n" +
                "    \"05-01-2022\":2000,\n" +
                "    \"06-01-2022\":3000,\n" +
                "    \"07-01-2022\":3500,\n" +
                "    \"08-01-2022\":4000,\n" +
                "    \"09-01-2022\":4500,\n" +
                "    \"10-01-2022\":5000,\n" +
                "    \"11-01-2022\":20000,\n" +
                "    \"12-01-2022\":35000,\n" +
                "    \"13-01-2022\":46000,\n" +
                "    \"14-01-2022\":70000,\n" +
                "    \"15-01-2022\":90000\n" +
                "}", responseString);
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
    //endregion

    //region ====[ Test ApiDataRetriever package ]====
    @Test
    void APIReturnsData() throws ExecutionException, InterruptedException, ClientApiUrlException, TimeoutException {
        APIConnection apiConnection = new APIConnection("http://localhost:2962/testing");

        // The regex here will only match to a string that:
        // 1. Starts with a '{'
        // 2. Has one or more sets of the following format:
        // (replace the second + with a {15} to check that exactly 15 sets are returned.)
        //      a. '00-00-0000=1, ' where 1 can be any number equal to or greater than 0
        //      b. '00-00-0000=1' where 1 can be any number equal to or greater than 0
        // 3. Ends with a '}'
        // such as the following:
        // {06-01-2022=3000, 13-01-2022=46000, 14-01-2022=70000, 12-01-2022=35000, 08-01-2022=4000, 15-01-2022=90000, 11-01-2022=20000, 07-01-2022=3500, 02-01-2022=500, 10-01-2022=5000, 01-01-2022=300, 03-01-2022=700, 09-01-2022=4500, 04-01-2022=1300, 05-01-2022=2000}
        assertTrue(apiConnection.SendRequest().getDateDataLookup().toString()
                .matches("\\{(?:\\d{2}-\\d{2}-\\d{4}=\\d+(?:, )?)+}"));
    }

    @Test
    void APIReturnsDataMultipleTimes() throws ExecutionException, InterruptedException, ClientApiUrlException, TimeoutException {
        // change the clientAPIUrl to "http://sam-user-activity.eu-west-1.elasticbeanstalk.com",
        // to test against the real API, (However if the API is down then the test will fail).
        APIConnection apiConnection = new APIConnection("http://localhost:2962/testing");

        // The regex here will only match to a string that:
        // 1. Starts with a '{'
        // 2. Has one or more sets of the following format:
        // (replace the second + with a {15} to check that exactly 15 sets are returned.)
        //      a. '00-00-0000=1, ' where 1 can be any number equal to or greater than 0
        //      b. '00-00-0000=1' where 1 can be any number equal to or greater than 0
        // 3. Ends with a '}'
        // such as the following:
        // {06-01-2022=3000, 13-01-2022=46000, 14-01-2022=70000, 12-01-2022=35000, 08-01-2022=4000, 15-01-2022=90000, 11-01-2022=20000, 07-01-2022=3500, 02-01-2022=500, 10-01-2022=5000, 01-01-2022=300, 03-01-2022=700, 09-01-2022=4500, 04-01-2022=1300, 05-01-2022=2000}
        assertTrue(apiConnection.SendRequest().getDateDataLookup().toString()
                .matches("\\{(?:\\d{2}-\\d{2}-\\d{4}=\\d+(?:, )?)+}"));
        assertTrue(apiConnection.SendRequest().getDateDataLookup().toString()
                .matches("\\{(?:\\d{2}-\\d{2}-\\d{4}=\\d+(?:, )?)+}"));
        assertTrue(apiConnection.SendRequest().getDateDataLookup().toString()
                .matches("\\{(?:\\d{2}-\\d{2}-\\d{4}=\\d+(?:, )?)+}"));
    }
    //endregion
}
