class RecycledLoader extends Loader{
	int delayedTime = 60;
	public RecycledLoader(int num){
		super(num);
	}

	public boolean CalculateTimeCruise(Cruise cruise){
		if (this.totalCruises.size() == 0){
			this.LoadCruiseCruise(cruise);
			return true;	
		}
		int currHour =  Integer.parseInt(this.totalCruises.get(totalCruises.size()-1).getSchedule().substring(0,2));
		int CruiseHour = Integer.parseInt(cruise.getSchedule().substring(0,2));
		int currMinute = Integer.parseInt(this.totalCruises.get(totalCruises.size()-1).getSchedule().substring(2,4));
		int CruiseMinute = Integer.parseInt(cruise.getSchedule().substring(2,4));
		int DelayedMinute = currMinute;
		int DelayedHour = currHour + 1;
		if (this.totalCruises.get(totalCruises.size()-1) instanceof BigCruise){
			if (CruiseHour - DelayedHour == 1 && CruiseMinute - DelayedMinute == 0){
				return true;
			}
			else if (CruiseHour - DelayedHour > 1){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(CruiseHour == DelayedHour && (CruiseMinute - DelayedMinute) >= 30){
				return true;
			}
			else if (CruiseHour - DelayedHour == 1 && CruiseMinute + 60 - DelayedMinute >= 30){
				return true;
			}
			else if (CruiseHour - DelayedHour > 1){
				return true;
			}
			else{
				return false;
			}
		}
	}

	public boolean CalculateTimeBigCruise(BigCruise bigcruise){
	if (this.totalCruises.size() == 0){
		this.LoadCruiseBigCruise(bigcruise);
		return true;
	}
	int currHour =  Integer.parseInt(this.totalCruises.get(totalCruises.size()-1).getSchedule().substring(0,2));
	int CruiseHour = Integer.parseInt(bigcruise.getSchedule().substring(0,2));
	int currMinute = Integer.parseInt(this.totalCruises.get(totalCruises.size()-1).getSchedule().substring(2,4));
	int CruiseMinute = Integer.parseInt(bigcruise.getSchedule().substring(2,4));
	int DelayedMinute = currMinute;
	int DelayedHour = currHour + 1;
	if (this.totalCruises.get(totalCruises.size()-1) instanceof BigCruise){
			if (CruiseHour - DelayedHour == 1 && CruiseMinute - DelayedMinute >= 0){
				return true;
			}
			else if (CruiseHour - DelayedHour > 1){
				return true;
			}
			else{
				return false;
			}
	}
	else{
		if(CruiseHour == DelayedHour && (CruiseMinute - DelayedMinute) >= 30){
				return true;
			}
			else if (CruiseHour - DelayedHour == 1 && CruiseMinute + 60 - DelayedMinute >= 30){
				return true;
			}
			else if (CruiseHour - DelayedHour > 1){
				return true;
			}
			else{
				return false;
			}
		}
	}
}