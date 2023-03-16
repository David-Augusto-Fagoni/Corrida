package View;
import java.util.concurrent.Semaphore;

import controller.ThreadCorrida;

public class Main {
	
	public static void main(String args[]){
		Semaphore Largada = new Semaphore(5);

		for (int J=2;J<=14; J = J+2) {
			ThreadCorrida Corrida = new ThreadCorrida(J,Largada);
			Corrida.start();
		}
	}
}
