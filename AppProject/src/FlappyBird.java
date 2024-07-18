import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth=360;
    int boardHeight=640;

    //We defined Image variables here.
    Image backgroundImg;
    Image topPipeImg;
    Image birdImg;
    Image bottomPipeImg;

    //Bird Dimensions
    int birdX= boardWidth/8;
    int birdY= boardHeight/2;
    int birdWidth= 34;
    int birdHeight = 24;

    //Defining a Bird class to make things easier
    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    //Pipes

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64; // Scaled the dimensions by 1/6
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }


    //This is the game logic.
    Bird bird;
    int velocityX = -4; //This moves the pipe to the left (So makes it look like the bird is moving right)
    int velocityY= 0;
    int gravity= 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;

    boolean gameOver = false;
    double score = 0;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.blue);
        //This makes sure that the FlappyBird class(That the JPanel is) is the one that takes the key events
        setFocusable(true);
        //checks the 3 key functions
        addKeyListener(this);

        //We load the images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //Bird Image
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        // Pipe placing timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //This is the game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

    }

    public void placePipes() {

        /*Since pipeHeight= 512 px,
        And Math.Random generates a number between 0-1, so
        (pipeHeight/2)*Math.Random generates a number between 0-256*/

        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        /* topPipe.y starts at the top of the topPipe as co ords start from top left corner
        So when we add pipeHeight we get to the bottom of the topPipe
        we add openingSpace and voila we get top of bottomPipe :)
         */
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //Drawing the background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        
        //Drawing the Bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    
        //Drawing the Pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

            //Display Score
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 32));
            //For displaying the game over sign and the final score
            if (gameOver) {
                g.drawString("Game Over :" + String.valueOf((int) score), 10, 35);
                g.drawString("\nPress Space to Restart", 10, 70);
            }
            else {
                g.drawString(String.valueOf((int) score), 10, 35);
            }
        }
    }

    public void move() {
       //Bird move
        velocityY += gravity;
        //To constantly change the velocity
        bird.y += velocityY;
        //For the bird to be capped to the screen and not go out of it
        bird.y = Math.max(bird.y, 0);

        //Pipe move
        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5; //we only add 0.5 because the bird passes 2 pipes so it totals to 1 per pipe pass
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
               
    }

    //This is the action that happens 60 times every sec, so move and repaint repeats 60 times/sec
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
     }

     @Override
     public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;        
            if (gameOver) {
                //We are resetting all the conditions as it was initially
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    
     }
    
     @Override
    public void keyTyped(KeyEvent e) {}
        

    @Override
    public void keyReleased(KeyEvent e) {}

}



