package me.denis.socks.controllers;

import me.denis.socks.model.Colour;
import me.denis.socks.model.Size;
import me.denis.socks.model.SockItem;
import me.denis.socks.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    public ResponseEntity<?> addSocks(@RequestBody SockItem sockItem) {
        socksService.add(sockItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> releaseSocks(@RequestBody SockItem sockItem) {
        socksService.release(sockItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam(required = false, name = "colour") String colour,
                             @RequestParam(required = false, name = "size") double size,
                             @RequestParam(required = false, name = "cottonMin") int cottonMin,
                             @RequestParam(required = false, name = "cottonMax") int cottonMax) {
        int available = socksService.get(colour, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSock(@RequestBody SockItem sockItem) {
        socksService.delete(sockItem);
        return ResponseEntity.ok().build();
    }

}
