package model;


public class Penalty {
    private String description;
    private boolean disqualification;
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
	public Penalty(String description, boolean disqualification) {
		super();
		this.description = description;
		this.disqualification = disqualification;
	}
}
