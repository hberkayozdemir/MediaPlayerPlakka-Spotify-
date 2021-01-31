package Controller.adaptor_pattern;

public class MediaAdapter implements MediaPlayer {

   AdvancedMediaPlayer advancedMusicPlayer;

   public MediaAdapter(String audioType){
   
      if(audioType.equalsIgnoreCase("mp3") ){
         advancedMusicPlayer = new MP3Player();
         
      }else if (audioType.equalsIgnoreCase("mp4")){
         advancedMusicPlayer = new MP4Player();
      }	
   }

   @Override
   public void play(String audioType, String fileName) {
   
      if(audioType.equalsIgnoreCase("mp3")){
         advancedMusicPlayer.playMp3(fileName);
      }
      else if(audioType.equalsIgnoreCase("mp4")){
         advancedMusicPlayer.playMp4(fileName);
      }
   }
}