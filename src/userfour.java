import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.awt.event.*;


public class userfour extends JFrame implements ActionListener,Runnable{

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    static Socket s;

    static DataInputStream din;
    static DataOutputStream dout;

    Boolean typing;

    BufferedWriter writer;
    BufferedReader reader;


    userfour() {

        p1=new JPanel();

        p1.setLayout(null);

        p1.setBackground(new Color(7,94,84));

        p1.setBounds(0,0,450,70);

        add(p1);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));

        Image i2=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);

        ImageIcon i3=new ImageIcon(i2);

        JLabel l1=new JLabel(i3);

        l1.setBounds(5,17,30,30);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent ae)
            {
                System.exit(0);
            }
        });


        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/bunty.jpeg"));

        Image i5=i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);

        ImageIcon i6=new ImageIcon(i5);

        JLabel l2=new JLabel(i6);

        l2.setBounds(40,5,60,60);
        p1.add(l2);

        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));

        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);

        ImageIcon i9=new ImageIcon(i8);

        JLabel l5=new JLabel(i9);

        l5.setBounds(290,20,30,30);
        p1.add(l5);

        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));

        Image i12=i11.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);

        ImageIcon i13=new ImageIcon(i12);

        JLabel l6=new JLabel(i13);

        l6.setBounds(350,20,35,30);
        p1.add(l6);

        ImageIcon i14=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));

        Image i15=i14.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);

        ImageIcon i16=new ImageIcon(i15);

        JLabel l7=new JLabel(i16);

        l7.setBounds(410,20,13,25);
        p1.add(l7);

        a1=new JTextArea();
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);

        a1.setBounds(5,75,440,530);
        //a1.setBackground(Color.PINK);
        add(a1);

        t1=new JTextField();
        t1.setBounds(5,620,300,30);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));

        add(t1);

        b1=new JButton("SEND");
        b1.setBounds(315,620,70,30);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);




        // getContentPane().setBackground(Color.YELLOW);

        JLabel l3=new JLabel("MIRZAPUR");

        l3.setFont(new Font("SAN_SARIF",Font.BOLD,10));
        l3.setForeground(Color.WHITE);

        l3.setBounds(110,15,100,18);

        p1.add(l3);


        JLabel l4=new JLabel("GUDDU,SWEETY,BABLU...");

        l4.setFont(new Font("SAN_SARIF",Font.PLAIN,6));
        l4.setForeground(Color.WHITE);

        l4.setBounds(110,35,100,20);

        p1.add(l4);

       /* Timer t=new Timer(1, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if(!typing)
                {
                    l4.setText("active now");
                    String name=l4.getText();
                }

            }
        });

        t.setInitialDelay(2000);

        t1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                l4.setText("typing.....");

                t.stop();

                typing=true;

            }

            public void keyReleased(KeyEvent Ke)
            {
                typing =false;

                if(!t.isRunning())
                {
                    t.start();
                }

            }

        });*/


        setLayout(null);
        setSize(390, 700);
        setLocation(400,200);

        //setUndecorated(true);
        setVisible(true);

        try
        {
            Socket socketClient=new Socket("localhost",2001);
            writer=new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }catch(Exception e)
        {}





    }

    public void actionPerformed(ActionEvent ae)
    {
        String str="pandit\n"+t1.getText();

        try
        {
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e)
        {}
    }

    public void run()
    {
        try {
            String msg = "";
            while ((msg = reader.readLine() )!= null)
            {
                a1.append(msg+"\n");
            }

        }
        catch(Exception e){}

        t1.setText("");
    }

    public static void main(String[] args)
    {
        userfour four=new userfour();
        Thread t1=new Thread(four);
        t1.start();

    }




}
