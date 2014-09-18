import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/*Implementação do método adicionaTaxiDisponivel() da classe CentralDeTaxi. Esse método
deve receber um Taxi como parâmetro e adicioná-lo à fila de táxis livres.  */


public class CentralDeTaxi {
	private ArrayList<Taxi> taxisLivres = new ArrayList<Taxi>();
	private ArrayList<Passageiro> passageirosEmEspera = new ArrayList<Passageiro>();

	public synchronized void adicionaTaxiDisponinivel(Taxi taxi){
		//JOptionPane.showMessageDialog(null,"Taxi " +taxi.getId() +"\n" + "Voltou pra fila ");
		System.out.print("Taxi " +taxi.getId() +"\n" + "Voltou pra fila ");
		taxisLivres.add(taxi);
		this.notifyAll();
	}

	public void pedeTaxi(Passageiro passageiro){
		Taxi taxi = this.esperaTaxi(passageiro);
		//JOptionPane.showMessageDialog(null,"Taxi " + taxi.getId() +"\n" + "Levando " + passageiro.getNome());
		System.out.print("Taxi " + taxi.getId() +"\n" + "Levando " + passageiro.getNome());
		
		taxi.atende();
	}
	private Taxi esperaTaxi(Passageiro passageiro){
		this.passageirosEmEspera.add(passageiro);
		synchronized (this){
			while (this.taxisLivres.isEmpty()
					|| !this.passageirosEmEspera.get(0).equals(passageiro)){
				try{
					this.wait();

				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			this.passageirosEmEspera.remove(0);
			return this.taxisLivres.remove(0);
		}


	}
}




