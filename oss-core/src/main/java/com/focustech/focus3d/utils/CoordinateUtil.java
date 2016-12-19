/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.focus3d.utils;

import com.focustech.common.utils.TCUtil;


/**
 * CoordinateUtil.java
 *
 * @author chenyang
 */
public class CoordinateUtil {
	public static String ROTATE_BY_X = "RotateByX";
	public static String ROTATE_BY_Y = "RotateByY";
	public static String ROTATE_BY_Z = "RotateByZ";

	/**
	 * 对矩阵旋转操作
	 *
	 * @param matrix
	 *            原来的矩阵
	 * @param angle
	 *            角度,int型
	 * @param axis
	 *            根据X或者Y或者Z来旋转
	 * @return String 坐标字符串
	 *         Rotate("1,0,0,0,0,1,0,0,0,0,1,0,-2.173645,1.042787,-0.025155,1"
	 *         ,90,ROTATE_BY_Z)
	 */
	public static String Rotate(String matrix, String angle, String axis) {
		String[] matrixs = matrix.split(",");

		Double radian = Math.toRadians(TCUtil.dv(angle));
		if (axis.equals(ROTATE_BY_X)) {
			return RotateX(matrixs, radian);
		} else if (axis.equals(ROTATE_BY_Y)) {
			return RotateY(matrixs, radian);
		} else if (axis.equals(ROTATE_BY_Z)) {
			return RotateZ(matrixs, radian);
		}
		return "";
	}

	/**
	 * 沿着X轴旋转矩阵
	 *
	 * @param matrix
	 * @param radians
	 * @return
	 */
	private static String RotateX(String[] matrix, Double radians) {
		Double matrixs5 = Math.cos(radians);
		Double matrixs6 = Math.sin(radians);
		Double matrixs9 = -Math.sin(radians);
		Double matrixs10 = Math.cos(radians);
		String[] res = new String[16];
		for (int i = 0; i < 16; i++) {
			if (i != 5 && i != 6 && i != 9 && i != 10) {
				res[i] = matrix[i];
			} else if (i == 5) {
				res[i] = matrixs5.toString();
			} else if (i == 6) {
				res[i] = matrixs6.toString();
			} else if (i == 9) {
				res[i] = matrixs9.toString();
			} else if (i == 10) {
				res[i] = matrixs10.toString();
			}
		}
		return BuildMatrix(res);
	}

	/**
	 * 沿着Y轴旋转矩阵
	 *
	 * @param matrix
	 * @param radians
	 * @return
	 */
	private static String RotateY(String[] matrix, Double radians) {
		Double matrixs0 = Math.cos(radians);
		Double matrixs2 = -Math.sin(radians);
		Double matrixs8 = Math.sin(radians);
		Double matrixs10 = Math.cos(radians);
		String[] res = new String[16];
		for (int i = 0; i < 16; i++) {
			if (i != 0 && i != 2 && i != 8 && i != 10) {
				res[i] = matrix[i];
			} else if (i == 0) {
				res[i] = matrixs0.toString();
			} else if (i == 2) {
				res[i] = matrixs2.toString();
			} else if (i == 8) {
				res[i] = matrixs8.toString();
			} else if (i == 10) {
				res[i] = matrixs10.toString();
			}
		}
		return BuildMatrix(res);
	}

	/**
	 * 沿着Z轴旋转矩阵
	 *
	 * @param matrix
	 * @param radians
	 * @return
	 */
	private static String RotateZ(String[] matrix, Double radians) {
		Double matrixs0 = Math.cos(radians);
		Double matrixs1 = Math.sin(radians);
		Double matrixs4 = -Math.sin(radians);
		Double matrixs5 = Math.cos(radians);
		String[] res = new String[16];
		for (int i = 0; i < 16; i++) {
			if (i != 0 && i != 1 && i != 4 && i != 5) {
				res[i] = matrix[i];
			} else if (i == 0) {
				res[i] = matrixs0.toString();
			} else if (i == 1) {
				res[i] = matrixs1.toString();
			} else if (i == 4) {
				res[i] = matrixs4.toString();
			} else if (i == 5) {
				res[i] = matrixs5.toString();
			}
		}
		return BuildMatrix(res);
	}

	/**
	 * 根据字符数组建立矩阵字符串
	 *
	 * @param matrix
	 * @return
	 */
	private static String BuildMatrix(String[] matrix) {
		String result = "";
		for (int p = 0; p < matrix.length; p++) {
			if (p != 15) {
				result += matrix[p] + ",";
			} else {
				result += matrix[p];
			}
		}
		return result;
	}

}
