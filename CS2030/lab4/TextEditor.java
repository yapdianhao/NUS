import java.util.List;
//import java.util.ArrayList;
import java.util.PriorityQueue;
public class TextEditor{
	List<TextFormatter> formatter;// = new ArrayList<TextFormatter>();
	PriorityQueue<TextFormatter> orderedformatter = new PriorityQueue<TextFormatter>();
	//ArrayList<String> strings = new ArrayList<String>();

	public TextEditor(List<TextFormatter> formatter){
		this.formatter = formatter;
	}
	
	/*public void addString(String s){
		strings.add(s);
	}*/

	public void printall(){
		while (!orderedformatter.isEmpty()){
			System.out.println(orderedformatter.remove().format());//poll()
		}
	}
}
