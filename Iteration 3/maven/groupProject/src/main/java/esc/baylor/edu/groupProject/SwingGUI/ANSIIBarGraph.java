package esc.baylor.edu.groupProject.SwingGUI;

import javax.swing.JTextField;

public class ANSIIBarGraph extends JTextField {
	public ANSIIBarGraph() {
		setEditable(false);
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	private float value = 0.0f;

}
