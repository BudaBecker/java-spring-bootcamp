package com.spring.demo.manufacture;

import java.awt.Color;

import com.spring.demo.manufacture.enums.Montadora;

public class HondaHRV extends Carro {

    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.LIGHT_GRAY);
        setMontadora(Montadora.HONDA);
    }

}
