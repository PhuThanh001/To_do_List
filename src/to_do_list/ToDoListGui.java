package to_do_list;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToDoListGui extends JFrame implements ActionListener   {
	
	private JPanel taskPanel,taskComponentPanel ;
	
	public ToDoListGui() {
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }
	
	public void addGuiComponents () 
	{
		JLabel bannerLabel = new JLabel("To do List");
		try {
            // Tải font từ file
            File fontFile = new File("src/to_do_list/LEMONMILK-Light.otf"); 
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(36f);
            bannerLabel.setFont(customFont);
        } catch (IOException e) {
            System.out.println("Không tìm thấy file font: " + e.getMessage());
            bannerLabel.setFont(new Font("Arial", Font.PLAIN, 36)); 
        } catch (FontFormatException e) {
            System.out.println("Định dạng font không hợp lệ: " + e.getMessage());
            bannerLabel.setFont(new Font("Arial", Font.PLAIN, 36)); 
        }		bannerLabel.setBounds
		((CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,15,
		CommonConstants.BANNER_SIZE.width,CommonConstants.BANNER_SIZE.height
		);
		
		taskPanel = new JPanel();
		
		taskComponentPanel = new JPanel();
		taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
		taskPanel.add(taskComponentPanel);
		
		
		JScrollPane scrollPane = new JScrollPane(taskPanel);
		scrollPane.setBounds(8 , 70,CommonConstants.TASKPANEL_SIZE.width,CommonConstants.TASKPANEL_SIZE.height);
		scrollPane.setMaximumSize(CommonConstants.TASKFIELD_SIZE);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//add Task Button
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.setBounds(-5,CommonConstants.GUI_SIZE.height - 88,
				CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height);
		addTaskButton.addActionListener(this);
		
		//add to frame
		this.getContentPane().add(bannerLabel);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(addTaskButton);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equalsIgnoreCase("Add Task")) {
			//create a TaskComponent 
			TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
			taskComponentPanel.add(taskComponent);
			
			//make the task field request focus after creation
			taskComponent.getTaskField().requestFocus();
			repaint();
			revalidate();
		}
	}
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ToDoListGui().setVisible(true);
            }
        });
    }
}
	