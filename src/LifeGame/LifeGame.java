package LifeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//生命游戏
public class LifeGame {
    // GUI组件
    JFrame jf;    // 存放细胞状态
    boolean[][] st1;
    // 显示细胞状态
    JPanel[][] jp;
   
    public LifeGame(int m, int n) {
        // GUI组件初始化
        jf = new JFrame("Life Game");
        st1 = new boolean[m][n];
        jp = new JPanel[m][n];
        jf.setLayout(new GridLayout(m, n, 2, 2));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                jp[i][j] = new JPanel();
                jp[i][j].setBackground(Color.white);
                jf.add(jp[i][j]);
            }
        }
        jf.setSize(600, 600);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        randomized();
        updata();
        jf.setVisible(true);
    }
   
    public void randomized() {
        for (int i = 1; i < st1.length - 1; i++) {
            for (int j = 1; j < st1[i].length - 1; j++) {
                if (Math.random() > 0.5) {
                    st1[i][j] = true;
                } else {
                    st1[i][j] = false;
                }
            }
        }
    }
   
    public void generation() {
        for (int i = 1; i < st1.length - 1; i++) {
            for (int j = 1; j < st1[i].length - 1; j++) {
                int counter = 0;
                if (jp[i - 1][j - 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i - 1][j].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i - 1][j + 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i][j - 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i][j + 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i + 1][j - 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i + 1][j].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i + 1][j + 1].getBackground() == Color.red) {
                    counter++;
                }
                if (jp[i][j].getBackground() == Color.red) {
                    if (counter <= 1) {
                        st1[i][j] = false;
                    } else if (counter > 3) {
                        st1[i][j] = false;
                    }
                } else {
                    if (counter == 3) {
                        st1[i][j] = true;
                    }
                }
            }
        }
    }
   
    public void updata() {
        for (int i = 1; i < st1.length - 1; i++) {
            for (int j = 1; j < st1[i].length - 1; j++) {
                if (st1[i][j]) {
                    jp[i][j].setBackground(Color.red);
                } else {
                    jp[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
   
    public static void main(String[] args) {
        // 创建游戏对象
        LifeGame lg = new LifeGame(20, 20);
        // 进行初始化
        lg.randomized();
        // 更新显示
        lg.updata();
        while (true) {
            // 取得下一代
            lg.generation();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 更新显示
            lg.updata();
        }
    }
}
