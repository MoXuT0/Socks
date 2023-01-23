package me.denis.socks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.denis.socks.model.SockItem;
import me.denis.socks.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Склад носков", description = "Операции для работы с носками")
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    @Operation(summary = "Добавление носков", description = "Добавление носков на склад по схеме")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки добавлены"),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<?> addSocks(@RequestBody SockItem sockItem) {
        socksService.add(sockItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Отпуск носков", description = "Отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки отпущены"),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<?> releaseSocks(@RequestBody SockItem sockItem) {
        socksService.release(sockItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Поиск носков", description = "Поиск носков на складе по характеристикам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки найдены"),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<Integer> getSocks(@RequestParam(name = "colour") String colour,
                             @RequestParam(name = "size") double size,
                             @RequestParam(required = false, defaultValue = "0", name = "cottonMin") int cottonMin,
                             @RequestParam(required = false, defaultValue = "100", name = "cottonMax") int cottonMax) {
        int available = socksService.get(colour, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping
    @Operation(summary = "Списывание носков", description = "Списывание бракованных носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Носки списаны"),
            @ApiResponse(responseCode = "400", description = "Ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка на сервере")
    })
    public ResponseEntity<?> deleteSock(@RequestBody SockItem sockItem) {
        socksService.delete(sockItem);
        return ResponseEntity.ok().build();
    }

}
