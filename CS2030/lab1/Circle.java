class Circle extends Shape{
	protected double radius; 
	
	public Circle(){
		this.radius = 0.0;
	}
	
	
	public Circle(double radius){
		this.radius = radius; 
	}
	
	public double getArea(){
		//System.out.println("GetArea called from Circle");
		return this.radius * this.radius * Math.PI; 
	}
	
	//@Override
	public String toString(){
		return super.toString() + " The circle with radius " + this.radius + " has an area of " + getArea(); 
	}
	
	@Override
	public void draw(){
		System.out.println("Draw from Circle");
	}
	
}

