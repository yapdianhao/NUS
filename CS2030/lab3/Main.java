import java.util.ArrayList;
import java.util.Scanner;
class Main{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Cruise[] readCruises(){
		Scanner scanner;
		Cruise[] cruises;
		scanner = new Scanner(System.in);
		cruises = new Cruise[scanner.nextInt()];
		for (int i = 0; i < cruises.length; i++){
			Cruise cruise = new Cruise(scanner.next(), scanner.nextInt());
			cruises[i] = cruise;
		}
		return cruises;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Cruise> setCruises(Cruise[] cruises){
		ArrayList<Cruise> Cruises = new ArrayList<Cruise>();
		for (Cruise cruise : cruises){
			if (cruise.isBigCruise()){
				BigCruise bigcruise1 = new BigCruise(cruise.CruiseCode, cruise.CruiseSchedule);
				Cruises.add(bigcruise1);
				Cruises.add(bigcruise1);
			}
			else{
				Cruise anothercruise = new Cruise(cruise.CruiseCode, cruise.CruiseSchedule);
				Cruises.add(cruise);
			}
		}
		return Cruises;
	}

	public static ArrayList<Loader> setLoaders(ArrayList<Cruise> Cruises){
		 ArrayList<Loader> Loaders = new ArrayList<Loader>();
		 Loader loader1 = new Loader(1);// loader array cannot be empty so add a loader
		 Loaders.add(loader1);
		 boolean inserted = false;
		 int i = 1;
		 for (Cruise cruise: Cruises){//loop through every cruise to place in loader
				for (Loader loader : Loaders){
					if (cruise instanceof BigCruise){
						BigCruise FuckingBigCruise = new BigCruise(cruise.CruiseCode,cruise.CruiseSchedule);
						if (loader.CalculateTimeBigCruise(FuckingBigCruise) && loader.totalCruises.contains(FuckingBigCruise)){
							//loader.LoadCruise(cruise);
							break;
							}
						else if (loader.CalculateTimeBigCruise(FuckingBigCruise)){
							loader.LoadCruiseBigCruise(FuckingBigCruise);
							break;
							}
						else if (loader.num == Loaders.size() && loader.CalculateTimeBigCruise(FuckingBigCruise) == false){
								inserted = true;
							}
					}
					else {
						if (loader.CalculateTimeCruise(cruise) && loader.totalCruises.contains(cruise)){
							//loader.LoadCruise(cruise);
							break;
							}
						else if (loader.CalculateTimeCruise(cruise)){
							loader.LoadCruiseCruise(cruise);
							break;
							}
						else if (loader.num == Loaders.size() && loader.CalculateTimeCruise(cruise) == false){
								inserted = true;
							}
						}
					}

					if (inserted == true){
						i++;
						if(i % 3 != 0 ){
							Loaders.add(new Loader(i));
						}
						else{
							Loaders.add(new RecycledLoader(i));
						}
						if (cruise instanceof BigCruise){
							BigCruise FuckingBigCruise = new BigCruise(cruise.CruiseCode, cruise.CruiseSchedule);
							Loaders.get(Loaders.size()-1).LoadCruiseBigCruise(FuckingBigCruise);
						}
						else {
							Loaders.get(Loaders.size()-1).LoadCruiseCruise(cruise);
						}
						inserted = false;
					}
			}
		return Loaders;
	}
	
	public static ArrayList<Loader> getRecycledLoaders(ArrayList<Loader> Loaders){
		ArrayList<Loader> RecycledLoaders = new ArrayList<Loader>();
		for (Loader loader : Loaders){
			if(loader instanceof RecycledLoader){
				RecycledLoaders.add(loader);
			}
		}
		return RecycledLoaders;
	}

	public static ArrayList<Loader> getNormalLoaders(ArrayList<Loader> Loaders, ArrayList<Loader> RecycledLoaders){
		ArrayList<Loader> NormalLoaders = new ArrayList<Loader>();
		for (Loader loader : Loaders){
			if (RecycledLoaders.contains(loader)){
				continue;
			}
			else{
				NormalLoaders.add(loader);
			}
		}
		return NormalLoaders;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int countBigCruises(Cruise[] cruises){
		int i = 0;
		for (Cruise cruise : cruises){
			if (cruise.isBigCruise()){
				i++;
			}
		}
		return i;
	}

	public static int countNormalCruises(Cruise[] cruises){
		return cruises.length - countBigCruises(cruises);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String border(String s, int n){
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		for (int i = 0; i < n; i++){
			sb.append(s);
		}
		sb.append("+");
		return sb.toString();
	}

	public static String CruiseStatistics(){
		StringBuilder cst = new StringBuilder();
		cst.append("| Cruise Statistics");
		for(int i = 0; i < 16; i++){
			cst.append(" ");
		}
		cst.append("|");
		return cst.toString();
	}

	public static String EquipmentStatistics(){
		StringBuilder eqp = new StringBuilder();
		eqp.append("| Equipment statistics");
		for(int i = 0; i < 13; i++){
			eqp.append(" ");
		}
		eqp.append("|");
		return eqp.toString();
	}

	public static String Numberof(String s){
		StringBuilder numof = new StringBuilder();
		numof.append("| Number of ");
		numof.append(s);
		for (int i = 0; i < 35 - 7 - 12 - s.length(); i++){
			numof.append(" ");
		}
		numof.append(" = ");
		return numof.toString();
	}

	public static String LoaderLine(Loader loader){
		StringBuilder lln = new StringBuilder();
		String s = String.format("| Loader %d serves:", loader.num);
		lln.append(s);
		for (int i = 0; i < 36 - s.length() - 1; i++){
			lln.append(" ");
		}
		lln.append("|");
		return lln.toString();
	}

	public static String CruiseLine(Cruise cruise){
		StringBuilder cln = new StringBuilder();
		String s = String.format("|     " + cruise.toString());
		cln.append(s);
		for (int i = 0; i <  36 - 1 - s.length(); i++){
			cln.append(" ");
		}
		cln.append("|");
		return cln.toString();
	}

	public static String RecycledLoaderLine(Loader recycledloader){
		StringBuilder rln = new StringBuilder();
		String s = String.format("| Loader %d (recycled) serves:", recycledloader.num);
		rln.append(s);
		for (int i = 0; i < 36 - 1 - s.length(); i++){
			rln.append(" ");
		}
		rln.append("|");
		return rln.toString();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		Cruise[] cruises = readCruises();
		ArrayList<Cruise> Cruises = setCruises(cruises);
		ArrayList<Loader> Loaders = setLoaders(Cruises);
		ArrayList<Loader> RecycledLoaders = getRecycledLoaders(Loaders);
		ArrayList<Loader> NormalLoaders = getNormalLoaders(Loaders,RecycledLoaders);
		System.out.println(border("=", 34));//thick border
		System.out.println(CruiseStatistics());//cruise stat
		System.out.println(border("-", 34));//thin border
		System.out.println(String.format(Numberof("normal cruises") + "%3d" + " |",countNormalCruises(cruises)));//num normal cruise
		System.out.println(String.format(Numberof("big cruises") + "%3d" + " |", countBigCruises(cruises)));//num big cruises
		System.out.println(border("=", 34));//thick border
		System.out.println(EquipmentStatistics());//equip stat
		System.out.println(border("-", 34));//thin border
		System.out.println(String.format(Numberof("loaders") + "%3d" + " |", NormalLoaders.size()));// num normal loaders
		System.out.println(String.format(Numberof("recycled loaders") + "%3d" + " |", RecycledLoaders.size()));//num recycled size
		System.out.println(border("=", 34));//thick border
		for(Loader loader : NormalLoaders){
			System.out.println(LoaderLine(loader));
			for (Cruise cruise : loader.totalCruises){
				System.out.println(CruiseLine(cruise));
			}
			System.out.println(border("=", 34));//thick border between loaders
		}
		for (Loader recycledloader : RecycledLoaders){
			System.out.println(RecycledLoaderLine(recycledloader));
			for (Cruise cruise: recycledloader.totalCruises){
				System.out.println(CruiseLine(cruise));
			}
			System.out.println(border("=", 34));
		}
	}
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




