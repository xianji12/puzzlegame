package com.itjie.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //创建一个二维数组（4行4列）
    //目的：用来管理数据
    //加载图片的时候，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;
    String path = "image\\animal\\animal1\\";
    //定义一个正确的数组，存储正确的数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };
    int step = 0;
    JMenuItem girlitem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    Random r = new Random();


    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱)
        initData();

        //初始化图片(根据打乱之后的结果去加载图片)
        initImage();

        this.setVisible(true);
    }

    private void initData() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = r.nextInt(array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        //给二维数组添加数据
        //解法一
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = array[i];

            //当i=0，i/4=0，i%4=0

        }
    }
    //添加图片的时候，需要按照二维数组中管理的数据添加图片

    private void initImage() {
        //请空原本已经出现的所有图片
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Users\\86157\\IdeaProjects\\untitled3\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        ;
        //路径分成两种
        //绝对路径：一定是从盘符开始的。c:\D：\
        //相对路径：不是从盘符开始的，相对项目而言。 aaa\\bbb，在aaa文件夹里面再去找bbb文件夹
        //外循环---把内循环重复执行4次
        for (int i = 0; i < 4; i++) {
            //内循环---表示在一行添加4张图片
            //*先执行内循环再执行外循环
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //把管理容器添加到界面中
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                // jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                jLabel.setBorder(new BevelBorder(1));
                //获取隐藏容器再add（）方法
                this.getContentPane().add(jLabel);
            }

        }
        //添加背景图片
        ImageIcon bg1 = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(bg1);
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加到界面中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeJMenu = new JMenu("更换图片");

        replayItem.addActionListener(this);
        girlitem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        changeJMenu.addActionListener(this);

        //将每个条目对象添加到（功能 关于我们）选项中
        changeJMenu.add(girlitem);
        changeJMenu.add(animalItem);
        changeJMenu.add(sportItem);
        functionJMenu.add(changeJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        //将两个选项（功能 关于我们）添加到菜单对象中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //再把菜单对象添加到窗体JFrame中
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v2.0");
        ImageIcon icon = new ImageIcon("App.界面照片\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//打X时关闭程序
        this.setLayout(null);//绝对布局，自己可以随心所欲摆放文件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            ImageIcon bg = new ImageIcon("image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加到界面中
            this.getContentPane().add(background);
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        //对上下左右键盘操作进行判断
        //左：37上：38右：39下：40
        e.getKeyCode();
        if (e.getKeyCode() == 37) {
            if (y == 3) {
                return;
            }
            ;
            //逻辑：
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
            ;
        } else if (e.getKeyCode() == 38) {
            if (x == 3) {
                return;
            }
            //逻辑：
            //把空白方块下面的数字向上移动
            //x,y代表空白方块    则x+1，y代表下方数字
            //把空白方块下方的数字赋值给空白方块
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (e.getKeyCode() == 39) {
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (e.getKeyCode() == 40) {
            if (x == 0) {
                return;
            }
            //逻辑：
            //把空白方块下面的数字向下移动
            //x,y代表空白方块    则x-1，y代表上方数字
            //把空白方块上方的数字赋值给空白方块
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (e.getKeyCode() == 65) {
            initImage();
        } else if (e.getKeyCode() == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0},
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayItem) {
            step = 0;
            initData();
            initImage();
        } else if (e.getSource() == reLoginItem) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (e.getSource() == closeItem) {
            //this.dispose();
            System.exit(0);
        } else if (e.getSource() == accountItem) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            ImageIcon icon = new ImageIcon("App.界面照片\\20196131733891546.jpeg");
            jDialog.setIconImage(icon.getImage());
            JLabel jLabel = new JLabel(new ImageIcon("微信二维码\\帅.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        } else if (e.getSource() == girlitem) {
            step = 0;
            String girl = "girl" + (r.nextInt(13) + 1) + "\\";
            path = "image\\girl\\" + girl;
            initData();
            initImage();
        } else if (e.getSource() == animalItem) {
            step = 0;
            String animal = "animal" + (r.nextInt(8) + 1) + "\\";
            path = "image\\animal\\" + animal;
            initData();
            initImage();
        } else if (e.getSource() == sportItem) {
            step = 0;
            String sport = "sport" + (r.nextInt(10) + 1) + "\\";
            path = "image\\sport\\" + sport;
            initData();
            initImage();
        }
    }
}
