package com.weisen.www.code.yjf.merchant.service.util;

public class LocationUtils {
	private static double EARTH_RADIUS = 6378.137;
	 
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
 
	/**
	 * 通过经纬度获取距离(单位：米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 距离
	 */
	public static final double getDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = rad(lat1);  
	       double radLat2 = rad(lat2);  
	       double a = radLat1 - radLat2;      
	       double b = rad(lng1) - rad(lng2);  
	       double s = 2 * Math.asin(Math.sqrt(Math.abs(Math.pow(Math.sin(a/2),2) +   
	        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))));  
	       s = s * EARTH_RADIUS;  
	       s = Math.round(s * 1000);  
	       return s;  
	}

}
