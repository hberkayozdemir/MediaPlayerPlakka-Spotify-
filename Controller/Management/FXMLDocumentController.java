/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Management;

import java.awt.event.InputEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.adaptor_pattern.AdvancedMediaPlayer;
import Controller.adaptor_pattern.AudioPlayer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

//    public FXMLDocumentController(String fileName) {
//        this.filePath=fileName;
//    }


    private InputEvent ii;

    public ImageView ab;
    @FXML

    private MediaPlayer mediaPlayer=null;
    private String filePath;



    @FXML
    private MediaView mediaView;
    @FXML
    private Duration duration;
    @FXML
    private Slider slider;
    @FXML
    private Slider seeSlider;
    @FXML
    private static final double MIN_CHANGE = 0.5 ;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        AudioPlayer audioPlayer=new AudioPlayer();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File ","*.mp4","*.mp3");
        
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filePath = file.toURI().toString();

            if(filePath != null){

                StringBuilder sb = new StringBuilder();

                char[] chs = filePath.toCharArray();

                sb.append(chs[chs.length-3]);
                sb.append(chs[chs.length-2]);
                sb.append(chs[chs.length-1]);

                String uzanti = sb.toString();

                char[] chs2 = new char[chs.length-6];

                for (int i = 6; i < chs.length; i++){
                    chs2[i-6] = chs[i];
                }

                StringBuilder sb2 = new StringBuilder();
                sb2.append(chs2);

                System.out.println(sb2.toString());


                System.out.println(uzanti);

                if(mediaPlayer!=null)
                {
                    mediaPlayer.stop();
                }

                Media media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                    DoubleProperty width = mediaView.fitWidthProperty();
                    DoubleProperty height = mediaView.fitHeightProperty();
                    width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                    height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
                    
                    slider.setValue(mediaPlayer.getVolume() * 100);
                    
                    slider.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        mediaPlayer.setVolume(slider.getValue()/100);
                    }
                });
                mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) -> seeSlider.setMax(newDuration.toSeconds()));
                seeSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                 if(!isChanging)   {
                     mediaPlayer.seek(Duration.seconds(seeSlider.getValue()));
                    }
                });
                seeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (! seeSlider.isValueChanging()) {
                    double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                    if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
                            mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                        }
                    }
                });
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (! seeSlider.isValueChanging()) {
                        seeSlider.setValue(newTime.toSeconds());
                    }
                });
                
                mediaPlayer.play();
            }
            else{

// baha duzeltme yapcak

            }
    }
    @FXML
    private void pauseVideo(ActionEvent event ){
        mediaPlayer.pause();
    }
    @FXML
    private void playVideo(ActionEvent event ){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    @FXML
    private void stopVideo(ActionEvent event ){
        mediaPlayer.stop();
    }
    @FXML
    private void fastVideo(ActionEvent event ){
        mediaPlayer.setRate(1.5);
    }
    @FXML
    private void fasterVideo(ActionEvent event ){
        mediaPlayer.setRate(2);
    }
    @FXML
    private void slowVideo(ActionEvent event ){
        mediaPlayer.setRate(.75);
    }
    @FXML
    private void slowerVideo(ActionEvent event ){
        mediaPlayer.setRate(.5);
    }
    @FXML
    private void exitVideo(ActionEvent event ){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
