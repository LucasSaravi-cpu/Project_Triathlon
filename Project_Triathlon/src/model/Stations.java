package model;

public class Stations {
	
	private String type;
	private int id;
	private double distancing;
	
	public Stations(String type, int id, double distancing) {
		super();
		this.type = type;
		this.id = id;
		this.distancing = distancing;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDistancing() {
		return distancing;
	}

	public void setDistancing(double distancing) {
		this.distancing = distancing;
	}

	@Override
	public String toString() {
		return "\n Type: " + type + "\n ID: " + id + "\n Distancing: " + distancing + "\n";
	}
	
	
	

}
