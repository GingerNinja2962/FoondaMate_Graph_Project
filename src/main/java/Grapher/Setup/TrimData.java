package Grapher.Setup;

import Grapher.Printer.DataManager.DataManager;

import java.util.TreeMap;
import java.util.SortedMap;
import java.time.LocalDate;

public class TrimData {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private TreeMap<LocalDate, Integer> APIData;

    public TrimData(ArgHandler args, DataManager APIData) {
        this.APIData = new TreeMap<LocalDate, Integer>(APIData.getSortedMap());
        this.startDate = args.getStart();
        this.endDate = args.getEnd();
        trimStartDate();
        trimEndDate();
    }

    public SortedMap<LocalDate, Integer> getTrimmedAPIData() {
        return APIData;
    }

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
}
