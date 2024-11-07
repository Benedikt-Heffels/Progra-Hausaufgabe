package T_Circle;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

    private Circle circle;

    public MyCanvas(Circle circle) {
        this.setPreferredSize(new Dimension(300, 230));
        this.circle = circle;
    }

    @Override
    public void paint(Graphics g) {
        g.drawOval(circle.getX() - circle.getRadius() / 2,
                circle.getY() - circle.getRadius() / 2,
                circle.getRadius(),
                circle.getRadius());
    }
}

@SuppressWarnings("serial")
class MyButton extends JButton {

    MyButton(String label) {
        super(label);
        setPreferredSize(new Dimension(120, 30));
    }
}

abstract class ClickListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

abstract class KeyPressedListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

public class XCircle {

    private XCircle(){}

    public static void main(String[] a) {
        final Circle circle = new Circle(150, 115, 50);
        final JFrame window = new JFrame();
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(300, 300, 300, 300);

        JComponent canvas = new MyCanvas(circle);
        window.getContentPane().add(canvas);

        JButton bigger = addButton(window, circle, "Bigger!", CircleAction.BIGGER);
        JButton narrower = addButton(window, circle, "Smaller!", CircleAction.SMALLER);

        KeyListener kl = new KeyPressedListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        circle.performAction(CircleAction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        circle.performAction(CircleAction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        circle.performAction(CircleAction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        circle.performAction(CircleAction.RIGHT);
                        break;
                    default:
                        return;
                }
                window.repaint();
            }
        };
        for (JComponent c : new JComponent[] { canvas, bigger, narrower }) {
            c.addKeyListener(kl);
        }

        window.setVisible(true);
    }

    private static JButton addButton(final JFrame window, final Circle circle, final String label, final CircleAction action) {
        JButton button = new MyButton(label);
        button.addMouseListener(new ClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                circle.performAction(action);
                window.repaint();
            }
        });
        window.getContentPane().add(button);
        return button;
    }

}
