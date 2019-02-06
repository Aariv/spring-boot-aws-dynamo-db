package org.elearn.dynamodb.utils;

public class DistanceCalculator {
	public static void main(String[] args) throws java.lang.Exception {
		System.out.println(distance(11.0168, 76.9558, 13.0827, 80.2707, "M") + " Miles\n");
		System.out.println(distance(11.0168, 76.9558, 11.0210, 76.9663, "M") + " Miles\n"); // Ganthipuram
		System.out.println(distance(11.0168, 76.9558, 11.0764, 77.0030, "M") + " Miles\n"); // Saravanambatti
		System.out.println(distance(11.0168, 76.9558, 11.1510, 76.9355, "M") + " Miles\n"); // Periyanaickenpalayam
		
//		System.out.println(distance(11.0168, 76.9558, 13.0827, 80.2707, "K") + " Kilometers\n");
//		System.out.println(distance(11.0168, 76.9558, 13.0827, 80.2707, "N") + " Nautical Miles\n");
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit == "K") {
				dist = dist * 1.609344;
			} else if (unit == "N") {
				dist = dist * 0.8684;
			}
			return (dist);
		}
	}
}