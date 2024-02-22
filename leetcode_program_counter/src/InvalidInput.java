import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvalidInput extends UserInterface{

    public void invalidUserName() {
        Label invalidUserNameLabel = new Label("Please Enter a valid User name...!!!");
        invalidUserNameLabel.setVisible(true);
        invalidUserNameLabel.setForeground(Color.red);
        invalidUserNameLabel.setBounds(160,100,200,100);
        frame.add(invalidUserNameLabel);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidUserNameLabel.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void invalidProfileLink(){
        Label invalidProfileLinkLabel = new Label("Please Enter a valid Profile Link...!!!");
        invalidProfileLinkLabel.setForeground(Color.red);
        invalidProfileLinkLabel.setBounds(150,100,200,100);
        invalidProfileLinkLabel.setVisible(true);
        frame.add(invalidProfileLinkLabel);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidProfileLinkLabel.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}