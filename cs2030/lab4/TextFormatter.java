interface TextFormatter{
	// Create a clone of the TextFormatter with another String
	public TextFormatter clone(String s);
	
	// Return a formatted string
	public String format();

	// Calculate the ASCII sum
	public int getASCII();	
}
