package me.denis.socks.model;

import com.fasterxml.jackson.annotation.JsonValue;
public enum Size {

    S(36),
    M(37),
    L(37.5);

    private final double size;

    Size(double size) {
        this.size = size;
    }

    @JsonValue
    public double getSize() {
        return size;
    }

    public static Size parse(double size) {
        for (Size s : values()) {
            if (Double.compare(s.size, size) == 0) {
                return s;
            }
        }
        return null;
    }

}
