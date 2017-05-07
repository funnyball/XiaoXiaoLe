package yty.xxl;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class XXL extends JFrame {
	private Container panel;
	private static JButton jb[][] = new JButton[8][8];
	private static ButtonIndex bu[][] = new ButtonIndex[8][8];
	private static int px = -1;
	private static int py = -1;
	private static JLabel jl = new JLabel(new ImageIcon("�ƶ���.png"));
	private static JLabel jll = new JLabel(new ImageIcon("ѡ����.png"));
	private int count = 0;
	JLabel countxiao = new JLabel("<html>��ϲ��<br>������<br>" + count + "��</html>");

	public XXL() {
		this.setJFrame();
		this.setButton();
		this.setJiLu();
		this.setMenu();
		this.setVisible(true);
		this.xiaoquanju();
	}

	private void setMenu() {
		MenuItem m1 = new MenuItem("���¿�ʼ");
		MenuItem m3 = new MenuItem("�˳���Ϸ");
		Menu m = new Menu("���ǲ˵�");
		m.add(m1);
		m.add(m3);
		MenuBar mb = new MenuBar();
		mb.add(m);
		this.setMenuBar(mb);
		m1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				App.x.setVisible(false);
				App.x.dispose();
				new App();
			}
		});
		m3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void setJiLu() {
		countxiao.setBounds(430, 0, 100, 300);
		countxiao.setFont(new Font("", Font.PLAIN, 30));
		countxiao.setForeground(Color.RED);
		this.panel.add(countxiao);
		JButton exit = new JButton("�˳�");
		exit.setBounds(420, 350, 130, 50);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.panel.add(exit);
	}

	private void buquan() {
		this.update(this.getGraphics());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				if ("0.png".equals(jb[a][b].getIcon().toString())) {
					jb[a][b].setIcon(TuPian.getcolor());
				}
			}
		}
		xiaoquanju();
	}

	private void xiaoquanju() {
		Boolean[][] boo = new Boolean[8][8];
		Boolean ifBuQuan = false;
		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {

				Boolean flag = ifExchange(a, b);
				boo[a][b] = flag;
				xiaojubu(a, b);
			}
		}
		c: for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				if (boo[a][b]) {
					ifBuQuan = true;
					break c;
				}
			}
		}
		if (ifBuQuan) {
			this.update(this.getGraphics());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int a = 0; a < 8; a++) {
				for (int b = 0; b < 8; b++) {
					if ("0.png".equals(jb[a][b].getIcon().toString())) {
						diaoluo(a, b);
					}
				}
			}
			buquan();
		} else {
			this.update(this.getGraphics());
		}
	}

	private void setButton() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				jb[x][y] = new JButton(TuPian.getcolor());
				jb[x][y].setBounds(50 * x + 5, 50 * y + 5, 40, 40);
				jb[x][y].setBackground(Color.WHITE);
				this.panel.add(jb[x][y]);
				bu[x][y] = new ButtonIndex(x, y);

				final ButtonIndex b = bu[x][y];
				// �����а�ť����洢

				jb[x][y].addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						jl.setBounds(50 * b.getX(), 50 * b.getY(), 50, 50);
						jl.setVisible(true);
					}

					public void mouseExited(MouseEvent e) {
						jl.setBounds(50 * b.getX(), 50 * b.getY(), 50, 50);
						jl.setVisible(false);
					}

					public void mouseClicked(MouseEvent e) {
						jll.setBounds(50 * b.getX(), 50 * b.getY(), 50, 50);
						jll.setVisible(true);
						Boolean flag = jiaohuan(b);
						if (flag) {
							xiaoquanju();
							px = -1;
							py = -1;

						} else {
							px = b.getX();
							py = b.getY();
						}
					}
				});
			}
		}
	}

	protected void xiaojubu(int x, int y) {
		int right = 1;
		int down = 1;
		String color = jb[x][y].getIcon().toString();
		if ("0.png".equals(color)) {
			return;
		}
		// ��Ϊ��һ��һ������������ֻҪ�жϸð�ťͼƬ����������Ƿ���ͬ����
		// ���ж�����
		while (x + right <= 7
				&& color.equals(jb[x + right][y].getIcon().toString())) {
			right++;
		}
		if (right >= 3) {
			for (int a = 0; a < right; a++) {
				jb[x + a][y].setBackground(Color.ORANGE);
			}
			this.update(this.getGraphics());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int a = 0; a < right; a++) {
				xiao(x + a, y);
			}
			count++;
			countxiao.setText("<html>��ϲ��<br>������<br>" + count + "��</html>");
			for (int a = 0; a < right; a++) {
				jb[x + a][y].setBackground(Color.WHITE);
			}
		}

		// ���ж�����
		while (y + down <= 7
				&& color.equals(jb[x][y + down].getIcon().toString())) {
			down++;
		}
		if (down >= 3) {
			for (int a = 0; a < down; a++) {
				jb[x][y + a].setBackground(Color.ORANGE);
			}
			this.update(this.getGraphics());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int a = 0; a < down; a++) {
				xiao(x, y + a);
			}
			count++;
			countxiao.setText("<html>��ϲ��<br>������<br>" + count + "��</html>");
			for (int a = 0; a < down; a++) {
				jb[x][y + a].setBackground(Color.WHITE);
			}
		}
	}

	private void diaoluo(int x, int y) {
		// ��ȡǰ���ͼƬ���Լ�����
		if (y == 0) {
			return;
		}
		for (int b = y; b > 0; b--) {
			String color = jb[x][b - 1].getIcon().toString();
			jb[x][b].setIcon(TuPian.getcolor(color));
			//System.out.println("��" + b + "��" + "��õ�" + (b - 1) + "�� ��" + color);
		}
		jb[x][0].setIcon(new ImageIcon("0.png"));
	}

	private void xiao(int x, int y) {
		if (!jb[x][y].getIcon().toString().equals("0.png")) {
			//System.out.println("����" + x + "==" + y + "  "
					//+ jb[x][y].getIcon().toString());
			jb[x][y].setIcon(new ImageIcon("0.png"));
		}
	}

	protected Boolean jiaohuan(ButtonIndex b) {
		if (px == b.getX()) {
			if (py == b.getY()) {
				return false;
			}
		}
		if (px != -1 && py != -1) {
			// ֻ�ܺ����ڰ�ť����
			if (Math.abs(px - b.getX()) == 1 && py - b.getY() == 0
					|| Math.abs(py - b.getY()) == 1 && px - b.getX() == 0) {

				// ��ɫ����������
				String color1 = jb[px][py].getIcon().toString();
				String color2 = jb[b.getX()][b.getY()].getIcon().toString();
				// �����ɫ��ȾͲ�����
				if (color1.equals(color2)) {
					return false;
				}
				//System.out.println(color1 + "=" + color2);
				jb[px][py].setIcon(TuPian.getcolor(color2));
				jb[b.getX()][b.getY()].setIcon(TuPian.getcolor(color1));
				// �������������������ô����false
				Boolean flag1 = ifExchange(px, py);
				Boolean flag2 = ifExchange(b.getX(), b.getY());
				if (!flag1 && !flag2) {
					jb[px][py].setIcon(TuPian.getcolor(color1));
					jb[b.getX()][b.getY()].setIcon(TuPian.getcolor(color2));
					return false;
				}
				return true;
			}
		}
		return false;
	}

	private Boolean ifExchange(int x, int y) {
		String color = jb[x][y].getIcon().toString();
		// ���ж�������ڲ���Ȼ����������ɫ
		if (x - 1 >= 0 && color.equals(jb[x - 1][y].getIcon().toString())) {
			// ������ɫ��ͬ���ٿ�������ɫ��ͬ��
			if (x - 1 - 1 >= 0
					&& color.equals(jb[x - 1 - 1][y].getIcon().toString())) {
				return true;
			}
			// ������ɫ��ͬ��������ɫ��ͬ���ٿ�������ɫ��ͬ��
			else if (x + 1 <= 7
					&& color.equals(jb[x + 1][y].getIcon().toString())) {
				return true;
			}
		}
		// �ж������棬�ٿ�����
		else if (x + 1 <= 7 && color.equals(jb[x + 1][y].getIcon().toString())) {
			if (x + 1 + 1 <= 7
					&& color.equals(jb[x + 1 + 1][y].getIcon().toString())) {
				return true;
			}
		}

		// ����ͬ�����ж�
		// ���ж���
		if (y - 1 >= 0 && color.equals(jb[x][y - 1].getIcon().toString())) {
			if (y - 1 - 1 >= 0
					&& color.equals(jb[x][y - 1 - 1].getIcon().toString())) {
				return true;
			} else if (y + 1 <= 7
					&& color.equals(jb[x][y + 1].getIcon().toString())) {
				return true;
			}
		} else if (y + 1 <= 7
				&& color.equals(jb[x][y + 1].getIcon().toString())) {
			if (y + 1 + 1 <= 7
					&& color.equals(jb[x][y + 1 + 1].getIcon().toString())) {
				return true;
			}
		}
		return false;
	}

	private void setJFrame() {
		this.panel = this.getContentPane();
		this.setSize(580, 480);
		this.setTitle("�ҵ�������");
		// �м�
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		// ���ǹر��¼�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel.setLayout(null);
		this.panel.add(jl);
		this.panel.add(jll);
	}
}
