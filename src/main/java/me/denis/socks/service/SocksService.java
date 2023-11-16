package me.denis.socks.service;

import me.denis.socks.model.Colour;
import me.denis.socks.model.Size;
import me.denis.socks.model.SockItem;

public interface SocksService {

    void add(SockItem sockItem);

    void release(SockItem sockItem);

    int get(String colour, double size, int cottonMin, int cottonMax);

    void delete(SockItem sockItem);

}
