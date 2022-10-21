package smt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;

public class MultiThreadController extends JFrame {

	private JPanel contentPane;
	private Thread t1;
	private Thread t2;
	private Semaphore semaphore = new Semaphore(1);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiThreadController frame = new MultiThreadController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MultiThreadController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSlider slider1 = new JSlider();
		slider1.setValue(0);
		slider1.setBounds(112, 170, 251, 26);
		contentPane.add(slider1);
		
		JSlider slider2 = new JSlider();
		slider2.setValue(0);
		slider2.setBounds(112, 230, 251, 26);
		contentPane.add(slider2);
		
		JLabel lblNewLabel = new JLabel("Thread 1:");
		lblNewLabel.setBounds(30, 170, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblThread = new JLabel("Thread 2:");
		lblThread.setBounds(30, 231, 72, 14);
		contentPane.add(lblThread);
		
		
		JButton btnThread1Stop = new JButton("Thread 1 Stop");
		btnThread1Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.interrupt();
			}
		});
		btnThread1Stop.setBounds(173, 39, 106, 44);
		contentPane.add(btnThread1Stop);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(401, 158, 125, 98);
		contentPane.add(textArea);
		
		JButton btnThread2Start = new JButton("Thread 2 Start");
		btnThread2Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sayac sayac2 = new Sayac(slider2,2000, "T2", textArea, semaphore);
				t2 = new Thread(sayac2);
				t2.start();
			}
		});
		btnThread2Start.setBounds(299, 39, 106, 44);
		contentPane.add(btnThread2Start);
		
		JButton btnThread2Stop = new JButton("Thread 2 Stop");
		btnThread2Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.interrupt();
			}
		});
		btnThread2Stop.setBounds(420, 39, 106, 44);
		contentPane.add(btnThread2Stop);
		
		JButton btnThread1Start = new JButton("Thread 1 Start");
		btnThread1Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sayac sayac1 = new Sayac(slider1,1000, "T1", textArea, semaphore);
				t1 = new Thread(sayac1);
				t1.start();
			}
		});
		btnThread1Start.setBounds(43, 39, 106, 44);
		contentPane.add(btnThread1Start);
		
	
	}
}
