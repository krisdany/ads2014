/*Adicição na classe Taxi um método para simular uma corrida de táxi. Adição também de um
atributo para identificar o táxi*/

public class Taxi {
	private CentralDeTaxi central;
	private int id;
	private static int contador = 0;

	public Taxi(CentralDeTaxi central){
		this.central = central;
		this.id = Taxi.contador ++;
	}
	public int getId(){
		return id;
	}

	public void atende(){
		try{
			Thread.sleep((long) (Math.random() * 3000.0));
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		this.central.adicionaTaxiDisponinivel(this);
	}

}

