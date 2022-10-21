package smt;

import java.util.concurrent.Semaphore;

import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Sayac implements Runnable{

	private JSlider slider;
	private int time;
	private String name;
	private JTextArea textArea;
	private Semaphore semaphore;
	
	public Sayac(JSlider slider, int time, String name, JTextArea textArea, Semaphore semaphore) {
		super();
		this.slider = slider;
		this.time = time;
		this.name = name;
		this.textArea = textArea;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		
		for(int i=0;i<20;i++) {
			this.slider.setValue(this.slider.getValue() + i);
			try {
				Thread.sleep(this.time);
				semaphore.acquire();
				this.textArea.setText(this.name + ":" + i);
				semaphore.release();
			} catch (InterruptedException e) {
				//e.printStackTrace();
				break;
			}
		}
		
		
	}

}
