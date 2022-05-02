import java.awt.Color; 

class FilledCircle extends Circle{
	//private double radius; 
	private Color color; 
	
	public FilledCircle(){
		
	}
	
	public FilledCircle(Color color){
		this.color = color; 
	}
	
	
	//@Override
	public String toString(){
		return " The circle with radius " + super.radius + " has an area of " + super.getArea() + " color is " + this.color; 
	}
	
	//@Override
	//public void draw(){
	//	System.out.println("Draw from FilledCircle");
	//}
	
}

