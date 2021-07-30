import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;

public class Interface extends JFrame {
    private static final long serialVersionUID = 1L;
    JTextField FIOField, TelField, EField;
    JButton Ok, Cancel;
    public String[] elements = new String[] {"Товар №1", "Товар №2", "Товар №3", "Товар №4", "Товар №5"};
    private JComboBox<String> cbFirst;
    private DefaultComboBoxModel<String> cbModel;

    public Interface() {
        super("Интернет - магазин");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createAboutMenu());
        menuBar.add(createReferenceMenu());
        setJMenuBar(menuBar);

        cbModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < elements.length; i++)
            cbModel.addElement((String)elements[i]);

        Vector<String> data = new Vector<String>();

        for (int i = 0; i < 10; i++)
            data.add(String.format("%d категория товара", i));

        cbFirst = new JComboBox<String>(cbModel);
        cbModel.setSelectedItem("Необходимый товар");
        int idx = cbModel.getIndexOf(cbModel.getSelectedItem());
        cbModel.setSelectedItem("Необходимый товар");
        cbFirst.setPrototypeDisplayValue("Максимальный размер");
        JComboBox<String> cbSecond = new JComboBox<String>(data);
        cbSecond.setEditable(true);
        cbSecond.setMaximumRowCount(5);
        JButton btnAdd = new JButton("Добавить в корзину");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(FileWriter writer = new FileWriter("shopping.txt", false)) {
                    PrintWriter wr = new PrintWriter(writer);

                    wr.println(cbFirst.toString());

                    wr.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        JPanel contents = new JPanel();
        contents.add(cbSecond);
        contents.add(cbFirst);
        contents.add(btnAdd);
        setContentPane(contents);

        setSize(800, 600);
        setVisible(true);
    }

    private void catalog(){
        cbModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < elements.length; i++)
            cbModel.addElement((String)elements[i]);

        Vector<String> data = new Vector<String>();

        for (int i = 0; i < 10; i++)
            data.add(String.format("#%d категория товара", i));

        cbFirst = new JComboBox<String>(cbModel);
        cbModel.setSelectedItem("Необходимый товар");
        int idx = cbModel.getIndexOf(cbModel.getSelectedItem());
        cbModel.removeElementAt(idx);
        cbModel.insertElementAt("Необходимый товар", idx);
        cbModel.setSelectedItem("Необходимый товар");
        cbFirst.setPrototypeDisplayValue("Максимальный размер");
        JComboBox<String> cbSecond = new JComboBox<String>(data);
        cbSecond.setEditable(true);
        cbSecond.setMaximumRowCount(5);
        JButton btnAdd = new JButton("Добавить в корзину");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(FileWriter writer = new FileWriter("shopping.txt", false)) {
                    PrintWriter wr = new PrintWriter(writer);

                    wr.println(FIOField.getText());
                    wr.println(TelField.getText());
                    wr.println(EField.getText());

                    wr.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        JPanel contents = new JPanel();
        contents.add(cbSecond);
        contents.add(cbFirst);
        contents.add(btnAdd);
        setContentPane(contents);
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("Корзина");
        JMenuItem open = new JMenuItem("Открыть", new ImageIcon("shopping.png"));
        JMenuItem exit = new JMenuItem(new ExitAction());
        exit.setIcon(new ImageIcon("exit.png"));
        menu.add(open);
        menu.addSeparator();
        menu.add(exit);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionListener.actionPerformed: open");
            }
        });

        return menu;
    }

    private JMenu createAboutMenu() {
        JMenu jMenu = new JMenu("Информация о покупателе");
        JButton about = new JButton("Данные о покупателе");

        jMenu.add(about);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FIOField = new JTextField("ФИО", 25);
                FIOField.setToolTipText("Длиное поле");
                FIOField.setHorizontalAlignment(JTextField.LEFT);
                TelField = new JTextField("Телефон", 25);
                TelField.setToolTipText("Длиное поле");
                TelField.setHorizontalAlignment(JTextField.LEFT);
                EField = new JTextField("Email", 25);
                EField.setToolTipText("Длиное поле");
                EField.setHorizontalAlignment(JTextField.LEFT);

                JPanel flow = new JPanel( new FlowLayout( FlowLayout.RIGHT, 0, 0) );
                JPanel grid = new JPanel( new GridLayout( 1,2,5,0) );
                Ok = new JButton("OK");
                Ok.addActionListener(new ButtonAction());
                Cancel = new JButton("Отмена");
                grid.add(Ok);
                grid.add(Cancel);
                grid.add(flow);

                JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
                contents.add(FIOField);
                contents.add(TelField);
                contents.add(EField);
                contents.add(Ok);
                contents.add(Cancel);
                setContentPane(contents);

                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
            }
        });

        return jMenu;
    }

    class ButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try(FileWriter writer = new FileWriter("info.txt", false)) {
                PrintWriter wr = new PrintWriter(writer);

                wr.println(FIOField.getText());
                wr.println(TelField.getText());
                wr.println(EField.getText());

                wr.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private JMenu createReferenceMenu() {
        JMenu jMenu = new JMenu("Справка");
        JMenuItem about = new JMenuItem("О программе\n");

        jMenu.add(about);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                message += about.getText() + "Графический интерфейс пользователя интернет - магазин. Учебная практика";
                JOptionPane.showMessageDialog(null, message, "О программе", JOptionPane.PLAIN_MESSAGE);
            }
        });

        return jMenu;
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
