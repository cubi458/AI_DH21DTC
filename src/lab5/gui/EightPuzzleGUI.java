package lab5.gui;

import lab5.AStarSearch;
import lab5.BFS;
import lab5.DFS;
import lab5.GreedyBestFirstSearch;
import lab5.HillClimbing;
import lab5.IPuzzleAlgo;
import lab5.Node;
import lab5.Puzzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class EightPuzzleGUI extends JFrame implements ActionListener {
    private int[][] original = {{1,5,3}, {4,0,8}, {7,2,6}};
    private Thread thread;
    private Runnable task;
    private int frameSize = 600;
    private int squareSize = frameSize / 3;
    private JButton moveUp, moveDown, moveLeft, moveRight, solve, reset;
    private JComboBox<String> algorithms;
    private IPuzzleAlgo solver;
    private Image[] images;
    private JPanel painter;
    private int emptySquare;
    private Puzzle puzzle;

    //Class này đóng vai trò như một bảng vẽ
    class Painter extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            for(int row = 0; row < 3; row++){
                for(int col = 0; col < 3; col++){
                    int tile = puzzle.getInitialState().getTile(row, col);
                    if(tile != 0){
                        g.drawImage(images[tile - 1],
                                col * squareSize,
                                row * squareSize,
                                squareSize,
                                squareSize,null);
                    }
                    else{
                        g.setColor(Color.WHITE);
                        g.drawRect(col * squareSize,
                                row * squareSize,
                                squareSize,
                                squareSize);
                    }
                }
            }
        }
    }

    public EightPuzzleGUI(String imageSource) {
        setTitle("8-Puzzle");
        setSize(frameSize, frameSize + 60);
        setLayout(new BorderLayout());
        puzzle = new Puzzle();
        puzzle.readInput("src/lab5/asset/PuzzleMap.txt",
                "src/lab5/asset/PuzzleGoalState.txt");

        algorithms = new JComboBox<>(new String[] {"A*", "Greedy", "BFS", "DFS", "HillClimbing"});
        solver = new AStarSearch();

        //Khởi tạo luồng song song
        task = new Runnable() {
            @Override
            public void run() {
                try {
                    LinkedList<Node> solution = new LinkedList<>();
                    Node trace = solver.executeWithBenchmark(puzzle);
                    if(trace == null){
                        System.out.println("Không tìm được!");
                        return;
                    }
                    while(trace != null){
                        solution.addFirst(trace);
                        trace = trace.getParent();
                    }
                    solution.removeFirst(); //Không bao gồm trạng thái gốc (tức là solution bắt đầu = trạng thái tiếp theo)

                    for(Node step : solution){
                        puzzle.setInitialState(step);
                        repaint();
                        Thread.sleep(300);
                    }
                }
                catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        thread = new Thread(task);

        //Set ảnh và tạo painter
        images = new Image[9];
        cropImage(imageSource);
        painter = new Painter();
        painter.setSize(frameSize, frameSize);
        painter.setBackground(Color.WHITE);
        add(painter, BorderLayout.CENTER);

        //Tạo nút
        JPanel buttonGroup = new JPanel();
        moveUp = new JButton("UP");
        moveUp.addActionListener(this);
        moveDown = new JButton("DOWN");
        moveDown.addActionListener(this);
        moveLeft = new JButton("LEFT");
        moveLeft.addActionListener(this);
        moveRight = new JButton("RIGHT");
        moveRight.addActionListener(this);
        reset = new JButton("RESET");
        reset.addActionListener(this);
        solve = new JButton("SOLVE");
        solve.addActionListener(this);
        buttonGroup.add(moveUp);
        buttonGroup.add(moveDown);
        buttonGroup.add(moveLeft);
        buttonGroup.add(moveRight);
        buttonGroup.add(reset);
        buttonGroup.add(solve);
        buttonGroup.add(algorithms);
        add(buttonGroup, BorderLayout.SOUTH);

        //OK lets a go!!!
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Cắt hình ảnh thành nhiều mảnh
    private void cropImage(String imageName){
        try {
            File imgFile = new File(imageName);
            String dirPath = imgFile.getParent();
            BufferedImage image = ImageIO.read(imgFile);
            int width = image.getWidth();
            int height = image.getHeight();
            int a = width/3;
            int b = height/3;
            int count = 0;
            for(int y = 0; y + b <= height; y += b){
                for(int x = 0; x + a <= width; x += a){
                    images[count] = image.getSubimage(x, y, a, b);
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetThread();
        Object action = e.getSource();
        if(action == moveUp){
            Node node = puzzle.moveWhiteTile(puzzle.getInitialState(), 'u');
            if(node != null){
                puzzle.setInitialState(node);
            }
        }
        else if(action == moveDown){
            Node node = puzzle.moveWhiteTile(puzzle.getInitialState(), 'd');
            if(node != null){
                puzzle.setInitialState(node);
            }
        }
        else if(action == moveLeft){
            Node node = puzzle.moveWhiteTile(puzzle.getInitialState(), 'l');
            if(node != null){
                puzzle.setInitialState(node);
            }
        }
        else if(action == moveRight){
            Node node = puzzle.moveWhiteTile(puzzle.getInitialState(), 'r');
            if(node != null){
                puzzle.setInitialState(node);
            }
        }
        else if(action == reset){
            Node node = new Node(original);
            puzzle.setInitialState(node);
        }
        else if(action == solve){
            String algorithm = (String) algorithms.getSelectedItem();
            switch (algorithm){
                case "A*":
                    solver = new AStarSearch();
                    break;
                case "Greedy":
                    solver = new GreedyBestFirstSearch();
                    break;
                case "BFS":
                    solver = new BFS();
                    break;
                case "DFS":
                    solver = new DFS();
                    break;
                case "HillClimbing":
                    solver = new HillClimbing();
                    break;
            }
            thread.start();
        }

        repaint();
    }

    public void resetThread(){
        if(thread.isAlive()){
            thread.stop();
        }
        thread = new Thread(task);
    }

    public static void main(String[] args) {
        new EightPuzzleGUI("src/lab5/asset/ScratchCat.jpg"); //Dùng ảnh bất kì (loại jpg cho đảm bảo)
    }
}

