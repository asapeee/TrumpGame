package kobeU.cs.samplesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintTool2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PaintTool2");
        frame.setContentPane(new PaintTool2().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel contentPane;
    private JButton btnUndo;
    private CustomView customView;
    private JButton btnReset;

    public PaintTool2() {
        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customView.undo();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customView.reset();
            }
        });
        customView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                customView.mousePressed(e.getPoint());
            }
        });
        customView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                customView.mouseReleased(e.getPoint());
            }
        });
        customView.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                customView.mouseDragged(e.getPoint());
            }
        });
    }

}
