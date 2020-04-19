package proyectoProgramacion;
import java.util.Scanner;

public class Movie {
	
	
	
	private String name;
	
	private String director;
	
	private String type;
	
	private int year;
	
	private int valoration;
	
	private int copies;
	
	private int maxCopies;

	public Movie() {
		
	}
	
	public Movie(String name,String director, String type, int year, int valoration, int copies) {
		this.name = name;
		this.director = director;
		this.type = type;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		
		if (type.equalsIgnoreCase("ciencia ficción")) {
			this.type=type;
			
		} else if(type.equalsIgnoreCase("animación")) {
			Scanner s = new Scanner(System.in);
			this.type=type;
			System.out.println("¿Qué tipo de animación? tradicional, por ordenador o stop motion");
			String animation = s.nextLine();
			
			if (animation.equalsIgnoreCase("tradicional")|| animation.equalsIgnoreCase("por ordenador") || animation.equalsIgnoreCase("stop motion")) {
				this.type=type+" "+animation;
			}
		}else {
			System.out.println("No se puede añadir una película de distinto tipo que ciencia ficción o animación");
		}
		
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

	public void setCopies(int copy) {
		this.copies = copy;
	}
	
	public int getMaxCopies() {
		return maxCopies;
	}

	
	@Override
	public String toString() {
		
		return "Nombre: "+name+"\n Director: "+director+"\n Año de lanzamiento: "+year+"\n Valoración: "+valoration+"\n Copias disponibles: "+copies;
	}
	
}
