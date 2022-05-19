package Grapher.ApiDataRetriever.JSONHandler;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;

/** A Data Structure class used to hold data from a JSON http response body as a HashMap.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-16
 */
public class DataStructure {
    /** This is a HashMap that contains all the date(String) - Value(String) pairs, returned from the remote API.<br>
     * This class is used by a JSON mapping library to map out the data from the JSON response into data used by the
     * CLI.
     */
    private HashMap<String, String> dateDataLookup = new HashMap<String, String>();

    //region ====[ Setters ]====
    /** This is a Json Setter that maps key value pairs into a HashMap (so no duplicates).
     *
     * @param key The key to be placed in the HashMap as a String.
     * @param value The Value to said Key to be placed in the HashMap as a String.
     * @see JsonAnySetter
     */
    @JsonAnySetter
    void setDateDataLookup(String key, String value) {
        dateDataLookup.put(key, value);
    }
    //endregion

    //region ====[ Getters ]====
    /** This is a Json Getter that returns the HashMap mapped out by a JsonParser.
     *
     * @return The HashMap mapped out by a JsonParser.
     * @see JsonAnyGetter
     */
    @JsonAnyGetter
    public HashMap<String, String> getDateDataLookup() {
        return dateDataLookup;
    }
    //endregion
}
