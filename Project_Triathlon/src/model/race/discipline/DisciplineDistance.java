package model.race.discipline;

public class DisciplineDistance {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private double distance;
	private String time;
	private Discipline discipline;
	
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public DisciplineDistance(double distance, String time, Discipline discipline) {
		super();
		this.distance = distance;
		this.time = time;
		this.discipline = discipline;
		
	}
	
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DisciplineDistance that = (DisciplineDistance) obj;

        return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
        return time != null ? time.hashCode() : 0;
    }
	

}


