package lab1.taskoptional;

import lab1.task3.Agent;
import lab1.task3.AgentProgram;
import lab1.task3.Environment;
import lab1.task3.EnvironmentState;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private Icon robot, wall, dirt, blank;
    private JButton stepButton, restart, untilDone;
    private Environment environment;
    private JButton[][] grid;
    public GUI(){
        init();
    }
    public void init(){
        environment = new Environment(7, 7, 0.2, 0.1);
        Agent agent = new Agent(new AgentProgram());
        environment.addAgent(agent, "1-1");
        grid = new JButton[7][7];

        robot = new ImageIcon("src/lab1/taskoptional/robot.png");
        wall = new ImageIcon("src/lab1/taskoptional/wall.png");
        dirt = new ImageIcon("src/lab1/taskoptional/spots.png");
        blank = new ImageIcon("src/lab1/taskoptional/square.png");

        setSize(700, 700);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(7, 7));
        JPanel buttonPanel = new JPanel();
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                grid[i][j] = new JButton();
                grid[i][j].setBackground(Color.WHITE);
                mainPanel.add(grid[i][j]);
            }
        }

        stepButton = new JButton("Next step");
        untilDone = new JButton("Run until done");
        buttonPanel.add(stepButton);
        buttonPanel.add(untilDone);
        stepButton.addActionListener(this);
        untilDone.addActionListener(this);

        update();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void update(){
        EnvironmentState environmentState = environment.getCurrentState();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Environment.LocationState locationState = environmentState.getLocationState(i + "-" + j);
                if(locationState == Environment.LocationState.CLEAN || locationState == null){
                    grid[i][j].setIcon(null);
                }
                else if (locationState == Environment.LocationState.DIRTY){
                    grid[i][j].setIcon(dirt);
                }
                else{
                    grid[i][j].setIcon(wall);
                }
            }
        }
        String[] s = environmentState.getAgentLocation().split("-");
        grid[Integer.valueOf(s[0])][Integer.valueOf(s[1])].setIcon(robot);
        repaint();
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if(source == stepButton){
            environment.step();
            update();
        }
        else if(source == untilDone){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!environment.isDone()){
                        environment.step();
                        update();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            thread.start();
        }
    }
}
