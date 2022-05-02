public class MixedFormat implements TextFormatter, Comparable<TextFormatter>{
	String s;
	
	public MixedFormat(String s){
		this.s = s;
	}

	@Override
	public int getASCII(){
		int ASCII = 0;
		String s = format();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			int num = (int) c;
			ASCII += num;
		}
		return ASCII;
	}

	@Override
	public TextFormatter clone(String s){
		return new MixedFormat(s);
	}

	public int CalculateASCII(String s){
		int res = 0;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			int num = (int) c;
			res += num;
		}
		return res;
	}

	@Override 
	public String format(){
		char c = this.s.charAt(0);
		int num = (int) c;
		int r = num % 3;
		String t = new Snake("I Love CS2030").format();
		String Snake = SnakeFormat();
		String Pascal = PascalFormat();
		String Kebab = KebabFormat();
		if(r == 0){
			if(CalculateASCII(Snake) > CalculateASCII(t)){
				return Snake;
			}
			else{
				return t;
			}
		}
		else if(r == 2){
			if(CalculateASCII(Pascal) > CalculateASCII(t)){
				return Pascal;
			}
			else{
				return t;
			}
		}
		else{
			if(CalculateASCII(Kebab) > CalculateASCII(t)){
				return Kebab;
			}
			else{
				return t;
			}
		}	
	}

	public String SnakeFormat(){
		Snake snake = new Snake(this.s);
		return snake.format();
	}

	public String PascalFormat(){
		Pascal pascal = new Pascal(this.s);
		return pascal.format();
	}
	
	public String KebabFormat(){
		Kebab kebab = new Kebab(this.s);
		return kebab.format();
	}

	@Override
	public int compareTo(TextFormatter other){
		if (this.getASCII() > other.getASCII()){
			return 1;
		}
		else if (this.getASCII() < other.getASCII()){
			return -1;
		}
		else{
			return 0;
		}
	}

}
