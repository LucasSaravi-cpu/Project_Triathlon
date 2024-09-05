package model.athlete.penalties;

import java.io.Serializable;
import java.util.Date;

public class AthletePenalty implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	    private Date dateHour;
	    private double distance;
	    private Penalty penalty;
	    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	    
		public AthletePenalty(Date dateHour, double distance, Penalty penalty) {
			super();
			this.dateHour = dateHour;
			this.distance = distance;
			this.penalty = penalty;
		}
		
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
		public Date getDateHour() {
			return dateHour;
		}
		public void setDateHour(Date dateHour) {
			this.dateHour = dateHour;
		}
		public double getDistance() {
			return distance;
		}
		public void setDistance(double distance) {
			this.distance = distance;
		}
		public Penalty getPenalty() {
			return penalty;
		}
		public void setPenalty(Penalty penalty) {
			this.penalty = penalty;
		}
		    
}
