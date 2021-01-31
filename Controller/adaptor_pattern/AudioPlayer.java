package Controller.adaptor_pattern;

public class AudioPlayer implements MediaPlayer{
	MediaAdapter mediaAdapter; 

	   @Override
	   public void play(String audioType, String fileName) {
		   
	      if(audioType.equalsIgnoreCase("mp3")){
	    	  System.out.println("DEBUG::" + fileName + "is playing");
	    	  mediaAdapter = new MediaAdapter(audioType);
		      mediaAdapter.play(audioType, fileName);
	      } 
	      
	      
	      else if(audioType.equalsIgnoreCase("mp4")){
	         mediaAdapter = new MediaAdapter(audioType);
	         mediaAdapter.play(audioType, fileName);
	      }
	      
	      else{
	         System.out.println("Invalid media. " + audioType + " format not supported");
	      }
	   }
}
