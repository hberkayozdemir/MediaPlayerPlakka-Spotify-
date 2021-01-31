package Controller.adaptor_pattern;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import Model.chain.AbstractLogger;
import Model.chain.ConsoleLogger;
import Model.chain.ErrorLogger;
import Model.chain.FileLogger;
import javazoom.jl.player.Player;
import com.mainiac.*;

public class MP3Player implements AdvancedMediaPlayer {
	FileInputStream FIS;
	BufferedInputStream BIS;
	
	public long pauseLocation;
	public long songTotalLength;
	Player player;

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
		try {
			AbstractLogger loggerChain = getChainOfLoggers();
			loggerChain.logMessage(AbstractLogger.INFO,"Mp3 Player is playing " + fileName + " song ");
			new MusicPlayer(fileName);
		} catch (Exception e1) {e1.printStackTrace();}
	}
	

	@Override
	public void playMp4(String fileName) {/*TODO nothing...*/}
}
