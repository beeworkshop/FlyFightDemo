package com.bee.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameElem {

	double degree;

	public Bullet() {
		x = 200;
		y = 200;
		width = 10;
		height = 10;
		speed = 3;
		degree = Math.random() * Math.PI * 2;
	}

	public void drawBullet(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int) x, (int) y, width, height);

		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		if (x < width || x > Constant.FRAME_WIDTH - width * 2) {
			degree = Math.PI - degree;
		}
		if (y < height * 4 || y > Constant.FRAME_HEIGHT - height * 3) {
			degree = -degree;
		}

		g.setColor(color);
	}
}
