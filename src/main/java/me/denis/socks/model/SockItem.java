package me.denis.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SockItem {

    private Sock sock;
    private int quantity;

}
