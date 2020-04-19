package proyectoProgramacion;

import java.util.ArrayList;

public class Season {

	private String name;
	private int valoration;
	private ArrayList<Episode> episodes = new ArrayList<>();
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValoration() {
		return valoration;
	}
	public void setValoration(int valoration) {
		this.valoration = valoration;
	}
	public ArrayList<Episode> getEpisodes() {
		return episodes;
	}
	public void setEpisodes(Episode episode) {
		this.episodes.add(episode);
	}

	public int getNumberOfEpisodes() {
		
		return episodes.size();
	}
	
	
}
