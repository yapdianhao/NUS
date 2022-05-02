import java.util.ArrayList;
class Loader{
	ArrayList<Cruise> totalCruises = new ArrayList<Cruise>();//this is the list of cruises each loeader carries
	int num;	
	public Loader(int num){
		this.totalCruises = totalCruises;//initially an empty list
		this.num = num;
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
		if (this.totalCruises.get(totalCruises.size()-1) instanceof BigCruise){
			if (CruiseHour - currHour == 1 && CruiseMinute - currMinute == 0){
				return true;
			}
			else if (CruiseHour - currHour > 1){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(CruiseHour == currHour && (CruiseMinute - currMinute) >= 30){
				return true;
			}
			else if (CruiseHour - currHour == 1 && CruiseMinute + 60 - currMinute >= 30){
				return true;
			}
			else if (CruiseHour - currHour > 1){
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
	if (this.totalCruises.get(totalCruises.size()-1) instanceof BigCruise){
			if (CruiseHour - currHour == 1 && CruiseMinute - currMinute >= 0){
				return true;
			}
			else if (CruiseHour - currHour > 1){
				return true;
			}
			else{
				return false;
			}
	}
	else{
		if(CruiseHour == currHour && (CruiseMinute - currMinute) >= 30){
				return true;
			}
			else if (CruiseHour - currHour == 1 && CruiseMinute + 60 - currMinute >= 30){
				return true;
			}
			else if (CruiseHour - currHour > 1){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public void LoadCruiseCruise(Cruise cruise){
		this.totalCruises.add(cruise);
	}

	public void LoadCruiseBigCruise(BigCruise bigcruise){
		this.totalCruises.add(bigcruise);
	}
}

