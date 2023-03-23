package controller;

import java.util.concurrent.Semaphore;

public class decolagem extends Thread {

	private int aviao;
	private Semaphore mutex1;
	private Semaphore mutex2;
	private Semaphore farol;
	private static int pista = 0;

	public decolagem(int aviao, Semaphore mutex1, Semaphore mutex2, Semaphore farol) {
		this.aviao = aviao;
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
		this.farol = farol;
	}

	public void run() {
		try {
			farol.acquire();
			EscolhaPista();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			farol.release();
		}

	}
	//Escolhe a pista e exibe mensagem de decolagem	
	private void EscolhaPista() {
		pista = (int) (Math.random() * 2) + 1;
		System.out.println(pista);

		if (pista == 1) {
			try {
				mutex1.acquire();
				System.out.println("O Aviao " + aviao + " entrou na pista NORTE e vai começar os procedimentos de decolagem\n");
				Decolagem();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mutex1.release();
			}
		} else {
			if (pista == 2) {
				try {
					mutex2.acquire();
					System.out.println("O Aviao " + aviao + " entrou na pista SUL e vai começar os procedimentos de decolagem\n");
					Decolagem();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mutex2.release();
				}
			}
		}
	}

	private void Decolagem() {
		int manobra = (int) (Math.random() * 4001) + 3000;
		try {
			System.out.println("O Aviao " + aviao + " está manobrando, aguarde " + (manobra/1000) + " segundos\n");
			sleep(manobra);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int taxiar = (int) (Math.random() * 5001) + 5000;
		try {
			System.out.println("O Aviao " + aviao + " está taxiando, aguarde " + (taxiar/1000) + " segundos\n");
			sleep(taxiar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int decolagem = (int) (Math.random() * 3001) + 1000;
		try {
			System.out.println("O Aviao " + aviao + " está decolando, aguarde " + (decolagem/1000) + " segundos\n");
			sleep(decolagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int afastamento = (int) (Math.random() * 5001) + 3000;
		try {
			System.out.println("O Aviao " + aviao + " ja decolou e está na fase de afastamento, aguarde " + (afastamento/1000) + " segundos\n");
			sleep(afastamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nO Aviao " + aviao + " ACABOU DE DECOLAR E A PISTA ESTÁ LIBERADA\n");
	}

}
