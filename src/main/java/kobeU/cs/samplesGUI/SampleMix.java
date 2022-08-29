package kobeU.cs.samplesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SampleMix extends JFrame {

    private final JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // FileChooser の見た目を改善したい
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SampleMix frame = new SampleMix();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 画像のセットアップ
	ArrayList<BufferedImage> cardImages = new ArrayList<>();
	private final SampleDrawPanel sampleDrawPanel;

	private void imageSetup() {
		File dir = new File(SampleMix.class.getResource("/img/cards/").getFile());
		for(File file: dir.listFiles()) {
			try { // 画像としての取り込みを計り
				BufferedImage img = ImageIO.read(file);
				if(img != null) cardImages.add(img);
			} catch (IOException e) { // 失敗したら、その旨表示。
				System.err.println("Can't read image: " + file);
			}
		}
	}
	
	// アプリ画面（sampleDrawPanel）をファイルに保存
	void saveImg() {
		// ファイル選択 (詳しい使い方は調べてみよう)
		JFileChooser chooser = new JFileChooser();
		// 拡張子指定したいなら
		FileNameExtensionFilter filter = new FileNameExtensionFilter("png image", "png");
		chooser.setFileFilter(filter);
		while(true) {
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal != JFileChooser.APPROVE_OPTION) {
				// ファイル選択しなかった場合
				return;
			}
			File file = chooser.getSelectedFile();
			if(file.getName().endsWith(".png")) {
				// ここで保存
			    sampleDrawPanel.saveRenderedImage(file);
			    return;
			}
		}
	}


    /**
     * Create the frame.
     */
    public SampleMix() {
        setPreferredSize(new Dimension(500, 300));
        setTitle("SoftDev Sample Mix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1123, 957);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnDownload = new JMenu("download");
        mnDownload.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		saveImg();
        	}
        });
        menuBar.add(mnDownload);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel setupPanel = new JPanel();
        contentPane.add(setupPanel, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		saveImg();
        	}
        });
        btnNewButton.setIcon(new ImageIcon(SampleMix.class.getResource("/img/button_download2.png")));
        setupPanel.add(btnNewButton);

        sampleDrawPanel = new SampleDrawPanel();
        contentPane.add(sampleDrawPanel, BorderLayout.CENTER);

        // 以下は手書き
        // sampleDrawPanel に画像ファイル指定
        imageSetup();
        sampleDrawPanel.setImages(cardImages);

    }

}
