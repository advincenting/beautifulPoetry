package vincent.net.blogjava;

import android.annotation.SuppressLint;

public enum Poetry {
	 YOUTH(1,"Youth 青春","youth.mp3","youth.txt"),
	ThreeDaysToSee(2,"Three Days to See(Excerpts)假如给我三天光明","ThreeDaysToSee.mp3","ThreeDaysToSee.txt");

    @SuppressLint("ParserError")
	private Poetry(int id, String name,String nameOfMP3, String nameOfText){
		this.id = id;
		this.name= name;
		this.nameOfMP3= nameOfMP3;
		this.nameOfText= nameOfText;
	}
    private String name;	
    private String nameOfMP3;
	private String nameOfText;
	private  int id;
	
	public String toString(){
			return name;
		
	}
	public String getName(){
		return name;
	
}
	
	public String getNameOfMP3(){
		return nameOfMP3;
	
}
	
	public String getNameOfText(){
		return nameOfText;
	
}
	public int getId(){
		return id;
		
	}

}