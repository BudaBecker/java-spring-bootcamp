package com.spring.demo.manufacture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.demo.manufacture.Motor;
import com.spring.demo.manufacture.enums.TipoMotor;

@Configuration
public class MontadoraConfig {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado() {
        var motor = new Motor();
        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setLitragem(2.0);
        motor.setModelo("XPTO-0");
        motor.setTipoMotor(TipoMotor.ASPIRADO);
        return motor;
    }

    @Bean
    public Motor motorEletrio() {
        var motor = new Motor();
        motor.setCavalos(110);
        motor.setCilindros(3);
        motor.setLitragem(1.4);
        motor.setModelo("TH-10");
        motor.setTipoMotor(TipoMotor.ELETRICO);
        return motor;
    }

    @Bean
    @Primary
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setCavalos(180);
        motor.setCilindros(4);
        motor.setLitragem(1.5);
        motor.setModelo("XPTO-0");
        motor.setTipoMotor(TipoMotor.TURBO);
        return motor;
    }
}
