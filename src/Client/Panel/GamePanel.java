package Client.Panel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GamePanel extends JFrame implements ActionListener{
    String[] questions =
            {
                    " hur gammal är simon ?",

                    " Fråga två?",

                    " Fråga tre?",

                    " Fråga fyra?"
            };
    String[][] options =
            {
                    {"10","34","2","3"},
                    {"Fel svar 1","Rätt svar","Fel Svar 2","Fel svar 3"},
                    {"Fel svar 1","Fel svar 2","Rätt svar","Fel svar 3"},
                    {"Fel svar 1","Fel svar 2","Rätt svar","Fel svar 3*"}
            };
    char[] answers =
            {
                    'A',
                    'B',
                    'C',
                    'C'
            };
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 60;
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0) {
                displayAnswer();
            }
        }
    });
    public GamePanel(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,370);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setResizable(false);

        textfield.setBounds(0,0,1100,50);
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.WHITE);
        textfield.setFont(new Font("Helvetica",Font.BOLD,30));
        textfield.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0,50,1100,100);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(Color.BLACK);
        textarea.setForeground(Color.WHITE);
        textarea.setFont(new Font("Helvetica",Font.BOLD,25));
        textarea.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        textarea.setEditable(false);
        buttonA.setBounds(0,150,100,100);
        buttonA.setFont(new Font("Helvetica",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A:");
        buttonA.setForeground(new Color(255,165,0));
        buttonA.setBackground(Color.BLACK);


        buttonB.setBounds(0,250,100,100);
        buttonB.setFont(new Font("Helvetica",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B:");
        buttonB.setForeground(new Color(255,165,0));
        buttonB.setBackground(Color.BLACK);


        buttonC.setBounds(550,150,100,100);
        buttonC.setFont(new Font("Helvetica",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("*C:");
        buttonC.setForeground(new Color(255,165,0));
        buttonC.setBackground(Color.BLACK);
        buttonD.setBounds(550,250,100,100);
        buttonD.setFont(new Font("Helvetica",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("*D:");
        buttonD.setForeground(new Color(255,165,0));
        buttonD.setBackground(Color.BLACK);


        answer_labelA.setBounds(125,150,500,100);
        answer_labelA.setBackground(Color.BLACK);
        answer_labelA.setForeground(Color.WHITE);
        answer_labelA.setFont(new Font("Helvetica",Font.PLAIN,25));


        answer_labelB.setBounds(125,250,500,100);
        answer_labelB.setBackground(Color.BLACK);
        answer_labelB.setForeground(Color.WHITE);
        answer_labelB.setFont(new Font("Helvetica",Font.PLAIN,25));


        answer_labelC.setBounds(675,150,500,100);
        answer_labelC.setBackground(Color.BLACK);
        answer_labelC.setForeground(Color.WHITE);
        answer_labelC.setFont(new Font("Helvetica",Font.PLAIN,25));
        answer_labelD.setBounds(675,250,500,100);
        answer_labelD.setBackground(Color.BLACK);
        answer_labelD.setForeground(Color.WHITE);
        answer_labelD.setFont(new Font("Helvetica",Font.PLAIN,25));

        seconds_left.setBounds(1050,0,50,50);
        seconds_left.setBackground(Color.BLACK);
        seconds_left.setForeground(Color.WHITE);
        seconds_left.setFont(new Font("Helvetica",Font.BOLD,25));
        seconds_left.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(955,0,100,25);
        time_label.setBackground(Color.BLACK);
        time_label.setForeground(Color.WHITE);
        time_label.setFont(new Font("Helvetica",Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Timer = ");

        number_right.setBounds(300,0,200,200);
        number_right.setBackground(Color.WHITE);
        number_right.setForeground(Color.BLACK);
        number_right.setFont(new Font("Helvetica",Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);
        percentage.setBounds(300,100,200,200);
        percentage.setBackground(Color.WHITE);
        percentage.setForeground(Color.BLACK);
        percentage.setFont(new Font("Helvetica",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        add(time_label);
        add(seconds_left);
        add(answer_labelA);
        add(answer_labelB);
        add(answer_labelC);
        add(answer_labelD);
        add(buttonA);
        add(buttonB);
        add(buttonC);
        add(buttonD);
        add(textarea);
        add(textfield);
        setVisible(true);


        nextQuestion();
    }
    public void nextQuestion() {

        if(index>=total_questions) {
            results();
        }
        else {

            textfield.setText("Fråga " +(index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);



        if(e.getSource()==buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }

        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }

        displayAnswer();
    }
    public void displayAnswer() {

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')
            answer_labelA.setForeground(Color.RED);
        if(answers[index] != 'B')
            answer_labelB.setForeground(Color.RED);
        if(answers[index] != 'C')
            answer_labelC.setForeground(Color.RED);
        if(answers[index] != 'D')
            answer_labelD.setForeground(Color.RED);

        Timer pause = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                answer_labelA.setForeground(Color.WHITE);
                answer_labelB.setForeground(Color.WHITE);
                answer_labelC.setForeground(Color.WHITE);
                answer_labelD.setForeground(Color.WHITE);

                answer = ' ';
                seconds = 60;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textfield.setText("RESULTS!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        add(number_right);
        add(percentage);

    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
    }
}