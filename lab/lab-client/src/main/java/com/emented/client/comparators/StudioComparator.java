package com.emented.client.comparators;

import com.emented.client.entities.MusicBand;

import java.util.Comparator;

public class StudioComparator implements Comparator<MusicBand> {
    @Override
    public int compare(MusicBand a, MusicBand b) {
        if (a.getStudio() == null) {
            return -1;
        } else if (b.getStudio() == null) {
            return 1;
        }
        return a.getStudio().compareTo(b.getStudio());
    }
}
