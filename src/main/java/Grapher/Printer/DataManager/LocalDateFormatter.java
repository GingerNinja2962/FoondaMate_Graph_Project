package Grapher.Printer.DataManager;

import com.joestelmach.natty.Parser;

import java.time.ZoneId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a simple formatting class used in the DataManager class to convert
 * the strings of date with format DD-MM-YYYY to LocalDate format.
 * Since the standard format for LocalDate is yyyy-MM-dd this is unfortunately required.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-19
 */
public class LocalDateFormatter {

    //region ====[ parsers ]====
    /** A simple function that converts String with a date
     * into a LocalDate object with the date of the provided String.
     *
     * It runs through a lot of different patterns until it finds
     * one that works or runs out of patters
     *
     * @param date The date to be converted as a String.
     * @return The LocalDate with the date of the string provided.
     */
    public static LocalDate parse(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException d) {
            return parseUsingNatty(date);
        }
    }
    //endregion

    //region ====[ Deprecated ]====
    /** This uses Netty which stopped being maintained in 2017.
     * If possible this should not be used however I could not
     * find any other packages that worked as well as this does.
     *
     * @param date The date String to be interpreted.
     * @return A LocalDate object of String date.
     * @deprecated This is a very old Library and is no longer maintained.
     */
    private static LocalDate parseUsingNatty(String date) {
        try {
            return new Parser().parse(date).get(0).getDates().get(0)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }
    //endregion
}
