package com.krakedev.financiero.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestBancoJUnit {
	
	@Test
	public void testCrearCuentasConCodigosConsecutivos() {

		Banco banco = new Banco();

		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");

		Cuenta cuenta1 = banco.crearCuenta(cliente);
		Cuenta cuenta2 = banco.crearCuenta(cliente);

		assertEquals("1000", cuenta1.getId());
		assertEquals("1001", cuenta2.getId());
	}

}
