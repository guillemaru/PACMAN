package Projet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;


public class DefaultStartTask extends Task<String>{
	StringProperty textprop;
	
	public DefaultStartTask() {
	     this.textprop = new SimpleStringProperty("The game will start.");
	}
	  @Override
	  protected String call() throws Exception {
	      for(int i = 3; i>=1; i--) {
	    	  textprop.setValue("The game will start in: "+i);
	    	  Thread.sleep(1000);
	      }
	      textprop.setValue("The game has started, good luck!");
	      
	      return null;
	  }
}
