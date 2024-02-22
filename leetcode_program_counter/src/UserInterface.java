import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface {
    static Frame frame;

    public static Frame getFrame() {
        return frame;
    }

    public static void setFrame(Frame frame) {
        UserInterface.frame = frame;
    }

    public void GUIFrame(){
        frame = new Frame("leetcode solved count finder");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });



        Font font = new Font("Arial", Font.PLAIN, 14);

        Label ProfileIdLabel = new Label("Enter Profile Id ");
        ProfileIdLabel.setBounds(100,170,100,30);
        ProfileIdLabel.setFont(font);


        TextField tf = new TextField();
        tf.setBounds(200,170,220,25);
        tf.setFont(font);
        tf.setForeground(Color.black);

        Label emptyTextFieldLabel = new Label("The text field is empty...!!!");
        emptyTextFieldLabel.setBounds(190,100,200,20);
        emptyTextFieldLabel.setFont(font);
        emptyTextFieldLabel.setForeground(Color.red);
        emptyTextFieldLabel.setVisible(false);



        Button submitButton = new Button("Submit");
        submitButton.setBounds(140,210,100,30);
        submitButton.setBackground(Color.cyan);
        submitButton.setFont(font);

        Button resetButton = new Button("Reset");
        resetButton.setFont(font);
        resetButton.setBounds(240,210,100,30);
        resetButton.setBackground(Color.lightGray);

        Label []result = new Label[4];
        setResultTextColor(result);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxValueFinder bv = new BoxValueFinder();
                String str = "";


                String tfValue = tf.getText();

                if(!tfValue.isEmpty())
                {
                    bv.formatProfileId(tfValue);

                    String boxValueResult = bv.getResult();
                    System.out.println();
                    if(!boxValueResult.isEmpty())
                    {
                        String []resultDisplay = bv.getResult().split(",");

                        int yAxis = 0;
                        for(int i = 0 ; i < resultDisplay.length ; i++){
                            if(result[i] != null)
                            {
                                result[i].setFont(font);
                                result[i].setText(resultDisplay[i]);
                                result[i].setBounds(180,250 + yAxis,250,25);
                                yAxis += 20;
                                frame.add(result[i]);
                            }
                        }
                    }
                }
                else{
                    emptyTextFieldLabel.setVisible(true);

                    Timer timer = new Timer(2500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            emptyTextFieldLabel.setVisible(false);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<result.length;i++){
                    result[i].setText("");
                }
                tf.setText("");
            }
        });

        frame.setTitle("Leetcode Tally");
        frame.add(tf);
        frame.add(ProfileIdLabel);
        frame.add(submitButton);
        frame.add(resetButton);
        frame.add(emptyTextFieldLabel);
    }



    public void setResultTextColor(Label []result){
        for(int i=0;i<result.length;i++){
            result[i] = new Label();
        }

        result[0].setForeground(Color.green);
        result[1].setForeground(Color.orange);
        result[2].setForeground(Color.red);
        result[3].setForeground(Color.darkGray);
    }

}