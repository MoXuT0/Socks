package me.denis.socks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sock {

    private Colour colour;
    private Size size;
    private int cotton;

}
