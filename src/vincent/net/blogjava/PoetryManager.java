package vincent.net.blogjava;
import java.util.ArrayList;
import java.util.List;


public class PoetryManager {
		public static Poetry getPoetry(int id){
			Poetry poetry = null;
		   for(Poetry p:	Poetry.values()){
			   if(p.getId()==id){
				   poetry = p;
				   break; 
				   
			   }
			  
		   }
			return poetry;
		}
		public static List<String> getPoetrysName(){
			List<String> nameOfList = new ArrayList<String>();
			for(Poetry p:	Poetry.values()){
				nameOfList.add(p.getName());
			   }
			return nameOfList;
			
		}

}
