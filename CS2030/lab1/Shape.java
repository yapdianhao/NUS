class Shape{
	private String name; 
	
	public Shape(){
		this.name = "Shape";
	}
	
	
	@Override
	public String toString(){
		return "This is a " + name; 
	}
	
	public void draw(){
		System.out.println("Draw from Shape");
	}
	
}

