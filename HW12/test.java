import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test extends JFrame {
    private JLabel detailsLabel;
    private JButton dogButton;
    private JButton catButton;
    private JPanel panel;

    public test() {
        setTitle("Pet Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        detailsLabel = new JLabel("Pet Details will be displayed here.");
        dogButton = new JButton("Dog");
        catButton = new JButton("Cat");
        panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1));
        panel.add(detailsLabel);
        panel.add(dogButton);
        panel.add(catButton);

        dogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dog dog = new dog("dog", 3);
                String details = dog.getDetails() + "<br>Sound : " + dog.makeSound() + "<br>Color : " + dog.Color()
                        + "<br>Breed :" + dog.Breed() + "<br>Character : " + dog.Character();
                detailsLabel.setText("<html>" + details + "</html>");
            }
        });

        catButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cat cat = new cat("meow", 2);
                String details = cat.getDetails() + "<br>Sound : " + cat.makeSound() + "<br>Color : " + cat.Color()
                        + "<br>Breed :" + cat.Breed() + "<br>Character : " + cat.Character();
                ;
                detailsLabel.setText("<html>" + details + "</html>");
            }
        });

        add(panel);
        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }
}