package Grapher.ApiDataRetriever.JSONHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import java.net.http.HttpResponse.ResponseInfo;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;

import java.util.function.Supplier;

/** A Json Body Handler record used to map data from
 * a JSON http response body to an object of a class.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-02-16
 */
public record JsonBodyHandler<Type>(Class<Type> targetClass)
        implements BodyHandler<Supplier<Type>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //region ====[ apply ]====
    @Override
    /** Used to override the default apply function,
     * this is done to convert the normal JSON response,
     * to a BodySubscriber object of a given class.
     * (in this case it is the DataStructure class used to hold the data from the API)
     *
     * While this could be done much more easily and way more efficiently,
     * I choose this way as I want to show my skills,
     * this could be used in a more dynamic and flexible way by simply changing out the DataStructure class
     */
    public BodySubscriber<Supplier<Type>> apply(ResponseInfo responseInfo) {
        return asJSON(this.targetClass);
    }
    //endregion

    //region ====[ asJSON ]====
    /** This is a method used to convert the body of a given response into a JSON BodySubscription
     * by applying a Lambda function on the upstream (created using the ofInputStream method,
     * which streams the response body as an InputStream).
     *
     * @param targetType The Class to be used for the JSON BodySubscription.
     * @param <W> The targetType class.
     * @return A BodySubscriber with the supplier of targetType class.
     * @see BodySubscriber
     * @see BodySubscribers
     * @see Supplier
     * @see Class
     */
    public static <W> BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
        BodySubscriber<InputStream> upstream = BodySubscribers.ofInputStream();

        return BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType));
    }
    //endregion

    //region ====[ lambda function - toSupplierOfType ]====
    /** The lambda function that maps the body of the response to a objectMapper
     * this function returns a Supplier that will run the readValue method on the get sub-method call
     * and map the inputStream to the targetType.
     * (This means it will move data from the response body into the class object,
     * using an ObjectMapper and lambda functions with InputStreams for better stability).
     *
     * @param inputStream The InputStream to be mapped out to the Class Object.
     * @param targetType The Class the InputStream should be mapped to.
     * @param <W> The Class the InputStream should be mapped out to.
     * @return The Supplier that invokes the mapping function for objectMapper.
     */
    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try {
                return objectMapper.readValue(inputStream, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
    //endregion
}
