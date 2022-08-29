package kobeU.cs.samplesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaintTool extends JFrame {

    private final JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PaintTool frame = new PaintTool();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PaintTool() {
        setPreferredSize(new Dimension(500, 300));
        setTitle("SoftDev Paint Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        CustomView customView = new CustomView();
        customView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                customView.mousePressed(e.getPoint());
            }
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
        contentPane.add(customView, BorderLayout.CENTER);

        JPanel setupPanel = new JPanel();
        contentPane.add(setupPanel, BorderLayout.SOUTH);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customView.reset();
            }
        });
        setupPanel.add(btnReset);

        JButton btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customView.undo();
            }
        });
        setupPanel.add(btnUndo);
    }


}
