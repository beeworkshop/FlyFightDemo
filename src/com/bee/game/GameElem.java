package com.bee.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏物体的父类
 * 
 * @author beeworkshop
 *
 */
public class GameElem {
	protected Image img;
	protected double x, y;
	protected int speed = 5;
	protected int width, height;

	public void drawSelf(Graphics g) {
		g.drawImage(img, (int) x, (int) y, null);
	}

	public GameElem(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public GameElem() {

	}

	/**
	 * 
	 * @return 返回物体所在的矩形，便于后续的碰撞检测。
	 */
	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, width, height);
	}

}
