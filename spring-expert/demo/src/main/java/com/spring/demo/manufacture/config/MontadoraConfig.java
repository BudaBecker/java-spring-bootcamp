package com.spring.demo.manufacture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.manufacture.Motor;
import com.spring.demo.manufacture.enums.TipoMotor;

@Configuration
public class MontadoraConfig {

    @Bean
    public Motor motor() {
        var motor = new Motor();
        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setLitragem(2.0);
        motor.setModelo("XPTO-0");
        motor.setTipoMotor(TipoMotor.ASPIRADO);
        return motor;
    }
}
