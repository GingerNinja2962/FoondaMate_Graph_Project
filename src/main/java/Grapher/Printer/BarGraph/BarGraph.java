package Grapher.Printer.BarGraph;

import java.util.Collections;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class BarGraph {
    private final SortedMap<String, String> Data;
    private final int Max;
    private final int Min;
    private final int Size;

    public BarGraph(SortedMap<String, String> data) {
        this.Data = data;
        this.Max = max();
        this.Min = min();
        this.Size = size();
    }

    public void print() {
        System.out.println("====[ BarGraph ]====\n");
        for (String date : this.Data.keySet())
            System.out.println(generateBar(date));
        System.out.println("====[ Finished ]====");
    }

    private String generateBar(String Date) {
        String bar = String.format("%s: \033[7m", Date);
        int value = Integer.parseInt(this.Data.get(Date));

        for (int i = value - this.Min; i >= 0; i -= this.Size) {
            bar = bar + " ";
        }
        return String.format(bar+" \033[0m - %s", value);
    }

    private int max() {
        return Collections.max(this.Data.values().stream().map(Integer::parseInt).collect(Collectors.toList()));
    }

    private int min() {
        return Collections.min(this.Data.values().stream().map(Integer::parseInt).collect(Collectors.toList()));
    }

    private int size() {
        return (this.Max - this.Min)/50;
    }
}
