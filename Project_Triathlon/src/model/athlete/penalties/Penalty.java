package model.athlete.penalties;


import java.io.Serializable;

public class Penalty implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private String description;
    private boolean disqualification;
	private int time;
    
    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
    public Penalty(String description, boolean disqualification, int time) {
		super();
		this.description = description;
		this.disqualification = disqualification;
		this.time = time;
	}
    
    //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    public String getDescription() {
    	return description;
    }
    public boolean isDesqualified() {
	   return disqualification;
   }
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDisqualification(boolean disqualification) {
		this.disqualification = disqualification;
	}
	public int getTime(){
		return time;
	}
	
}
