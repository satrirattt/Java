import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test extends JFrame implements ActionListener {
    Author au[];
    Publisher pub[];
    String authorStr[], publisherStr[];
    Container container;
    JComboBox auCombo, pubCombo;
    JLabel auLabel, pubLabel;
    JButton addauBtn, addpubBtn, saveBtn, clearBtn, cancelBtn; // เปลี่ยนชื่อ cancleBtn เป็น cancelBtn
    JTextField auText;
    JTextArea pubTextArea;
    JScrollPane pubScroll;
    Book b;
    int count = 0;

    public test() {
        super("Program Book ");
        container = getContentPane();
        container.setLayout(new FlowLayout());
        initAuthor();
        initPublisher();
        initGui();
        b = new Book(new Author(), 5);
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initAuthor() {
        au = new Author[3];
        authorStr = new String[3];
        au[0] = new Author("YUVAL", "NOAH");
        au[1] = new Author("ACIMAN, ANDRE", "---");
        au[2] = new Author("ALBOM, MITCH", "---");
        for (int n = 0; n < au.length; n++)
            authorStr[n] = au[n].toString();
    }

    public void initPublisher() {
        pub = new Publisher[3];
        publisherStr = new String[3];
        pub[0] = new Publisher("HOMO DEUS: A BRIEF HISTORY OF TOMORROW", 2020);
        pub[1] = new Publisher("CALL ME BY YOUR NAME", 2020);
        pub[2] = new Publisher("TUESDAYS WITH MORRIE", 2020);
        for (int n = 0; n < pub.length; n++)
            publisherStr[n] = pub[n].toString();
    }

    public void initGui() {
        auLabel = new JLabel("Select Author : ");
        container.add(auLabel);
        auCombo = new JComboBox(authorStr);
        auCombo.setMaximumRowCount(3);
        container.add(auCombo);
        addauBtn = new JButton("Add Author");
        addauBtn.addActionListener(this);
        container.add(addauBtn);
        auText = new JTextField(40);
        auText.setEditable(false);
        container.add(auText);

        pubLabel = new JLabel("Select Publisher : ");
        container.add(pubLabel);
        pubCombo = new JComboBox(publisherStr);
        pubCombo.setMaximumRowCount(3);
        container.add(pubCombo);
        addpubBtn = new JButton("Add Publisher");
        addpubBtn.addActionListener(this);
        container.add(addpubBtn); // เพิ่มปุ่ม Add Publisher ลงใน container
        addpubBtn.setEnabled(false); // ปิดใช้งานเมื่อโหลด GUI
        pubTextArea = new JTextArea(6, 40);
        pubTextArea.setEditable(false);
        pubScroll = new JScrollPane(pubTextArea);
        container.add(pubScroll);

        saveBtn = new JButton(" Save ");
        saveBtn.setEnabled(false);
        saveBtn.addActionListener(this);
        container.add(saveBtn);
        cancelBtn = new JButton(" Cancel ");
        cancelBtn.setEnabled(false);
        cancelBtn.addActionListener(this);
        container.add(cancelBtn);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addauBtn) {
            int n = auCombo.getSelectedIndex();
            auText.setText(au[n].toString());
            b.setAuthor(au[n]);
            addauBtn.setEnabled(false);
            addpubBtn.setEnabled(true); // เปิดใช้งานปุ่ม Add Publisher เมื่อเลือก Author แล้ว
            saveBtn.setEnabled(true);
            cancelBtn.setEnabled(true);
        } else if (event.getSource() == addpubBtn) {
            int n = pubCombo.getSelectedIndex();
            pubTextArea.append(publisherStr[n] + "\n"); // แสดงข้อมูล Publisher จากตัวแปร publisherStr
            b.setPublisher(pub[n]);
            count++; // เพิ่มค่า count เมื่อผู้ใช้คลิกที่ปุ่ม Add Publisher
            addpubBtn.setEnabled(false);
            saveBtn.setEnabled(true);
            cancelBtn.setEnabled(true);

        } else if (event.getSource() == saveBtn) {
            String output = "";
            output = "Author : " + b.getAuthor();
            output += "\nPublisher: ";
            for (int n = 0; n < count; n++)
                output += b.getPublisher(n);
            JOptionPane.showMessageDialog(this, output,
                    "Registration Data", JOptionPane.INFORMATION_MESSAGE);
            resetBtn();
            count = 0;
        } else if (event.getSource() == cancelBtn) {
            auText.setText("");
            pubTextArea.setText("");
            count = 0;
            resetBtn();
        }
    }

    public void resetBtn() {
        addauBtn.setEnabled(true);
        addpubBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        cancelBtn.setEnabled(false);
        auText.setText("");
        pubTextArea.setText("");
    }

    public static void main(String[] args) {
        new test();
    }
}
