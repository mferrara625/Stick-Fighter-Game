package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Contents extends JPanel implements ActionListener {


    private static int playerHealth = 250, difficulty = 150, enemyX = 500, enemyY = 205, enemyHP = 150, enemyHPMax = 150, x = 300, y = 205,
            floorLevel = 205, score1 = 0, count = 0, count2 = 0, count3 = 0, count4 = 0, enemiesDefeated = 0;
    public static boolean didEnemyJump = false, didEnemyKick = false, didEnemyJab = false, didEnemyBlock = false, didWin = false, didJump = false,
            playerFailed = false, didBlock = false, crouchInProgress = false, didCrouch = false, didJab = false, didHook = false, didKick = false,
            enemyHit = false, enemyHitBody = false, enemyDefeated = false;


    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();
    }

    static KeyListener listener = new KeyListener() {
        @Override
        public void keyPressed(KeyEvent event) {

            if(event.getKeyChar()==KeyEvent.VK_A || event.getKeyChar()=='a' && !playerFailed){
                xV = -3;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_D || event.getKeyChar()=='d' && !playerFailed){
                xV = 3;
                event.consume();
            }
            if((event.getKeyChar()==KeyEvent.VK_J || event.getKeyChar()=='j') && !playerFailed && !didJab && !didHook && !didKick && !didBlock && !didCrouch && count4 == 0){
                count = 0;
                count4++;
                didJab = true;
                event.consume();
            }
            if((event.getKeyChar()==KeyEvent.VK_H || event.getKeyChar()=='h') && !playerFailed && !didHook && !didJab && !didKick && !didBlock && !didCrouch && count4 == 0){
                count = 0;
                count4++;
                didHook = true;
                event.consume();
            }
            if((event.getKeyChar()==KeyEvent.VK_K || event.getKeyChar()=='k') && !playerFailed && !didHook && !didJab && !didKick && !didBlock && !didCrouch && count4 == 0){
                count = 0;
                count4++;
                didKick = true;
                event.consume();
            }
            if((event.getKeyChar()==KeyEvent.VK_B || event.getKeyChar()=='b') && !playerFailed && !didHook && !didJab && !didKick && !didCrouch){
                count = 0;
                didBlock = true;
                event.consume();
            }
            if((event.getKeyChar()==KeyEvent.VK_N || event.getKeyChar()=='n') && !playerFailed && !didHook && !didJab && !didKick && !didBlock){
                count = 0;
                didCrouch = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_SPACE && y >= floorLevel && !playerFailed){
                yV = -6;
                didJump = true;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_ENTER && playerFailed){
                playerFailed = false;
//                didWin = false;
                enemyHP = 150;
                enemyHPMax = 150;
                score1 = 0;
                difficulty = 150;
                playerHealth = 250;
                x = 300;
                event.consume();
            }


        }

        @Override
        public void keyReleased(KeyEvent event) {
            if(event.getKeyChar()==KeyEvent.VK_A || event.getKeyChar()=='a'){
                xV = 0;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_D || event.getKeyChar()=='d'){
                xV = 0;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_H || event.getKeyChar()=='h'){
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_B || event.getKeyChar()=='b'){
                didBlock = false;
                event.consume();
            }
            if(event.getKeyChar()==KeyEvent.VK_N || event.getKeyChar()=='n'){
                didCrouch = false;
                event.consume();
            }
        }

        @Override
        public void keyTyped(KeyEvent event) {
        }

    };

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        ImageIcon ii = new ImageIcon(this.getClass().getResource("stance.png"));
        Image stance = ii.getImage();
        ImageIcon ii2 = new ImageIcon(this.getClass().getResource("fightStance.png"));
        Image fightStance = ii2.getImage();
        ImageIcon ii3 = new ImageIcon(this.getClass().getResource("leftJab1.png"));
        Image leftJab1 = ii3.getImage();
        ImageIcon ii4 = new ImageIcon(this.getClass().getResource("leftJab2.png"));
        Image leftJab2 = ii4.getImage();
        ImageIcon ii5 = new ImageIcon(this.getClass().getResource("rightHook1.png"));
        Image rightHook1 = ii5.getImage();
        ImageIcon ii6 = new ImageIcon(this.getClass().getResource("rightHook2.png"));
        Image rightHook2 = ii6.getImage();
        ImageIcon ii7 = new ImageIcon(this.getClass().getResource("kick1.png"));
        Image kick1 = ii7.getImage();
        ImageIcon ii8 = new ImageIcon(this.getClass().getResource("kick2.png"));
        Image kick2 = ii8.getImage();
        ImageIcon ii9 = new ImageIcon(this.getClass().getResource("block.png"));
        Image block = ii9.getImage();
        ImageIcon ii10 = new ImageIcon(this.getClass().getResource("crouch1.png"));
        Image crouch1 = ii10.getImage();
        ImageIcon ii11 = new ImageIcon(this.getClass().getResource("crouch2.png"));
        Image crouch2 = ii11.getImage();
        ImageIcon ii12 = new ImageIcon(this.getClass().getResource("enemyHeadHit.png"));
        Image enemyHeadHit = ii12.getImage();
        ImageIcon ii13 = new ImageIcon(this.getClass().getResource("enemyBodyHit.png"));
        Image enemyBodyHit = ii13.getImage();
        ImageIcon ii14 = new ImageIcon(this.getClass().getResource("enemyKO1.png"));
        Image enemyKO1 = ii14.getImage();
        ImageIcon ii15 = new ImageIcon(this.getClass().getResource("enemyKO2.png"));
        Image enemyKO2 = ii15.getImage();
        ImageIcon ii16 = new ImageIcon(this.getClass().getResource("enemyKO3.png"));
        Image enemyKO3 = ii16.getImage();
        ImageIcon ii17 = new ImageIcon(this.getClass().getResource("enemyFightStance.png"));
        Image enemyFightStance = ii17.getImage();
        ImageIcon ii18 = new ImageIcon(this.getClass().getResource("enemyLeftJab1.png"));
        Image enemyLeftJab1 = ii18.getImage();
        ImageIcon ii19 = new ImageIcon(this.getClass().getResource("enemyLeftJab2.png"));
        Image enemyLeftJab2 = ii19.getImage();
        ImageIcon ii20 = new ImageIcon(this.getClass().getResource("enemyBlock.png"));
        Image enemyBlock = ii20.getImage();
        ImageIcon ii21 = new ImageIcon(this.getClass().getResource("enemyKick1.png"));
        Image enemyKick1 = ii21.getImage();
        ImageIcon ii22 = new ImageIcon(this.getClass().getResource("enemyKick2.png"));
        Image enemyKick2 = ii22.getImage();
        ImageIcon ii23 = new ImageIcon(this.getClass().getResource("KO1.png"));
        Image KO1 = ii23.getImage();
        ImageIcon ii24 = new ImageIcon(this.getClass().getResource("KO2.png"));
        Image KO2 = ii24.getImage();
        ImageIcon ii25 = new ImageIcon(this.getClass().getResource("KO3.png"));
        Image KO3 = ii25.getImage();

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawString( "CONTROLS: ", 10, 25);
        g2d.drawString( "SPACE: JUMP", 10, 50);
        g2d.drawString( "MOVE LEFT: A", 10, 75);
        g2d.drawString( "MOVE RIGHT: D", 10, 100);
        g2d.drawString( "HOOK: H", 10, 125);
        g2d.drawString( "JAB: J", 10, 150);
        g2d.drawString( "KICK: K", 10, 175);
        g2d.drawString("BLOCK: B", 10, 200);
        g2d.drawString("CROUCH: N", 10, 225);
        g2d.drawString("Difficulty: " + difficulty, 10, 250);
        g2d.drawString("x: " + x + "y: " + y + " Score: " + score1, 10, 275);

        g2d.drawString("ENEMIES DEFEATED: " + enemiesDefeated/100, 625, 65);
        g2d.drawString("ENEMY HP: ", 625, 40);
        g2d.drawRect(700, 25, enemyHPMax, 25);
        g2d.setColor(Color.orange);
        g2d.fillRect(700, 25, enemyHP, 25);
        g2d.setColor(Color.black);

        g2d.drawString("PLAYER HP: ", 125, 40);
        g2d.drawRect(200, 25, 250, 25);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(200, 25, playerHealth, 25);
        g2d.setColor(Color.black);
        g2d.drawString("SCORE: " + score1, 460, 40);





//        g2d.drawString(" /﹋\\", x, y);
//        g2d.drawString("(҂`_´)", x, y + 15);
//        g2d.drawString("<,︻╦╤─ ", x, y  + 30);
//        g2d.drawString("/﹋\\", x, y + 45);

//        " ҉ - -"
        if(!enemyHit && !enemyHitBody && !enemyDefeated && !didEnemyJab && !didEnemyBlock && !didEnemyKick)
        g2d.drawImage(enemyFightStance, enemyX, enemyY, this);

        if(didEnemyBlock && !enemyDefeated){
            if(count2 >= 0 && count2 <= 60)
            g2d.drawImage(enemyBlock, enemyX, enemyY, this);
            if(count2 > 60 || didEnemyJab || didEnemyKick)
                didEnemyBlock = false;
        }

        if(playerFailed) {
            if (count3 >= 0 && count3 <= 10)
                g2d.drawImage(stance, x, y, this);
            if (count3 >= 11 && count3 <= 22)
                g2d.drawImage(KO1, x, y + 50, this);
            if (count3 >= 23 && count3 <= 35)
                g2d.drawImage(KO2, x + 25, y + 100, this);

            if (count3 > 35)
                g2d.drawImage(KO3, x + 50, y + 150, this);



        }

        if(enemyDefeated) {
            if (count3 >= 0 && count3 <= 10)
                g2d.drawImage(stance, enemyX, enemyY, this);
            if (count3 >= 11 && count3 <= 22)
                g2d.drawImage(enemyKO1, enemyX, enemyY + 50, this);
            if (count3 >= 23 && count3 <= 35)
                g2d.drawImage(enemyKO2, enemyX - 25, enemyY + 100, this);

            if (count3 > 35 && count3 < 56)
                g2d.drawImage(enemyKO3, enemyX - 50, enemyY + 150, this);

            if(count3 == 99 && difficulty > 10)
                difficulty -= 5;

            if(count3 > 100){
                enemyHPMax = (int)(Math.random() * 150) + 1;
                enemyHP = enemyHPMax;
                enemyDefeated =false;

            }

        }

        if(enemyHit && !enemyHitBody && !enemyDefeated){
            if(count >= 0 && count <= 5)
                g2d.drawImage(stance, enemyX, enemyY, this);
            if(count >= 6 && count <= 12){
                g2d.drawImage(enemyHeadHit, enemyX, enemyY, this);
                enemyHP -= (int)(Math.random() * 2);
            }
            if(count > 12)
                enemyHit = false;

        }


        if(enemyHitBody && !enemyDefeated){
            if(count >= 0 && count <= 5)
                g2d.drawImage(stance, enemyX, enemyY, this);
            if(count >= 6 && count <= 12){
                g2d.drawImage(enemyBodyHit, enemyX - 75, enemyY, this);
                enemyHP -= (int)(Math.random() * 2);
            }
            if(count > 12)
                enemyHitBody = false;
        }

        if(!didJab && !didHook && !didKick && !didBlock && !didCrouch && !playerFailed)
        g2d.drawImage(fightStance, x, y, this);

        if(didBlock)
            g2d.drawImage(block, x, y, this);


        if(didJab){
            if(count >= 0 && count <= 4){
                count4++;
                g2d.drawImage(leftJab1, x, y, this);
            }
            if(count >= 5 && count <= 9)
                g2d.drawImage(leftJab2, x, y, this);
            if(count > 9){
                didJab = false;
                count4 = 0;
            }

        }
        if(didEnemyJab && !didEnemyBlock && !enemyDefeated){
            if(count2 >= 0 && count2 <= 4)
                g2d.drawImage(enemyLeftJab1, enemyX, enemyY, this);
            if(count2 >= 5 && count2 <= 9)
                g2d.drawImage(enemyLeftJab2, enemyX, enemyY, this);
            if(count2 > 9)
                didEnemyJab = false;

        }
        if(didEnemyKick && !didEnemyBlock && !enemyDefeated){
            if(count2 >= 0 && count2 <= 4)
                g2d.drawImage(enemyKick1, enemyX, enemyY, this);
            if(count2 >= 5 && count2 <= 9)
                g2d.drawImage(enemyKick2, enemyX, enemyY, this);
            if(count2 > 9)
                didEnemyKick = false;

        }
        if(didHook){
            if(count >= 0 && count <= 4){
                count4++;
                g2d.drawImage(rightHook1, x, y, this);
            }
            if(count >= 5 && count <= 9)
                g2d.drawImage(rightHook2, x, y, this);
            if(count > 9){
                didHook = false;
                count4 = 0;
            }

        }
        if(didKick){
            if(count >= 0 && count <= 4){
                count4++;
                g2d.drawImage(kick1, x, y, this);
            }
            if(count >= 5 && count <= 9)
                g2d.drawImage(kick2, x, y, this);
            if(count > 9){
                didKick = false;
                count4 = 0;
            }

        }
        if(didCrouch){
            if(!didJump){
                if(count >= 0 && count <= 4 && !crouchInProgress){
                    y = floorLevel + 25;
                    g2d.drawImage(crouch1, x, y, this);
                }
                if(count > 4)
                    crouchInProgress = true;

                if(crouchInProgress){
                    y = floorLevel + 50;
                    g2d.drawImage(crouch2, x, y, this);
                }
            } else {
                g2d.drawImage(crouch2, x, y + 50, this);
            }


        }



    }


    private static int zV = 3;
    private static int zV2 = 8;
    private static int yV = 0;
    private static int xV = 0;




    public void move(){
        enemyX = enemyX + zV;
        enemyY = enemyY + zV2;
        y = y + yV;
        x = x + xV;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();

        if(enemyX <= 400){
            zV = (int)(Math.random() * 3) + 1;
        } else if (enemyX >= 600) {
            zV = (int)(Math.random() * 3) - 3;
        }

        if(yV <= 6 && count2 % 4 == 0){
            yV++;
            if(y >= floorLevel){
                y = floorLevel;
                yV = 0;
                didJump = false;
            }
        }
        if(y >= floorLevel)
            y = floorLevel;

        if(zV2 <= 6 && count2 % 4 == 0){
            zV2++;
            if(enemyY >= floorLevel){
                enemyY = floorLevel;
                zV2 = 0;
                didEnemyJump = false;
            }
        }
        if(enemyY >= floorLevel)
            enemyY = floorLevel;
        if(enemyY <= 0)
            zV2++;

        if(didEnemyJab && !didBlock && !didCrouch || didEnemyKick && !didBlock && !didCrouch){
            if(enemyX - 75 >= x && enemyX - 75 <= x + 85 && enemyY >= y - 50 && enemyY <= y + 205){
                playerHealth--;
            }
        }
        if(didEnemyKick && !didEnemyJump && !didCrouch){

            if(enemyX - 75 >= x && enemyX - 75 <= x + 85 && enemyY + 75 >= y && enemyY + 75 <= y + 205){
                if(didBlock)
                    didBlock = false;
                playerHealth--;
            }
        }


        count++;
        count2++;
        count3++;

        if((didHook && !didEnemyBlock) || (didJab && !didEnemyBlock)){
            if(x + 170 >= enemyX && x + 170 <= enemyX + 85 && y >= enemyY - 50 && y <= enemyY + 205){
                score1++;
                enemyHit = true;
            }
        }
        if(didJump && didKick && !didEnemyBlock && !enemyDefeated){
            if(x + 170 >= enemyX && x + 170 <= enemyX + 85 && y + 75 >= enemyY - 85 && y + 75 <= enemyY + 205){
                score1++;
                enemyHit = true;
            }
        }
        if(didKick && !didJump){

            if(x + 170 >= enemyX && x + 170 <= enemyX + 85 && y + 75 >= enemyY - 85 && y + 75 <= enemyY + 205){
                if(didEnemyBlock)
                    didEnemyBlock = false;
                score1++;
                enemyHitBody = true;
            }
        }
        if(playerHealth <= 0){
            if(!playerFailed){
                count3 = 0;
                playerFailed = true;
            }
        }
        if(enemyHP <= 0){
            if(!enemyDefeated)
                count3 = 0;

            enemiesDefeated++;
            score1 += 5;
            enemyDefeated = true;
        }
        if(count2 > difficulty + 5)
            count2 = 0;
        if(count2 == (int)(Math.random() * difficulty) + 1 && !enemyDefeated){
            count2 = 0;
            int randInt = (int)(Math.random() * 4) + 1;
            int randInt2 = (int)(Math.random() * 4) + 1;
            if(randInt2 % 2 == 0){
                zV2 = -6;
                didEnemyJump = true;
            }
            if(randInt == 2)
                didEnemyJab = true;
            else if (randInt == 3)
                didEnemyBlock = true;
            else if (randInt == 4){
                didEnemyKick = true;
            }
        }

        repaint();
    }
}
