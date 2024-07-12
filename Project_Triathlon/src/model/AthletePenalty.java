package model;

import java.util.Date;

public class AthletePenalty {
	    private Date dateHour;
	    private double distance;
	    private Penalty penalty;
		public AthletePenalty(Date dateHour, double distance, Penalty penalty) {
			super();
			this.dateHour = dateHour;
			this.distance = distance;
			this.penalty = penalty;
		}
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
