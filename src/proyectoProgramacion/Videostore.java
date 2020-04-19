package proyectoProgramacion;

import java.util.ArrayList;
import java.util.Scanner;

public class Videostore {

	private ArrayList<User> users = new ArrayList<>();

	private ArrayList<Movie> movies = new ArrayList<>();

	private ArrayList<Series> series = new ArrayList<>();

	public ArrayList<User> getUsers() {
		return this.users;
	}

	public int loginUser(String user, String password) {

		int control;

		for (User x : users) {

			if (x.getUser().equals(user) && x.getPassword().equals(password)) {
				System.out.println("Sesión iniciada correctamente");
				control = users.indexOf(x);
				if (control == 0) {
					managerMenu();
					return 1;

				} else {
					employeeMenu();
					return 2;

				}
			}
		}
		return -1;
	}
	/**
	 * Menú del jefe
	 */
	public void managerMenu() {

		System.out.println("Menú:");
		System.out.println("1-Crear usuario");
		System.out.println("2-Borrar usuario");
		System.out.println("3-Buscar");
		System.out.println("4-Añadir película");
		System.out.println("5-Añadir serie");
		System.out.println("6-Alquilar película");
		System.out.println("7-Alquilar serie");
		System.out.println("8-Devolver película");
		System.out.println("9-Devolver serie");
		System.out.println("10-Cerrar sesión");

	}
	/**
	 * Menú del empleado
	 */
	public void employeeMenu() {

		System.out.println("Menú:");
		System.out.println("1-Buscar");
		System.out.println("2-Añadir película");
		System.out.println("3-Añadir serie");
		System.out.println("4-Alquilar película");
		System.out.println("5-Alquilar serie");
		System.out.println("6-Devolver película");
		System.out.println("7-Devolver serie");
		System.out.println("8-Cerrar sesión");

	}
	/**
	 * Método crear usuario
	 */
	public void createUser(String user, String password) {
		User newUser = new User();
		newUser.setUser(user);
		newUser.setPassword(password);

		users.add(newUser);

		System.out.println("Usuario creado con éxito");

	}
	/**
	 * Método borrar usuario
	 */
	public void deleteUser(String user) {

		int control = 0;

		for (User x : users) {

			if (x.getUser().equals(user)) {
				control = 1;
				users.remove(x);
			}

		}

		if (control == 1) {
			System.out.println("Se ha borrado el usuario con éxito");
		} else {
			System.out.println("No se ha podido encontrar al usuario;");
		}
	}
	/**
	 * Método añadir una película
	 */
	public void addMovie(String name, String director, String type, int year, int valoration, int copies) {

		Movie newMovie = new Movie(name, director, type, year, valoration, copies);

		movies.add(newMovie);

	}
	/**
	 * Método añadir una serie
	 */
	public void addSeries(String name, ArrayList<Season> seasons, String director, int year, int valoration,
			int copies) {

		Series newSeries = new Series(name, seasons, director, year, valoration, copies);

		series.add(newSeries);
	}
	/**
	 * Método alquilar una película
	 */
	public void rentOutMovie(String movieName) {

		for (Movie x : movies) {
			if (x.getName().equalsIgnoreCase(movieName) && x.getCopies() > 0) {

				System.out.println("¡Correcto! Se le proporcionará una copia");
				x.setCopies(x.getCopies() - 1);
				System.out.println(x);
			} else {
				System.out.println("No está disponible el título.");
			}
		}

	}
	/**
	 * Método alquilar una serie
	 */
	public void rentOutSeries(String seriesName) {

		for (Series x : series) {
			if (x.getName().equalsIgnoreCase(seriesName) && x.getCopies() > 0) {

				System.out.println("¡Correcto! Se le proporcionará una copia");
				x.setCopies(x.getCopies() - 1);
				System.out.println(x);
			} else {
				System.out.println("No está disponible el título.");
			}
		}

	}
	/**
	 * Método devolver una película
	 */
	public void returnMovie(String movieName) {

		if (movies.isEmpty()) {
			System.out.println("No hay películas.");
		}

		for (Movie x : movies) {
			if (x.getName().equalsIgnoreCase(movieName) && x.getMaxCopies() > x.getCopies()) {
				x.setCopies(x.getCopies() + 1);

			} else {
				System.out.println("No se puede devolver una película que no ha sido alquilada");
			}
		}
	}
	/**
	 * Método devolver una serie
	 */
	public void returnSeries(String seriesName) {

		if (series.isEmpty()) {
			System.out.println("No hay series.");
		}
		for (Series x : series) {
			if (x.getName().equalsIgnoreCase(seriesName) && x.getMaxCopies() > x.getCopies()) {
				x.setCopies(x.getCopies() + 1);

			} else {
				System.out.println("No se puede devolver una serie que no ha sido alquilada");
			}
		}
	}
	/**
	 * Método valorar una película
	 */
	public void ratingMovie(Movie movie) {

		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		int rating;

		System.out.println("Emita una valoración entre 1 y 5 para la película: " + movie.getName());

		rating = s.nextInt();

		while (rating > 5 || rating < 1) {
			rating = s.nextInt();
		}

		rating = (rating + movie.getValoration()) / 2;

	}
	/**
	 * Método valorar una serie
	 */
	public void ratingSeries(Series serie) {

		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		int rating;
		ArrayList<Season> seasons = new ArrayList<>();
		ArrayList<Episode> episodes = new ArrayList<>();

		seasons = serie.getSeasons();

		System.out.println("Emita una valoración entre 1 y 5 para la serie: " + serie.getName());

		rating = s.nextInt();
		s.nextLine();

		while (rating > 5 || rating < 1) {
			System.out.println("Introduzca un número entre 1 y 5");
			rating = s.nextInt();
			s.nextLine();
		}

		rating = (rating + serie.getValoration()) / 2;

		serie.setValoration(rating);

		for (Season x : seasons) {

			System.out.println("Emita una valoración entre 1 y 5 para la temporada: " + x.getName());

			rating = s.nextInt();
			s.nextLine();

			while (rating > 5 || rating < 1) {
				System.out.println("Introduzca un número entre 1 y 5");
				rating = s.nextInt();
				s.nextLine();
			}

			rating = (rating + x.getValoration()) / 2;

			x.setValoration(rating);

			episodes = x.getEpisodes();

			for (Episode y : episodes) {

				System.out.println("Emita una valoración entre 1 y 5 para el capítulo: " + y.getName());

				rating = s.nextInt();
				s.nextLine();

				while (rating > 5 || rating < 1) {
					System.out.println("Introduzca un número entre 1 y 5");
					rating = s.nextInt();
					s.nextLine();
				}

				rating = (rating + y.getValoration()) / 2;

				y.setValoration(rating);

			}
		}

	}
	/**
	 * Método buscar película o serie
	 */
	public void searchElement() {

		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		int menu;
		String name;
		int number;
		System.out.println("---------------------");
		System.out.println("1-Buscar por serie");
		System.out.println("2-Buscar por película");
		System.out.println("---------------------");
		menu = s.nextInt();
		s.nextLine();
		while (menu > 2 || menu < 1) {
			System.out.println("Introduzca un número válido");
			menu = s.nextInt();
			s.nextLine();
		}
		switch (menu) {
		case 1:
			if(series.isEmpty()) {
				System.out.println("No hay series añadidas");
				break;
			}
			System.out.println("¿Por qué atributo desea buscar?");
			System.out.println("1-Nombre");
			System.out.println("2-Director");
			System.out.println("3-Año");
			System.out.println("4-Valoración");

			menu = s.nextInt();
			s.nextLine();

			switch (menu) {
			case 1:
				System.out.println("Introduzca el título a buscar:");
				name = s.nextLine();

				for (Series x : series) {

					if (x.getName().equalsIgnoreCase(name)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;

			case 2:
				System.out.println("Introduzca el nombre del director con sus apellidos a buscar:");
				name = s.nextLine();

				for (Series x : series) {

					if (x.getDirector().equalsIgnoreCase(name)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;
			case 3:
				System.out.println("Introduzca el año de producción:");
				number = s.nextInt();
				s.nextLine();
				for (Series x : series) {

					if (x.getYear() == (number)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;
			case 4:
				System.out.println("Introduzca la valoración deseada:");
				number = s.nextInt();
				s.nextLine();
				for (Series x : series) {

					if (x.getValoration() == (number)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;

			}

			break;

		case 2:
			if(movies.isEmpty()) {
				System.out.println("No hay películas añadidas");
				break;
			}
			System.out.println("¿Por qué atributo desea buscar?");
			System.out.println("1-Nombre");
			System.out.println("2-Director");
			System.out.println("3-Año");
			System.out.println("4-Valoración");

			menu = s.nextInt();
			s.nextLine();

			switch (menu) {
			case 1:
				System.out.println("Introduzca el título a buscar:");
				name = s.nextLine();

				for (Movie x : movies) {

					if (x.getName().equalsIgnoreCase(name)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;

			case 2:
				System.out.println("Introduzca el nombre del director con sus apellidos a buscar:");
				name = s.nextLine();

				for (Movie x : movies) {

					if (x.getDirector().equalsIgnoreCase(name)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;
			case 3:
				System.out.println("Introduzca el año de producción:");
				number = s.nextInt();
				s.nextLine();
				for (Movie x : movies) {

					if (x.getYear() == (number)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;
			case 4:
				System.out.println("Introduzca la valoración deseada:");
				number = s.nextInt();
				s.nextLine();
				for (Movie x : movies) {

					if (x.getValoration() == (number)) {
						System.out.println("Se ha encontrado el título:");
						System.out.println(x);
					} else {
						System.out.println("Ese título no está disponible");
					}

				}
				break;

			}
			break;
		}

	}

}
