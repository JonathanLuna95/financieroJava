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

	@Test
	public void testDepositarMontoValido() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");
		Cuenta cuenta = banco.crearCuenta(cliente);

		boolean resultado = banco.depositar(500.00, cuenta);

		assertEquals(true, resultado);
		assertEquals(500.00, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testDepositarMontoInvalido() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");
		Cuenta cuenta = banco.crearCuenta(cliente);

		boolean resultado = banco.depositar(-100.00, cuenta);

		assertEquals(false, resultado);
		assertEquals(0.0, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetirarMontoValido() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");
		Cuenta cuenta = banco.crearCuenta(cliente);

		banco.depositar(500.00, cuenta);

		boolean resultado = banco.retirar(200.00, cuenta);

		assertEquals(true, resultado);
		assertEquals(300.00, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testRetirarSaldoInsuficiente() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");
		Cuenta cuenta = banco.crearCuenta(cliente);

		banco.depositar(100.00, cuenta);

		boolean resultado = banco.retirar(200.00, cuenta);

		assertEquals(false, resultado);
		assertEquals(100.00, cuenta.getSaldoActual(), 0.0001);
	}

	@Test
	public void testTransferirExitoso() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");

		Cuenta origen = banco.crearCuenta(cliente);
		Cuenta destino = banco.crearCuenta(cliente);

		banco.depositar(500.00, origen);

		boolean resultado = banco.transferir(origen, destino, 200.00);

		assertEquals(true, resultado);
		assertEquals(300.00, origen.getSaldoActual(), 0.0001);
		assertEquals(200.00, destino.getSaldoActual(), 0.0001);
	}

	@Test
	public void testTransferirSaldoInsuficiente() {

		Banco banco = new Banco();
		Cliente cliente = new Cliente("1723919591", "Jonathan", "Luna");

		Cuenta origen = banco.crearCuenta(cliente);
		Cuenta destino = banco.crearCuenta(cliente);

		banco.depositar(100.00, origen);

		boolean resultado = banco.transferir(origen, destino, 200.00);

		assertEquals(false, resultado);
		assertEquals(100.00, origen.getSaldoActual(), 0.0001);
		assertEquals(0.00, destino.getSaldoActual(), 0.0001);
	}

}
