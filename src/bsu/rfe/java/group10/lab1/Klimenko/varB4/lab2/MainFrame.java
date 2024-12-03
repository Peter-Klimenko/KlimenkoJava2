package bsu.rfe.java.group10.lab1.Klimenko.varB4.lab2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	private JTextField xField, yField, zField, resultField;
    private JRadioButton function1Button, function2Button;
    private JRadioButton var1Button, var2Button, var3Button;
    private Double mem1 = null, mem2 = null, mem3 = null;
    private double activeMemory = 0.0;

    public MainFrame() {
        setTitle("Калькулятор для функций");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        xField = new JTextField(10);
        yField = new JTextField(10);
        zField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("x:"));
        inputPanel.add(xField);
        inputPanel.add(new JLabel("y:"));
        inputPanel.add(yField);
        inputPanel.add(new JLabel("z:"));
        inputPanel.add(zField);
        add(inputPanel);


        function1Button = new JRadioButton("Формула 1");
        function2Button = new JRadioButton("Формула 2");
        ButtonGroup functionGroup = new ButtonGroup();
        functionGroup.add(function1Button);
        functionGroup.add(function2Button);
        JPanel functionPanel = new JPanel();
        functionPanel.add(new JLabel("Выбирите функцию:"));
        functionPanel.add(function1Button);
        functionPanel.add(function2Button);
        add(functionPanel);

        var1Button = new JRadioButton("Переменная 1");
        var2Button = new JRadioButton("Переменная 2");
        var3Button = new JRadioButton("Переменная 3");
        ButtonGroup varGroup = new ButtonGroup();
        varGroup.add(var1Button);
        varGroup.add(var2Button);
        varGroup.add(var3Button);
        JPanel varPanel = new JPanel();
        varPanel.add(new JLabel("Активная переменная:"));
        varPanel.add(var1Button);
        varPanel.add(var2Button);
        varPanel.add(var3Button);
        add(varPanel);

        resultField = new JTextField(15);
        resultField.setEditable(false);
        JButton calculateButton = new JButton("Вычислить");
        calculateButton.addActionListener(new CalculateButtonListener());
        JPanel resultPanel = new JPanel();
        resultPanel.add(calculateButton);
        resultPanel.add(resultField);
        add(resultPanel);

        JButton mcButton = new JButton("MC");
        JButton mPlusButton = new JButton("M+");
        mcButton.addActionListener(new MemoryClearButtonListener());
        mPlusButton.addActionListener(new MemoryAddButtonListener());
        JPanel memoryPanel = new JPanel();
        memoryPanel.add(mcButton);
        memoryPanel.add(mPlusButton);
        add(memoryPanel);
        
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
            	if (mem1 == null)
            	{
            	mem1 = Double.parseDouble(xField.getText());
            	mem2 = Double.parseDouble(yField.getText());
            	mem3 = Double.parseDouble(zField.getText());
            	}
                double result = 0;
                if (function1Button.isSelected()) {
                    result = calculateFormula1();
                } else if (function2Button.isSelected()) {
                    result = calculateFormula2();
                }

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Необходимо ввести все значения(x, y, z).");
            }
        }
    }

    private class MemoryClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (var1Button.isSelected()) {
                mem1 = 0.0;
            } else if (var2Button.isSelected()) {
                mem2 = 0.0;
            } else if (var3Button.isSelected()) {
                mem3 = 0.0;
            }
            updateResultFieldWithMemory();
        }
    }

    private class MemoryAddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double result = Double.parseDouble(resultField.getText());
            if (var1Button.isSelected()) {
                mem1 += result;
            } else if (var2Button.isSelected()) {
                mem2 += result;
            } else if (var3Button.isSelected()) {
                mem3 += result;
            }
            updateResultFieldWithMemory();
        }
    }

    private void updateResultFieldWithMemory() {
        if (var1Button.isSelected()) {
            resultField.setText(String.valueOf(mem1));
        } else if (var2Button.isSelected()) {
            resultField.setText(String.valueOf(mem2));
        } else if (var3Button.isSelected()) {
            resultField.setText(String.valueOf(mem3));
        }
    }

    private double calculateFormula1() {
    	double x = mem1 ;
    	double y = mem2 ;
    	double z = mem3 ;
    	if (x==0)
    	{
    		 JOptionPane.showMessageDialog(null, "Нe возможно вычеслить Функцию (x=0).");
    		 SwingUtilities.invokeLater(MainFrame::new);
    	}
        return Math.sin(Math.sin(y + Math.pow(Math.E, Math.cos(y))) + Math.pow(z, 2)) * 
               Math.pow(Math.sqrt(Math.sin(Math.PI * Math.pow(y, 2)) + Math.log(Math.pow(x, 2))), 0.25);
    }

    private double calculateFormula2() {
    	double x = mem1 ;
    	double y = mem2 ;
    	double z = mem3 ;
    	if (z==0)
    	{
    		 JOptionPane.showMessageDialog(null, "Нe возможно вычеслить Функцию (z=0).");
    		 SwingUtilities.invokeLater(MainFrame::new);
    	}
        return Math.pow(x, x) / (Math.sqrt(Math.pow(y, 3)) + 1 + Math.log(z));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
