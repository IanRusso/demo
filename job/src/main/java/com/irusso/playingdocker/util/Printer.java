package com.irusso.playingdocker.util;

import com.irusso.playingdocker.model.Location;
import com.irusso.playingdocker.model.GroundStation;
import com.irusso.playingdocker.model.Observatory;

import java.util.List;

public class Printer {

    public static void printGroundStations(List<GroundStation> groundStations) {
        System.out.println(
            pad("Name", 30)
                + "|" + pad("ID", 28)
                + "|" + pad("Location", 46)
        );
        groundStations.forEach(station ->
            System.out.println(pad(station.getName(), 30)
                + "|" + pad(String.valueOf(station.getName()), 28)
                + "|" + pad(formatLocation(station.getLocation()), 46)
            ));
    }

    public static void printObservatories(List<Observatory> observatories) {
        System.out.println(
            pad("Name", 36)
                + "|" + pad("Camera Resolution", 22)
                + "|" + pad("Start Time", 40)
                + "|" + pad("End Time", 40)
                + "|" + pad("Group ID", 40)
        );
        observatories.forEach(obsv ->
            System.out.println(pad(obsv.getName(), 36)
                + "|" + pad(String.valueOf(obsv.getResolution()), 22)
                + "|" + pad(String.valueOf(obsv.getStartTime()), 40)
                + "|" + pad(String.valueOf(obsv.getEndTime()), 40)
                + "|" + pad(obsv.getGroupId() == null ? "None" : obsv.getGroupId(), 40)
            ));
    }


    private static String formatLocation(Location location) {
        String latitude = formatDouble(location.getLatitude(), 2);
        String longitude = formatDouble(location.getLongitude(), 2);

        return "[" + latitude + "," + longitude + "]";
    }

    private static String formatDouble(double val, int placesAfterDecimal) {
        String doubleText = String.valueOf(val);

        String[] pieces = doubleText.split("\\.");
        if (pieces.length == 1) {
            return doubleText + "." + repeat("0", placesAfterDecimal);
        }

        String beforeDecimal = pieces[0];
        String afterDecimal = pieces[1];

        if (afterDecimal.length() < placesAfterDecimal) {
            return doubleText + repeat("0", (placesAfterDecimal - afterDecimal.length()));
        }
        return doubleText.substring(0, beforeDecimal.length() + placesAfterDecimal + 1);
    }

    private static String pad(String name, int i) {
        int charsNeeded = i - name.length();
        if (charsNeeded > 0) {
            int beforeCount = i / 6;
            String spacesBefore = repeat(" ", beforeCount);
            String spacesAfter = repeat(" ", (i - beforeCount - name.length()));
            String padded = spacesBefore + name + spacesAfter;
            return padded;
        }
        if (charsNeeded < 0) {
            return name.substring(0, i);
        }
        return name;
    }

    private static String repeat(String x, int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; i++) {
            sb.append(x);
        }
        return sb.toString();
    }
}
