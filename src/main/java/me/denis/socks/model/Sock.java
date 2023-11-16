package me.denis.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sock {

    private Colour colour;
    private Size size;
    private int cotton;

}
