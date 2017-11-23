package TestSnap;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;



public class Controller {

	
	  @FXML
	    private Button btnStart;

	    @FXML
	    private Button btnStop;

	    @FXML
	    private Button btnSnap;

	    @FXML
	    private ImageView SnapImage;

	    
	    private DaemonThread myThread = null;
	    int count = 0;
	    VideoCapture webSource = null;

	    Mat frame = new Mat();
	    MatOfByte mem = new MatOfByte();
	    
	    
	    
	    
	    @FXML
	    void btnSnapAction(ActionEvent event) 
	    {
	    	int var=(int)((Math.random()+10000)*100);
	    	
	        Highgui.imwrite("bin/photo/tharhtet"+var+".jpg", frame);
	    }

	    @FXML
	    void btnStartAction(ActionEvent event) {
	    	
	    	webSource =new VideoCapture(0);
	    	  myThread = new DaemonThread();
	    	            Thread t = new Thread(myThread);
	    	            t.setDaemon(true);
	    	            myThread.runnable = true;
	    	            t.start();
	    	          
	    	            
	    				 btnStart.setDisable(true);;  //start button
	    	            btnStop.setDisable(false);  // stop button
	    	            
	    }

	    @FXML
	    void btnStopAction(ActionEvent event) {
	    	myThread.runnable = false;
	    	
	        btnStop.setDisable(true);   
            btnStart.setDisable(false);
            
            webSource.release();			

	    }

	    
	    
	    class DaemonThread implements Runnable
	    {
	    protected volatile boolean runnable = false;

	    @Override
	    public  void run()
	    {
	        synchronized(this)
	        {
	            while(runnable)
	            {
	                if(webSource.grab())
	                {
			    	try
	                        {
	                            webSource.retrieve(frame);
				    Highgui.imencode(".bmp", frame, mem);
				
				    
				    ByteArrayInputStream bis = new ByteArrayInputStream(mem.toArray());
				    BufferedImage read = ImageIO.read(bis);
				    javafx.scene.image.Image image = SwingFXUtils.toFXImage(read, null);
				    SnapImage.setImage(image);
				    
				    if(runnable == false)
	                            {
				    	System.out.println("Going to wait()");
				    	this.wait();
				    }
				 }
				 catch(Exception ex)
	                         {
				    System.out.println("Error");
	                         }
	                }
	            }
	        }
	     }
	   }
	    
	
}
