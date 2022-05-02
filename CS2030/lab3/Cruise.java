class Cruise{
	String CruiseCode;
	int CruiseSchedule;
	int loadingTime;
	
	public Cruise(String CruiseCode, int CruiseSchedule){
		this.CruiseCode = CruiseCode;
		this.CruiseSchedule = CruiseSchedule;
		this.loadingTime = 30;
	}

	@Override
	public String toString(){
		return String.format("%s@%04d",this.CruiseCode, this.CruiseSchedule);
	}
	
	public String getSchedule(){
		return String.format("%04d",this.CruiseSchedule);
	}

	/*public int getHour(){
		return Integer.parseInt(this.getSchedule().substring(0,2));
	}

	public int getMinutes(){
		return Integer.parseInt(this.getSchedule().substring(2,4));
	}*/

	public boolean isBigCruise(){
		return this.CruiseCode.substring(0,1).equals("B");
	}
} 
