package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorrida extends Thread {
	private int Corredor;
	private Semaphore largada;
	private Semaphore colocação;
	private Semaphore arruma;
	private static float Equipes[] [] = new float [2][15];
	private static int i=0;
	
	public ThreadCorrida (int Corredor, Semaphore largada, Semaphore colocação, Semaphore arruma) {
		this.Corredor = Corredor;
		this.largada = largada;
		this.colocação = colocação;
		this.arruma = arruma;
	}
	public void run () {

			Corrida();

		}

	private void Corrida() {
		int[] VT = new int [2];
		VT[0] = Corredor-1;
		VT[1] = Corredor;
		float[] Tempos = new float [3];
		float Temp;
		for (int Vetor=0;Vetor<2;Vetor++){
			try {
				largada.acquire();
				System.out.println("O carro "+VT[Vetor]+" da equipe "+Corredor/2+" entrou na corrida");
				for (int Volta=0;Volta<3;Volta++){
					Temp = (float) (Math.random()*41+20);
					System.out.println("O carro "+VT[Vetor]+" da equipe "+Corredor/2+" demorou "+Temp+"s para fazer a º"+(Volta+1)+" volta.");
					Tempos[Volta] = Temp;

				}
				float J=0;
				int Bubble = 0;
				while (Bubble != 1) {
					Bubble = 1;
					for (int V = 0;V>2;V++) {
						if (Tempos[V] < Tempos[V+1]) {
							J = Tempos[V];
							Tempos[V] = Tempos[V+1];
							Tempos[V+1] = Tempos[V];
							Bubble = 0;
							
						}
					}
				}
				
				Equipes[0] [VT[Vetor]] = Tempos[0];
				Equipes[1] [VT[Vetor]] = VT[Vetor];
				System.out.println("O carro "+VT[Vetor]+" da equipe "+Corredor/2+" saiu da corrida");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				i=i+1;
				largada.release();
			}
			float J=0;
			float K=0;
			int Bubble = 0;
			if (i == 14) {
				while (Bubble != 1) {
					Bubble = 1;
					for (int V = 1;V<=13;V++) {
						if (Equipes[0][V] > Equipes[0][V+1]) {
							J = Equipes[0][V];
							K = Equipes[1][V];
							Equipes[0][V] = Equipes[0][V+1];
							Equipes[1][V] = Equipes[1][V+1];
							Equipes[0][V+1] = J;
							Equipes[1][V+1] = K;
							Bubble = 0;
						}
					}
				}
				
				for (J = 1;J<15;J++) {
					System.out.println("O "+J+"º lugar foi o Carro "+Equipes[1][(int) J]+" com o tempo: "+Equipes[0][(int) J]);
				}
			}
		}
	}
}
