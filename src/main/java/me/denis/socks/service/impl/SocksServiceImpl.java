package me.denis.socks.service.impl;

import me.denis.socks.exception.IncorrectParamException;
import me.denis.socks.model.Colour;
import me.denis.socks.model.Size;
import me.denis.socks.model.Sock;
import me.denis.socks.model.SockItem;
import me.denis.socks.service.SocksService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            throw new RuntimeException("Таких носков нет");
        } else {
            int available = socks.get(sock);
            int result = available - sockItem.getQuantity();
            if (result < 0) {
                throw new IncorrectParamException("Недостаточно носков");
            }
            socks.put(sock, result);
        }
    }

    @Override
    public int get(String colour, double size, int cottonMin, int cottonMax) {
        Colour c = Colour.parse(colour);
        Size s = Size.parse(size);
        if(Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax || cottonMin < 0 || cottonMax > 100) {
            throw new IncorrectParamException("Неправильно заполнены параметры");
        }
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            int available = entry.getValue();
            if (sock.getColour() == c && sock.getSize() == s &&
                    sock.getCotton() >= cottonMin && sock.getCotton() <= cottonMax) {
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
            throw new IncorrectParamException("Цвет и размер должны быть заполнены");
        }
        if (sock.getCotton() < 0 || sock.getCotton() > 100) {
            throw new IncorrectParamException("Процент хлопка должен быть от 0 до 100");
        }
        if (sockItem.getQuantity() <= 0) {
            throw new IncorrectParamException("Количество должно быть больше 0");
        }
    }

}
