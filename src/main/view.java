package main;

import java.util.concurrent.Semaphore;

import controller.decolagem;

public class view {

	public static void main(String[] args) {
		
		Semaphore mutex1 = new Semaphore(1);
		Semaphore mutex2 = new Semaphore(1);
		Semaphore farol = new Semaphore(2);
		
		for(int aviao = 0; aviao <= 12; aviao++) {
			Thread decolagem = new decolagem(aviao, mutex1, mutex2, farol);
			decolagem.start();
		}

	}

}
