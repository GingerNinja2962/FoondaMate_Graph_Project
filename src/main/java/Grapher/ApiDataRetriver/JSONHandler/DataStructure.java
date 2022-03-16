package Grapher.ApiDataRetriver.JSONHandler;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;

/** A Data Structure class used to hold data from a JSON http response body as a HashMap.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-02-16
 */
public class DataStructure {
    private HashMap<String, String> dateDataLookup = new HashMap<String, String>();

    //region ====[ Setters ]====
    /** This is a Json Setter that maps key value pairs into a HashMap (so no duplicates).
     *
     * @param key The key to be placed in the HashMap as a String.
     * @param value The Value to said Key to be placed in the HashMap as a String.
     * @return Nothing.
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
