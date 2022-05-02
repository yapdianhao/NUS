class BigCruise extends Cruise{
	int loadingTime;

	public BigCruise(String CruiseCode, int CruiseSchedule){
		super(CruiseCode, CruiseSchedule);  
		this.loadingTime = 60;
	}
}
