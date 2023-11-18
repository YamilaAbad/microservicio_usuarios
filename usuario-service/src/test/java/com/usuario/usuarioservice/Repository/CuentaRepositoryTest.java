package com.usuario.usuarioservice.Repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.usuario.usuarioservice.Model.Cuenta;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class CuentaRepositoryTest {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    void testCargarSaldo() {
        int idCuenta = 2;
        float saldoAnterior = cuentaRepository.findById(idCuenta)
                .map(Cuenta::getSaldo)
                .orElse(0.0f);

        float saldoACargar = 100.0f;
        cuentaRepository.cargarSaldo(idCuenta, saldoACargar);
        float saldoActualizado = cuentaRepository.findById(idCuenta)
                .map(Cuenta::getSaldo)
                .orElse(0.0f);

        assertEquals(saldoAnterior + saldoACargar, saldoActualizado, 0.01);
    }

    @Test
    @Transactional
    void testSuspenderCuenta() {
        int idCuenta = 1; // ID de la cuenta existente en tu base de datos de prueba
        String motivoSuspension = "Razón de suspensión de prueba";

        // Realizar la actualización
        cuentaRepository.suspenderCuenta(idCuenta, motivoSuspension);

        // Verificar que la cuenta se haya suspendido correctamente
        Optional<Cuenta> cuentaSuspension = cuentaRepository.findById(idCuenta);

        assertTrue(cuentaSuspension.isPresent()); // Verificar que la cuenta existe

        Cuenta cuenta = cuentaSuspension.get();
        assertFalse(cuenta.isEstado()); // Verificar que el estado sea falso
        assertEquals(motivoSuspension, cuenta.getMotivo_de_suspencion()); // Verificar el motivo de suspensión
    }

    @Test
    public void testBuscarCuenta() {
        //datos de una cuenta ya creada en la bd
        String usuario = "paula52";
        String contraseña = "1234";

        Cuenta cuentaEncontrada = cuentaRepository.buscarCuenta(usuario, contraseña);

        //verifica los datos
        assertNotNull(cuentaEncontrada);
        assertEquals(usuario, cuentaEncontrada.getUser());
        assertEquals(contraseña, cuentaEncontrada.getPass());
    }

    @Test
    public void testGetByUser() {

        String usuario = "paula52";
        Cuenta cuentaEncontrada = cuentaRepository.getByUser(usuario);
        assertNotNull(cuentaEncontrada);
        assertEquals(usuario, cuentaEncontrada.getUser());
    }
}