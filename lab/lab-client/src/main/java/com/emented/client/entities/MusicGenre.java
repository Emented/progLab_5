package com.emented.client.entities;

public enum MusicGenre {
    PROGRESSIVE_ROCK,
    PSYCHEDELIC_CLOUD_RAP,
    JAZZ,
    BLUES,
    BRIT_POP;

    public static String show() {
        StringBuilder sb = new StringBuilder();
        for (MusicGenre j : values()) {
            sb.append(j);
            sb.append("\n");
        }
        return sb.toString();
    }
}
