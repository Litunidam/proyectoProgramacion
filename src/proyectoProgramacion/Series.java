package proyectoProgramacion;

import java.util.ArrayList;

public class Series {

	private String name;
	
	private ArrayList<Season> seasons = new ArrayList<>();
	
	private String director;
	
	private int year;
	
	private int valoration;
	
	private int copies;

	private int maxCopies;
	
	public Series() {
		
	}
	
	public Series(String name, ArrayList<Season> seasons, String director, int year, int valoration,int copies) {
		
		this.name = name;
		this.seasons = seasons;
		this.director = director;
		this.year = year;
		this.valoration = valoration;
		this.copies = copies;
		this.maxCopies = copies;
		}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}
	
	public int getMaxCopies() {
		return maxCopies;
	}
	public int getMaxEpisodes() {
		
		int number = 0;
		
		for (Season x: seasons) {
			
			number+=x.getNumberOfEpisodes();
			
		}
		
		return 1;
	}
	
	@Override
	public String toString() {
		
		return "Nombre: "+name+"\n Temporadas: "+seasons.size()+"\n Capítulos: "+getMaxEpisodes()+"\n Director: "+director+"\n Año de lanzamiento: "+year+"\n Valoración: "+valoration+"\n Copias disponibles: "+copies;
	}
	
	
}
