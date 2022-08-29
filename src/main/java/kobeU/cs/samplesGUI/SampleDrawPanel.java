package kobeU.cs.samplesGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SampleDrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	List<BufferedImage> images = new ArrayList<>();
	public List<BufferedImage> getImages() {
		return images;
	}
	public void setImages(List<BufferedImage> images) {
		this.images = images;
		repaint();
	}

	public SampleDrawPanel()  {
		setSize(new Dimension(500, 300));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight()); // 画面クリア
		g.setColor(Color.black); // 色を黒にして
		int x = 0; int y = 0;
		boolean flag = false;
		while(x < getWidth()-100) {         // x軸方向に円を10個書く
			if(flag) g.fillOval(x, y, 50, 50);
			else g.drawOval(x, y, 50, 50);
			x+=50; flag = !flag;
		}
		g.setColor(Color.red);  // 赤色で
		g.drawLine(0, 60, getWidth()-10, 60); // 線を引く
		g.setColor(Color.blue); // 青色で
		g.fillRect(50, 70, getWidth()-100, 10); // 長方形を塗る
		// font 指定
		g.setFont(new Font("SansSerif", Font.ITALIC, 30));
		g.drawString("Hello World!", 10, 110); // 文字を書く
		x = 0;
		y = 120;
		for(BufferedImage img: images) { // 画像image を表示
			g.drawImage(img, x, y, 80, 120, null);
			x+=100;
		}
	}

	/**
	 * 描画内容を、指定されたファイルに png format で出力を試みる。
	 * @param file 出力先ファイル
	 */
	public void saveRenderedImage(File file) {
		BufferedImage outputImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = outputImage.createGraphics();
		g.setBackground(Color.white);
		paintComponent(g);
		try {
			ImageIO.write(outputImage, "png", file);
		} catch (IOException e) {
			System.err.println("Can't write file: " + file);
			e.printStackTrace();
		}
	}
}
