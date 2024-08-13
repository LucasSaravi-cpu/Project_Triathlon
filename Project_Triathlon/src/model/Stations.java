package model;

public class Stations {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private String type;
	private int id;
	private double distancing;
	
	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public Stations(String type, int id, double distancing) {
		super();
		this.type = type;
		this.id = id;
		this.distancing = distancing;
	}

	  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
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
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tType: ").append(type).append("\n\tID: ").append(id).append("\n\tDistancing: ").append(distancing).append("\n");
		return sb.toString();
	}
	
	
	

}
