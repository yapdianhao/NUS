import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
class Main{
	public static TextEditor readStrings(){
		//ArrayList<String> strings = new ArrayList<String>();
		List<TextFormatter> formatter = new ArrayList<TextFormatter>();
		Snake snake = new Snake("dummy");
		Pascal pascal = new Pascal("dummy");
		Kebab kebab = new Kebab("dummy");
		MixedFormat mixedformat = new MixedFormat("dummy");
		formatter.add(snake);
		formatter.add(kebab);
		formatter.add(pascal);
		formatter.add(mixedformat);
		TextEditor texteditor = new TextEditor(formatter);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			String s = scanner.nextLine();
			texteditor.formatter.add(texteditor.formatter.get(0).clone(s));
			texteditor.formatter.add(texteditor.formatter.get(1).clone(s));
			texteditor.formatter.add(texteditor.formatter.get(2).clone(s));
			texteditor.formatter.add(texteditor.formatter.get(3).clone(s));
			//texteditor.addString(s);
			//Snake snake = new Snake(s);
			//texteditor.formatter.add(snake);
			//texteditor.orderedformatter.add(snake);
			//Kebab kebab = new Kebab(s);
			//texteditor.formatter.add(kebab);
			//texteditor.orderedformatter.add(kebab);
			//Pascal pascal = new Pascal(s);
			//texteditor.formatter.add(pascal);
			//texteditor.orderedformatter.add(pascal);
			//MixedFormat mixedformat = new MixedFormat(s);
			//texteditor.formatter.add(mixedformat);
		}

		/*for (TextFormatter textformatter : texteditor.formatter){
				texteditor.orderedformatter.add(textformatter);
		}
		return texteditor;*/
		for(int i = 4; i < texteditor.formatter.size(); i++){
			TextFormatter textformatter = texteditor.formatter.get(i);
			texteditor.orderedformatter.add(textformatter);
		}
		return texteditor;
	}	

	public static void main(String[] args){
		TextEditor texteditor = readStrings();
		//System.out.println(texteditor.formatter.size());
		texteditor.printall();
		/*for(TextFormatter textformatter : texteditor.orderedformatter){
			System.out.println(textformatter.format());
		}*/
	}
}
