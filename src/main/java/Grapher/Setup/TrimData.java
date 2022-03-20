package Grapher.Setup;

import Grapher.Printer.DataManager.DataManager;

import java.util.TreeMap;
import java.util.SortedMap;
import java.time.LocalDate;

/** A simple class that trims the APIData's SortedMap to the dates specified
 * in the command line launch arguments.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-20
 */
public class TrimData {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private TreeMap<LocalDate, Integer> APIData;

    //region ====[ Constructor ]====
    /** The TrimData class constructor. The APIData is trimmed and
     * stored within this class upon instantiation.
     *
     * @param args ArgHandler containing the command line launch args.
     * @param APIData DataManager containing the requested data from the API.
     */
    public TrimData(ArgHandler args, DataManager APIData) {
        this.APIData = new TreeMap<LocalDate, Integer>(APIData.getSortedMap());
        this.startDate = args.getStart();
        this.endDate = args.getEnd();
        trimStartDate();
        trimEndDate();
    }
    //endregion

    //region ====[ Getters ]====
    public SortedMap<LocalDate, Integer> getTrimmedAPIData() {
        return APIData;
    }
    //endregion

    //region ====[ Private ]====
    private void trimStartDate() {
        if (this.startDate == null) return;
        if (!this.APIData.firstKey().isBefore(this.startDate)) return;

        this.APIData = new TreeMap<LocalDate, Integer>(this.APIData.subMap(this.startDate, true, this.APIData.lastKey(), true));
    }

    private void trimEndDate() {
        if (this.endDate == null) return;
        if (!this.APIData.lastKey().isAfter(this.endDate)) return;

        this.APIData = new TreeMap<LocalDate, Integer>(this.APIData.subMap(this.APIData.firstKey(), true, this.endDate, true));
    }
    //endregion
}
