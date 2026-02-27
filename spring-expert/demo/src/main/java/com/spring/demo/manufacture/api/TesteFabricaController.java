package com.spring.demo.manufacture.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.manufacture.CarroStatus;
import com.spring.demo.manufacture.Chave;
import com.spring.demo.manufacture.HondaHRV;
import com.spring.demo.manufacture.Motor;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    @Autowired
    // @Qualifier("motorTurbo")
    @Turbo
    private Motor motor;

    @PostMapping
    public CarroStatus ligarCarro(@RequestBody Chave chave) {
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }
}
