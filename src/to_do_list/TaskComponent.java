package to_do_list;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TaskComponent extends JPanel implements ActionListener {
	
	private JCheckBox checkBox;
	private JTextPane taskField;
	private JButton deleteButton;
	
	public JTextPane getTaskField() {
		return taskField;
	}
	//this panel is used so that we can make update to the task component panel when deleting tasks
	private JPanel parentPanel ;
	
	public TaskComponent(JPanel parentPanel) {
		this.parentPanel = parentPanel;
	
	
	//task field
	taskField = new JTextPane(); 
	taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
	taskField.setContentType("text/html"); 
	taskField.addFocusListener(new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
			taskField.setBackground(Color.WHITE);
		}
		@Override 
		public void focusLost(FocusEvent e) {
			taskField.setBackground(null);
		}
	});
	
	//check box
	checkBox = new JCheckBox();
	checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
	checkBox.addActionListener(this);
	checkBox.addActionListener(this);
	
	//delete box
	deleteButton = new JButton("X");
	deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
	deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	deleteButton.addActionListener(this);
	
	
	//add to this
	add(checkBox);
	add(taskField);
	add(deleteButton);
	}
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBox) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", ""); 
            if (checkBox.isSelected()) {
                taskField.setText("<html><s>" + taskText + "</s></html>");
            } else {
                taskField.setText(taskText);
            }
        } 
        else if (e.getSource() == deleteButton) {
            parentPanel.remove(this); 
            parentPanel.revalidate(); 
            parentPanel.repaint();    
        }
    }
}
