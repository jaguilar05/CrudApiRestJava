package com.bootcamp.cuenta;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancariaService {

    private CuentaBancariaRepository cuentaBancariaRepository;


    public CuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository){
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }


    public List<CuentaBancaria> getAllCuentas(){
        return cuentaBancariaRepository.findAll();
    }
}
