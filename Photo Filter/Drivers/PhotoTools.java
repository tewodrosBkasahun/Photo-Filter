package Drivers;

import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import PhotoLibrary.Photograph;
import PhotoLibrary.Pixel;

/**
 * This class will be written by the Student. It provides various static methods
 * that take a photograph and produce a copy of it with various modifications.
 * 
 * @author Tewodros Kasahun
 *
 */
public class PhotoTools {
	/**
	 * This method will copy the picture in the box
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph copy(Photograph photo) {
		Photograph copyphoto = new Photograph(photo.getWd(), photo.getHt());
		for (int c = 0; c < photo.getWd(); c++) {
			for (int r = 0; r < photo.getHt(); r++) {
				copyphoto.setPixel(c, r, photo.getPixel(c, r));
			}
		}
		return copyphoto;
	}

	/**
	 * This method will turn the selected picture to gray scale
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph makeGrayscale(Photograph photo) {
		Photograph toGray = copy(photo);

		for (int c = 0; c < photo.getWd(); c++) {
			for (int r = 0; r < photo.getHt(); r++) {
				Pixel pix = photo.getPixel(c, r);
				int ave = ((pix.getGreen() + pix.getGreen() + pix.getRed()) / 3);
				Pixel changColor = new Pixel(ave, ave, ave);
				toGray.setPixel(c, r, changColor);
			}
		}
		return toGray;
	}

	/**
	 * THis will apply the striped filter
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph striped(Photograph photo) {
		Photograph strip = copy(photo);

		for (int r = 0; r < photo.getWd(); r++) {

			for (int c = 0; c < photo.getHt(); c++) {
				Pixel pix = photo.getPixel(r, c);
				if (c / 10 == 0 || c / 10 == 3 || c / 10 == 6 || c / 10 == 9 || c / 10 == 12 || c / 10 == 15
						|| c / 10 == 18 || c / 10 == 21 || c / 10 == 24 || c / 10 == 27) {
					strip.setPixel(r, c, pix);
				} else if (c / 10 == 1 || c / 10 == 4 || c / 10 == 7 || c / 10 == 10 || c / 10 == 13 || c / 10 == 16
						|| c / 10 == 19 || c / 10 == 22 || c / 10 == 25 || c / 10 == 28) {
					Pixel white = new Pixel(225, 225, 225);
					strip.setPixel(r, c, white);
				} else if (c / 10 == 2 || c / 10 == 5 || c / 10 == 8 || c / 10 == 11 || c / 10 == 14 || c / 10 == 17
						|| c / 10 == 20 || c / 10 == 23 || c / 10 == 26 || c / 10 == 29) {
					Pixel black = new Pixel(0, 0, 0);
					strip.setPixel(r, c, black);

				}
			}
		}
		return strip;
	}

	/**
	 * 
	 * @param photo
	 * @param type
	 * @return
	 */
	public static Photograph isolateColor(Photograph photo, int type) {
		Photograph isolatphoeColor = copy(photo);
		if (type == 1) {
			for (int c = 0; c < photo.getWd(); c++) {
				for (int r = 0; r < photo.getHt(); r++) {
					Pixel pix = photo.getPixel(c, r);
					if (pix.getBlue() > 0 && pix.getRed() > 0) {
						Pixel changColor = new Pixel(0, pix.getGreen(), 0);
						isolatphoeColor.setPixel(c, r, changColor);
					}
				}
			}
		} else if (type == 0) {
			for (int c = 0; c < photo.getWd(); c++) {
				for (int r = 0; r < photo.getHt(); r++) {
					Pixel pix = photo.getPixel(c, r);
					if (pix.getBlue() > 0 && pix.getGreen() > 0) {
						Pixel changColor = new Pixel(pix.getRed(), 0, 0);
						isolatphoeColor.setPixel(c, r, changColor);
					}
				}
			}

		} else if (type == 2) {
			for (int c = 0; c < photo.getWd(); c++) {
				for (int r = 0; r < photo.getHt(); r++) {
					Pixel pix = photo.getPixel(c, r);
					if (pix.getRed() > 0 && pix.getGreen() > 0) {
						Pixel changColor = new Pixel(0, 0, pix.getBlue());
						isolatphoeColor.setPixel(c, r, changColor);
					} else {

					}
				}
			}
		}
		return isolatphoeColor;
	}

	/**
	 * This method will just grab one single row of pixels or a line of pixels and
	 * stretch it
	 * 
	 * @param photo
	 * @param type
	 * @return
	 */
	public static Photograph stretched(Photograph photo, int type) {
		Photograph stretched1 = new Photograph(photo.getWd() * 2, photo.getHt());
		if (type == 0) {

			for (int c = 0; c < photo.getWd(); c++) {
				for (int r = 0; r < photo.getHt(); r++) {
					stretched1.setPixel(c + c, r, photo.getPixel(c, r));
					stretched1.setPixel(c + c + 1, r, photo.getPixel(c, r));

				}
			}
		}
		return stretched1;
	}

	/**
	 * This method will turn the right half of the image into a mirror reflection of
	 * the left half
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph mirrored(Photograph photo) {
		Photograph mirrored = new Photograph(photo.getWd(), photo.getHt());
		for (int r = 0; r < photo.getWd(); r++) {
			for (int c = 0; c < photo.getHt(); c++) {

				mirrored.setPixel(photo.getWd() - r - 1, c, photo.getPixel(r, c));
			}
		}
		return mirrored;
	}

	/**
	 * This method will rotate picture 90 degrees
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph rotated(Photograph photo) {
		int m = photo.getHt();
		int n = photo.getWd();
		Photograph rotated = new Photograph(m, n);
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				rotated.setPixel(m - c - 1, n - r - 1, photo.getPixel(r, c));
			}
		}
		return rotated;
	}

	/**
	 * This method will turn the picture upside down
	 * 
	 * @param photo
	 * @return
	 */
	public static Photograph upsideDown(Photograph photo) {
		Photograph upsideDown = new Photograph(photo.getWd(), photo.getHt());
		for (int c = 0; c < photo.getWd(); c++) {
			for (int r = 0; r < photo.getHt(); r++) {
				upsideDown.setPixel(photo.getWd() - c - 1, photo.getHt() - r - 1, photo.getPixel(c, r));
			}
		}
		return upsideDown;
	}

}
