package View;
import java.util.concurrent.Semaphore;

import controller.ThreadCorrida;

public class Main {
	
	public static void main(String args[]){
		Semaphore Largada = new Semaphore(5);
		Semaphore Colocação = new Semaphore(1);
		Semaphore Arruma = new Semaphore(1);
		for (int J=2;J<=14; J = J+2) {
			ThreadCorrida Corrida = new ThreadCorrida(J,Largada,Colocação,Arruma);
			Corrida.start();
		}
	}
}
