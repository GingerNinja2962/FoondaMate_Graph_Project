package Grapher.Printer.BarGraph;

import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedMap;

public class BarGraph {
    private final SortedMap<LocalDate, Integer> Data;
    private final int Max;
    private final int Min;
    private final int Size;

    public BarGraph(SortedMap<LocalDate, Integer> data) {
        this.Data = data;
        this.Max = max();
        this.Min = min();
        this.Size = size();
    }

    public void print() {
        System.out.println("====[ Bar-Graph ]====\n");
        for (LocalDate date : this.Data.keySet())
            System.out.println(generateBar(date));
        System.out.println("\n====[ End Of Graph ]====");
    }

    private String generateBar(LocalDate Date) {
        String bar = String.format("%s: \033[7m", Date);
        int value = this.Data.get(Date);

        for (int i = value - this.Min; i >= 0; i -= this.Size) {
            bar = bar + " ";
        }
        return String.format(bar+"\033[0m - %s", value);
    }

    private int max() {
        return Collections.max(this.Data.values().stream().toList());
    }

    private int min() {
        return Collections.min(this.Data.values().stream().toList());
    }

    private int size() {
        return (this.Max - this.Min)/50;
    }
}
