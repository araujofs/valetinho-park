package src.appconsole;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import src.modelo.Cliente;
import src.modelo.Conta;
import src.modelo.Lancamento;
import src.repositorio.Repositorio;

public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas-----");
			for(Conta c : Repositorio.getAll(Conta.class)) { 
				System.out.println(c);
				for(Lancamento lan : c.getLancamentos()) 
					System.out.println("   "+lan);
			}

			System.out.println("\n---------listagem de clientes ----");
			for(Cliente e : Repositorio.getAll(Cliente.class)) 
				System.out.println(e);
			
			
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main (String[] args) 
	{
		new Listar();
	}
}


