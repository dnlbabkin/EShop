import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;

public class Interface extends JFrame {
    private static final long serialVersionUID = 1L;
    private static JButton button;
    private static JLabel label;
    Container container = this.getContentPane();
    Container container2 = this.getContentPane();

    public Interface() {
        super("Интернет - магазин");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createReferenceMenu());

        container.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        label = new JLabel("Три бесплатных доставки в месяц!");
        button = new JButton("Подробнее");
        container.add(label);
        container.add(button);


        setJMenuBar(menuBar);
        setSize(800, 600);
        setVisible(true);
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
