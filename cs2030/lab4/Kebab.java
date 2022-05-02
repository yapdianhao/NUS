public class Kebab implements TextFormatter, Comparable<TextFormatter>{
	String s;

	public Kebab(String s){
		this.s = s;
	}
	
	@Override
	public int getASCII(){
		int ASCII = 0;
		String s = this.format();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			int num = (int) c;
			ASCII += num;
		}
		return ASCII;
	}
	
	@Override
	public TextFormatter clone(String s){
		return new Kebab(s);
	}
	
	@Override
	public String format(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (Character.toString(c).equals(" ")){
				sb.append("-");
			}
			else {
				sb.append(Character.toString(c));
			}
		}
		return sb.toString().toLowerCase();
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
