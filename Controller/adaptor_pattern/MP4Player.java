package Controller.adaptor_pattern;


import Model.chain.AbstractLogger;
import Model.chain.ConsoleLogger;
import Model.chain.ErrorLogger;
import Model.chain.FileLogger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import  Controller.PrototypePattern.Gui;
import Controller.Management.FXMLDocumentController;

public class MP4Player implements AdvancedMediaPlayer {
private Gui path=new Gui();
//FXMLDocumentController fxmdp;
	private static AbstractLogger getChainOfLoggers(){

		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		return errorLogger;
	}

	@Override
	public void playMp3(String fileName) {
		//-- this function of this class of java does nothing... --//
		}

	@Override
	public void playMp4(String fileName) {
		AbstractLogger loggerChain = getChainOfLoggers();


		try {
			loggerChain.logMessage(AbstractLogger.INFO,"Mp4 Player is playing " + fileName + " song ");
			//fxmdp=new FXMLDocumentController(fileName);
			loggerChain.logMessage(AbstractLogger.ERROR,"Basarılı");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(path.getAbsolutepath()+"Mp4Request.fxml"));
			Stage stage = new Stage();
			Scene root = new Scene(fxmlLoader.load(), 700, 500);
			stage.setTitle("Plakka");
			stage.setScene(root);
			stage.show();

		}catch (Exception e){
			e.printStackTrace();
			loggerChain.logMessage(AbstractLogger.ERROR, "ERROR:: During the runtime MP4 folder cannot be opened could it be break down. ERROR NO 107");
		}

	}
}