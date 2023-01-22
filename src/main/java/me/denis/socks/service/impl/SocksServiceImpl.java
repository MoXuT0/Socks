package me.denis.socks.service.impl;

import me.denis.socks.model.Colour;
import me.denis.socks.model.Size;
import me.denis.socks.model.Sock;
import me.denis.socks.model.SockItem;
import me.denis.socks.service.SocksService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {

    private final Map<Sock, Integer> socks = new HashMap<>();

    @Override
    public void add(SockItem sockItem) {
        validate(sockItem);
        Sock sock = sockItem.getSock();
        if (socks.containsKey(sock)) {
            socks.replace(sock, socks.get(sock) + sockItem.getQuantity());
        } else {
            socks.put(sock, sockItem.getQuantity());
        }
    }

    @Override
    public void release(SockItem sockItem) {
        validate(sockItem);
        Sock sock = sockItem.getSock();
        if (!socks.containsKey(sock)) {
            throw new RuntimeException();
        } else {
            int oldQuantity = sockItem.getQuantity();
            int newQuantity = oldQuantity - sockItem.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException();
            }
            socks.replace(sock, newQuantity);
        }
    }

    @Override
    public int get(String colour, double size, int cottonMin, int cottonMax) {
        Colour c = Colour.parse(colour);
        Size s = Size.parse(size);
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            int available = entry.getValue();
            if (sock.getColour() == c && sock.getSize() == s && sock.getCotton() >= cottonMin && sock.getCotton() <= cottonMax) {
                return available;
            }
        }
        return 0;
    }

    @Override
    public void delete(SockItem sockItem) {
        validate(sockItem);
        Sock sock = sockItem.getSock();
        if (!socks.containsKey(sock)) {
            throw new RuntimeException();
        } else {
            socks.remove(sock, sockItem.getQuantity());
        }
    }

    private void validate(SockItem sockItem) {
        Sock sock = sockItem.getSock();
        if (sock.getColour() == null || sock.getSize() == null) {
            throw new RuntimeException("Все поля должны быть заполнены");
        }
        if (sock.getCotton() < 0 || sock.getCotton() > 100) {
            throw new RuntimeException("Процент хлопка должен быть между 0 и 100");
        }
        if (sockItem.getQuantity() <= 0) {
            throw new RuntimeException("Количество должно быть больше 0");
        }
    }

}
