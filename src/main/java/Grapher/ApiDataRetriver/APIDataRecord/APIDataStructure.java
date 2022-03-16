package Grapher.ApiDataRetriver.APIDataRecord;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;

public class APIDataStructure {
    private HashMap<String, String> dateDataLookup = new HashMap<String, String>();

    //region ====[ Setters ]====
    @JsonAnySetter
    void setDateDataLookup(String key, String value) {
        dateDataLookup.put(key, value);
    }
    //endregion

    //region ====[ Getters ]====
    @JsonAnyGetter
    public HashMap<String, String> getDateDataLookup() {
        return dateDataLookup;
    }
    //endregion
}
