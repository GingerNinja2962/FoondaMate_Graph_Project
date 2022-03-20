package Grapher.Printer.DataManager;

import Grapher.ApiDataRetriever.JSONHandler.DataStructure;

import java.time.LocalDate;

import java.util.TreeMap;
import java.util.SortedMap;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

/** A class designed to handle the data received from the API and
 * convert it into LocalDate and Integer pairs in a SortedMap.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-19
 */
public class DataManager {
    private final DataStructure APIData;
    private final SortedMap<LocalDate, Integer> sortedData;

    //region ====[ Constructors ]====
    /** The Constructor for DataManager Class that handles the
     * data need for the graphs, and the conversion of said data.
     *
     * @param ApiData The DataStructure used to parse the data received from the API.
     */
    public DataManager(DataStructure ApiData) {
        this.APIData = ApiData;
        this.sortedData = generateSortedMap();
    }
    //endregion

    //region ====[ Getters ]====
    /** The getter for a SortedMap of LocalDates and Integers that
     * the graphs will use to display the data.
     *
     * @return SortedMap of LocalDates as Keys and Integers as Values
     * @see SortedMap
     * @see LocalDate
     */
    public SortedMap<LocalDate, Integer> getSortedMap() {
        return this.sortedData;
    }
    //endregion

    //region ====[ Private ]====
    /** A function that will create the SortedMap.
     * It creates the SortedMap by started a boxed Int Stream of size 0 to the
     * length of the data in the DataStructure.
     * It then creates a Map using Collectors functions
     * where the first function is a function converting the KeySet of DataStructure into a Stream
     * and mapping it through a Class LocalDateFormatter with the function parseDDMMYYYY
     * then converting it back to a list, and lastly calling the get method using the
     * current index provided by th IntStream mentioned at the start.
     *
     * This is due to how the data received from the API is structured it cannot be
     * parsed through the LocalDate parser without a formatter pattern, this Class
     * tries to parse th date through a number of parses with different patterns until one works.
     *
     * The second function is a function that converts the values of the DataStructure from Strings
     * to Ints by also converting the values' collection into a stream and mapping it through the
     * Integer parseInt function then converting it back into a list and lastly calling the get method using the
     * current index provided by th IntStream mentioned at the start.
     *
     * @return SortedMap with LocalDates as Keys and Integers as Values
     * @see TreeMap
     * @see IntStream
     * @see Collectors
     */
    private SortedMap<LocalDate, Integer> generateSortedMap() {
        return new TreeMap<LocalDate, Integer>(IntStream.range(0, this.APIData.getDateDataLookup().size())
            .boxed().collect(Collectors.toMap(
                this.APIData.getDateDataLookup().keySet().stream().map(LocalDateFormatter::parse).toList()::get,
                this.APIData.getDateDataLookup().values().stream().map(Integer::parseInt).toList()::get)));
    }
    //endregion
}
