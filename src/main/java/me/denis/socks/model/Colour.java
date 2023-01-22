package me.denis.socks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Colour {

    BLACK("black"),
    RED("red"),
    YELLOW("yellow");

    private final String colour;

    Colour(String colour) {
        this.colour = colour;
    }

    @JsonValue
    public String getColour() {
        return colour;
    }

    @JsonCreator
    public static Colour parse(String colour) {
        for (Colour c : values()) {
            if (c.name().equals(colour)) {
                return c;
            }
        }
        return null;
    }

}
