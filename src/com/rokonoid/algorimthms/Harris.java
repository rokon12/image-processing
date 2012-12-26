package com.rokonoid.algorimthms;

import java.awt.Color;

public class Harris {

	private static int[] input;
	private static int[] output;
	private int width;
	private int height;
	private int convolveX[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	private int convolveY[] = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	private int templateSize = 3;
	private double k = 0.04;
	private double threshold = 10000000;
	
	public void init(int[] inputIn, int widthIn, int heightIn, double k) {
		width = widthIn;
		height = heightIn;
		input = inputIn;
		output = new int[width * height];

		this.k = k < 0 ? .04 : k;
	}

	public int[] process() {	
		int iX[] = new int[width * height];
		int iY[] = new int[width * height];
		int iXY[] = new int[width * height];

		partialDerivative(iX, iY, iXY);

		GaussianFilter g = new GaussianFilter();
		iX = gFilter(iX, g);
		iY = gFilter(iY, g);
		iXY = gFilter(iXY, g);

		// now perform "harris corner response" function

		double[] cornerResponse = convolute(iX, iY, iXY);

		// do non-max suppression on the hcr
		computeCornerness(cornerResponse);

		return output;
	}

	private void computeCornerness(double[] cornerResponse) {
		Nonmax nmOp = new Nonmax();
		nmOp.init(cornerResponse, width, height);
		double nm[] = nmOp.process();

		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				int val;
				if ((nm[y * width + x]) == 0)
					val = ((input[y * width + x] & 0xff) + 255) >> 1;
				else {
					val = Color.WHITE.getRGB();
				}
				output[y * width + x] =
					0xff000000 | ((int) (val) << 16 | (int) (val) << 8 | (int) (val));
			}
		}
	}

	private double[] convolute(int[] iX, int[] iY, int[] iXY) {
		double cornerResponse[] = new double[width * height];
		double val = 0;
		double A, B, C;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				A = (iX[y * width + x]);
				B = (iY[y * width + x]);
				C = (iXY[y * width + x]);
				val = ((A * B - (C * C)) - (k * ((A + B) * (A + B))));

				if (val > threshold) {
					cornerResponse[y * width + x] = val;
				} else {
					cornerResponse[y * width + x] = 0;
				}
			}
		}
		return cornerResponse;
	}

	private int[] gFilter(int[] diffx, GaussianFilter g) {
		g.init(diffx, 2, 12, width, height);
		g.generateTemplate();
		diffx = g.process();
		return diffx;
	}

	private void partialDerivative(int[] diffx, int[] diffy, int[] diffxy) {
		for (int x = templateSize / 2; x < width - (templateSize / 2); x++) {
			for (int y = templateSize / 2; y < height - (templateSize / 2); y++) {
				int valx = 0;
				int valy = 0;
				for (int x1 = 0; x1 < templateSize; x1++) {
					for (int y1 = 0; y1 < templateSize; y1++) {
						int pos = (y1 * templateSize + x1);
						int imPos =
							(x + (x1 - (templateSize / 2)))
								+ ((y + (y1 - (templateSize / 2))) * width);

						valx += ((input[imPos] & 0xff) * convolveX[pos]);
						valy += ((input[imPos] & 0xff) * convolveY[pos]);
					}
				}
				diffx[x + (y * width)] = valx * valx;
				diffy[x + (y * width)] = valy * valy;
				diffxy[x + (y * width)] = valx * valy;
			}
		}
	}
}
