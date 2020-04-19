package proyectoProgramacion;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Videostore videoclub = new Videostore();
		
		videoclub.createUser("admin","admin");
		
		//Para iniciar sesión como jefe el usuario es admin y la contraseña admin
		loginPanel(videoclub);
		
	}

	public static void loginPanel(Videostore videoclub) {
		
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		String user;
		
		String password;
		
		System.out.println("Inicie sesión");	
		
		System.out.println("Nombre de usuario:");
		
		user = s.nextLine();
		
		System.out.println("Contraseña:");
		
		password = s.nextLine();
		
		menu=videoclub.loginUser(user,password);
		
		menus(videoclub, s, menu,user,password);
	}
	/**
	 * Este método te devuelve el menú según el tipo de usuario con el que te identificas 
	 */
	public static void menus(Videostore videoclub, Scanner s, int menu,String user,String password) {
		int option=0;
		
		switch (menu) {
			case 1:
				while(option<9) {
				System.out.println("Seleccione una opción:");
				option = s.nextInt();
				s.nextLine();
				
				while (option>10 || option<1) {
					System.out.println("Introduzca una opción correcta");
					option = s.nextInt();
					s.nextLine();
				}
				bossOptions(videoclub, s, option,user, password);
				
				break;
				}
			case 2:
				while(option<7) {
				System.out.println("Seleccione una opción:");
				option = s.nextInt();
				s.nextLine();
				
				while (option>8 || option<1) {
					System.out.println("Introduzca una opción correcta");
					option = s.nextInt();
					s.nextLine();
				}
				
				employeeOptions(videoclub, s, option,user,password);
				break;
				}
			case -1:
				
				System.out.println("Se ha cerrado sesión");
				break;
		}
	}

	/**
	 * Todas las opciones del menú del empleado
	 */
	public static void employeeOptions(Videostore videoclub, Scanner s, int option,String user,String password) {
		
		String name;
		String director;
		String type;
		int year;
		int valoration;
		int copies;		
		int control;
		int menu = videoclub.loginUser(user, password);
		
		switch (option) {
		
		case 1:
			
			videoclub.searchElement();
			
			
			/**
			 * Esta función la utilizo en todas las opciones de cada empleado y jefe para volver al menú, se repite mucho 
			 * porque no sabía como hacer para que volviera el menú después de hacer una opción
			 */
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
			}
			break;
			
		case 2:
			
			
			System.out.println("Añadir una película");
			
			System.out.println("Introduzca el nombre:");
			name = s.nextLine();
			
			System.out.println("Introduzca el dombre del director");
			director = s.nextLine();
			
			System.out.println("Introduzca el tipo de película, ciencia ficción o animación");
			type = s.nextLine();
			
			while(!type.equals("ciencia ficción")&&!type.equals("animación")){
				
				System.out.println("Solo puede ser el tipo ciencia ficción o animación");
				type = s.nextLine();
				
			}
			if(type.equalsIgnoreCase("animación")) {
				String aux;
				System.out.println("¿Qué tipo de animación? tradicional, por ordenador o stop motion.");
				
				aux = s.nextLine();
				while (!aux.equalsIgnoreCase("tradicional")&&!aux.equalsIgnoreCase("por ordenador")&&!aux.equalsIgnoreCase("stop motion")) {
					aux = s.nextLine();
				}
				type = type + " "+aux;
			}
			
			System.out.println("Introduzca el año de estreno:");
			year = s.nextInt();
			s.nextLine();
			
			System.out.println("Introduzca una valoración, entre 1 y 5:");
			valoration = s.nextInt();
			s.nextLine();
			
			while(valoration<1||valoration>5) {
				System.out.println("La valoración solo puede ser entre 1 y 5");
				valoration = s.nextInt();
				s.nextLine();
			}
			
			System.out.println("Introduzca la cantidad de copias disponibles:");
			copies = s.nextInt();
			s.nextLine();
			
			while(copies<1) {
				System.out.println("La cantidad no puede ser inferior a 1");
				copies = s.nextInt();
				s.nextLine();			
			}
			
			videoclub.addMovie(name, director, type, year, valoration, copies);
		
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			break;
			
		case 3:
			
			int rating;
			int numberOfSeasons;
			int numberOfEpisodes;
			System.out.println("Añadir una serie");
			
			System.out.println("Introduzca el nombre:");
			name = s.nextLine();
			
			System.out.println("Introduzca el número de temporadas:");
			numberOfSeasons = s.nextInt();
			s.nextLine();
			ArrayList<Season> seasons = new ArrayList<>();
			
			for (int i = 0;i<numberOfSeasons;i++) {
		
				Season season = new Season();
				
				season.setName("Temporada "+(i+1));
				
				System.out.println("Introduzca la valoración de la temporada "+(i+1));
				rating = s.nextInt();
				s.nextLine();
				
				while(rating<1||rating>5) {
					System.out.println("La valoración solo puede ser entre 1 y 5");
					rating = s.nextInt();
					s.nextLine();
				}
				season.setValoration(rating);
				
				System.out.println("Introduzca el número de capítulos de la temporada "+(i+1));
				numberOfEpisodes=s.nextInt();
				s.nextLine();
				
				for (int j=0;j<numberOfEpisodes;j++) {
					
					String episodeName;
					
					Episode episode  = new Episode();
					
					System.out.println("Introduzca el nombre del episodio:");
					episodeName = s.nextLine();
					
					episode.setName(episodeName);
					
					System.out.println("Introduzca una valoración al capítulo: "+episodeName);
					valoration =s.nextInt();
					s.nextLine();
					
					while(rating<1||rating>5) {
						System.out.println("La valoración solo puede ser entre 1 y 5");
						rating = s.nextInt();
						s.nextLine();
					}
					
					episode.setValoration(rating);
				
					season.setEpisodes(episode);
				}
				
				seasons.add(season);
			
			
			}
			
			System.out.println("Introduzca el dombre del director");
			director = s.nextLine();
			
			System.out.println("Introduzca el año de estreno:");
			year = s.nextInt();
			s.nextLine();
			
			System.out.println("Introduzca una valoración, entre 1 y 5:");
			valoration = s.nextInt();
			s.nextLine();
			
			while(valoration<1||valoration>5) {
				System.out.println("La valoración solo puede ser entre 1 y 5");
				valoration = s.nextInt();
				s.nextLine();
			}
			
			System.out.println("Introduzca la cantidad de copias disponibles:");
			copies = s.nextInt();
			s.nextLine();
			
			while(copies<1) {
				System.out.println("La cantidad no puede ser inferior a 1");
				copies = s.nextInt();
				s.nextLine();			
			}
			videoclub.addSeries(name, seasons, director, year, valoration, copies);
			
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			
			break;
		case 4:
			
			System.out.println("Alquilar película:");
			System.out.println("Introduzca el nombre de la película a alquilar:");
			
			name = s.nextLine();
			
			videoclub.rentOutMovie(name);
			
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			
			break;
			
		case 5:
			
			System.out.println("Alquilar serie:");
			System.out.println("Introduzca el nombre de la serie a alquilar:");
			
			name = s.nextLine();
			
			videoclub.rentOutSeries(name);
			
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			
			break;
			
		case 6:
			
			System.out.println("Devolver una película");
			System.out.println("Introduzca el nombre de la película a devolver:");
			
			name = s.nextLine();
			
			videoclub.returnMovie(name);
			
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			
			break;
			
		case 7:
			
			System.out.println("Devolver una serie");
			System.out.println("Introduzca el nombre de la serie a devolver:");
			
			name = s.nextLine();
			
			videoclub.returnSeries(name);
			
			for ( User x: videoclub.getUsers() ){
				
				if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
					
					control = videoclub.getUsers().indexOf(x);
					if (control==0) {
						videoclub.managerMenu();
						menus(videoclub, s, menu,user,password);
						
					}else {
						videoclub.employeeMenu();
						menus(videoclub, s, menu,user,password);
				
					}
				}
				
			}	
			
			break;
			
		case 8:
			
			System.out.println("Sesión cerrada:");
			System.out.println("¿Desea iniciar sesión o cerrar el programa?");
			System.out.println("1-Iniciar Sesión");
			System.out.println("2-Cerrar Sesión");
			
			option = s.nextInt();
			s.nextLine();
			
			while (option<1||option>2) {
				
				System.out.println("Introduzca un número válido");
				
				option = s.nextInt();
				s.nextLine();
			}
			switch(option) {
			
				case 1:
					
					loginPanel(videoclub);
					
				case 2:
					
					break;
			}
			
			
			break;
				
		}
	}

	/**
	 * Todas las opciones del menú del jefe
	 */
	public static void bossOptions(Videostore videoclub, Scanner s, int option,String user, String password) {
		int control;
		String name;
		String director;
		String type;
		int year;
		int valoration;
		int copies;		
		int menu = videoclub.loginUser(user, password);;
		String newUser;
		String newPassword;
		
		
		switch (option) {
		
			case 1:
			
				System.out.println("Crear un nuevo usuario.");
				System.out.println("Introduzca el nombre:");
				
				newUser=s.nextLine();
				
				System.out.println("Introduzca la contraseña");
				
				newPassword = s.nextLine();
				
				videoclub.createUser(newUser, newPassword);
			
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;						
			
			case 2:
				
				System.out.println("Borrar un usuario");
				System.out.println("Introduzca el nombre de usuario a borrar:");
				
				newUser = s.nextLine();
				
				videoclub.deleteUser(newUser);
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
			
			case 3:
				
				videoclub.searchElement();
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
			
			case 4:
				
				
				System.out.println("Añadir una película");
				
				System.out.println("Introduzca el nombre:");
				name = s.nextLine();
				
				System.out.println("Introduzca el dombre del director");
				director = s.nextLine();
				
				System.out.println("Introduzca el tipo de película, ciencia ficción o animación");
				type = s.nextLine();
				
				while(!type.equals("ciencia ficción")&&!type.equals("animación")){
					
					System.out.println("Solo puede ser el tipo ciencia ficción o animación");
					type = s.nextLine();
					
				}
				if(type.equalsIgnoreCase("animacion")) {
					String aux;
					System.out.println("¿Qué tipo de animación? tradicional, por ordenador o stop motion.");
					
					aux = s.nextLine();
					while (!aux.equalsIgnoreCase("tradicional")&&!aux.equalsIgnoreCase("por ordenador")&&!aux.equalsIgnoreCase("stop motion")) {
						aux = s.nextLine();
					}
					type = type + " "+aux;
				}
				
				System.out.println("Introduzca el año de estreno:");
				year = s.nextInt();
				s.nextLine();
				
				System.out.println("Introduzca una valoración, entre 1 y 5:");
				valoration = s.nextInt();
				s.nextLine();
				
				while(valoration<1||valoration>5) {
					System.out.println("La valoración solo puede ser entre 1 y 5");
					valoration = s.nextInt();
					s.nextLine();
				}
				
				System.out.println("Introduzca la cantidad de copias disponibles:");
				copies = s.nextInt();
				s.nextLine();
				
				while(copies<1) {
					System.out.println("La cantidad no puede ser inferior a 1");
					copies = s.nextInt();
					s.nextLine();			
				}
				
				videoclub.addMovie(name, director, type, year, valoration, copies);
			
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
				
			case 5:
				
				int rating;
				int numberOfSeasons;
				int numberOfEpisodes;
				System.out.println("Añadir una serie");
				
				System.out.println("Introduzca el nombre:");
				name = s.nextLine();
				
				System.out.println("Introduzca el número de temporadas:");
				numberOfSeasons = s.nextInt();
				s.nextLine();
				ArrayList<Season> seasons = new ArrayList<>();
				
				for (int i = 0;i<numberOfSeasons;i++) {
			
					Season season = new Season();
					
					season.setName("Temporada "+(i+1));
					
					System.out.println("Introduzca la valoración de la temporada "+(i+1));
					rating = s.nextInt();
					s.nextLine();
					
					while(rating<1||rating>5) {
						System.out.println("La valoración solo puede ser entre 1 y 5");
						rating = s.nextInt();
						s.nextLine();
					}
					season.setValoration(rating);
					
					System.out.println("Introduzca el número de capítulos de la temporada "+(i+1));
					numberOfEpisodes=s.nextInt();
					s.nextLine();
					
					for (int j=0;j<numberOfEpisodes;j++) {
						
						String episodeName;
						
						Episode episode  = new Episode();
						
						System.out.println("Introduzca el nombre del episodio:");
						episodeName = s.nextLine();
						
						episode.setName(episodeName);
						
						System.out.println("Introduzca una valoración al capítulo: "+episodeName);
						valoration =s.nextInt();
						s.nextLine();
						
						while(rating<1||rating>5) {
							System.out.println("La valoración solo puede ser entre 1 y 5");
							rating = s.nextInt();
							s.nextLine();
						}
						
						episode.setValoration(rating);
					
						season.setEpisodes(episode);
					}
					
					seasons.add(season);
				
				
				}
				
				System.out.println("Introduzca el dombre del director");
				director = s.nextLine();
				
				System.out.println("Introduzca el año de estreno:");
				year = s.nextInt();
				s.nextLine();
				
				System.out.println("Introduzca una valoración, entre 1 y 5:");
				valoration = s.nextInt();
				s.nextLine();
				
				while(valoration<1||valoration>5) {
					System.out.println("La valoración solo puede ser entre 1 y 5");
					valoration = s.nextInt();
					s.nextLine();
				}
				
				System.out.println("Introduzca la cantidad de copias disponibles:");
				copies = s.nextInt();
				s.nextLine();
				
				while(copies<1) {
					System.out.println("La cantidad no puede ser inferior a 1");
					copies = s.nextInt();
					s.nextLine();			
				}
				videoclub.addSeries(name, seasons, director, year, valoration, copies);
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;			
				
			case 6:
				
				System.out.println("Alquilar película:");
				System.out.println("Introduzca el nombre de la película a alquilar:");
				
				name = s.nextLine();
				
				videoclub.rentOutMovie(name);
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
				
			case 7:
				
				System.out.println("Alquilar serie:");
				System.out.println("Introduzca el nombre de la serie a alquilar:");
				
				name = s.nextLine();
				
				videoclub.rentOutSeries(name);
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
				
			case 8:
				
				System.out.println("Devolver una película");
				System.out.println("Introduzca el nombre de la película a devolver:");
				
				name = s.nextLine();
				
				videoclub.returnMovie(name);
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
				
			case 9:
				
				System.out.println("Devolver una serie");
				System.out.println("Introduzca el nombre de la serie a devolver:");
				
				name = s.nextLine();
				
				videoclub.returnSeries(name);
				
				
				for ( User x: videoclub.getUsers() ){
					
					if(x.getUser().equals(user)&& x.getPassword().equals(password)) {
						
						control = videoclub.getUsers().indexOf(x);
						if (control==0) {
							videoclub.managerMenu();
							menus(videoclub, s, menu,user,password);
							
						}else {
							videoclub.employeeMenu();
							menus(videoclub, s, menu,user,password);
					
						}
					}
					
				}	
				
				break;
				
			case 10:

				System.out.println("Sesión cerrada:");
				System.out.println("¿Desea iniciar sesión o cerrar el programa?");
				System.out.println("1-Iniciar Sesión");
				System.out.println("2-Cerrar Sesión");
				
				option = s.nextInt();
				s.nextLine();
				
				while (option<1||option>2) {
					
					System.out.println("Introduzca un número válido");
					
					option = s.nextInt();
					s.nextLine();
				}
				switch(option) {
				
					case 1:
						
						loginPanel(videoclub);
						
					case 2:
						
						break;
				}
				
				break;
		
		}
	}
}
