import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Sets the board size.
        int boardWidth=360;
        int boardHeight=640;

        //Initializes JFrame
        JFrame frame=new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird= new FlappyBird();
        frame.add(flappyBird);
        flappyBird.requestFocus();
        //the next line makes sure we don't include title bar in the dimensions we set.
        frame.pack();
        //better to make it visible after everything has loaded in.
        frame.setVisible(true);
    }
}
