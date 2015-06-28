package Lyft;
/** Prompt:
 * Calculate the detour distance between two different rides. 
 * Given four latitude / longitude pairs, 
 * where driver one is traveling from point A to point B and driver two is traveling from point C to point D, 
 * write a function (in your language of choice) 
 * to calculate the shorter of the detour distances 
 * the drivers would need to take to pick-up and drop-off the other driver.
 */

/**
 * Decided to use a Coordinate class for the latitude/longitude pairs.
 * @author Jeannie
 *
 */
public class Coordinate {

	/**
	 * Test code here.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Coordinate A = new Coordinate(71.0589, 42.3601); // Boston, MA
		Coordinate B = new Coordinate(75.1667, 39.9500); // Philadelphia, PA 
		Coordinate C = new Coordinate(74.0059, 40.7127); // New York, NY
		Coordinate D = new Coordinate(72.6743, 41.7627); // Hartford, CT
		
		// Some test code.
		//System.out.println(Coordinate.distance(A, B) + " miles. Boston to Philadelphia.");
		//System.out.println(Coordinate.distance(C, D) + " miles. New York to Hartford.");
		//System.out.println(Coordinate.distance(A, C) + " miles. Boston to New York.");
		//System.out.println(Coordinate.distance(A, D) + " miles. Boston to Hartford.");
		
		// Test shortest detour method.
		shortestDetourDistance(A, B, C, D);
		
	}
	
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor for a Coordinate. Longitude and latitude passed in are in degrees.
	 * @param latitude
	 * @param longitude
	 */
	public Coordinate(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude(){
		return this.latitude;
	}
	
	public double getLongitude(){
		return this.longitude;
	}
	
	/**
	 * Computes distance from one pair of coordinates to another.
	 * Used the Haversine formula for calculation, which takes into account the Earth's spherical shape.
	 * @param coord1
	 * @param coord2
	 * @return The distance between two points in miles.
	 */
	public static double distance(Coordinate coord1, Coordinate coord2){
		
		double theta = coord1.getLongitude() - coord2.getLongitude();
		double coord1_latitude_rad = degreeToRadian(coord1.getLatitude());
		double coord2_latitude_rad = degreeToRadian(coord2.getLatitude());
		
		double distance = Math.sin(coord1_latitude_rad) 
				* Math.sin(coord2_latitude_rad) 
				+ Math.cos(coord1_latitude_rad)
				* Math.cos(coord2_latitude_rad)
				* Math.cos(degreeToRadian(theta));
		distance = Math.acos(distance);
		distance = radianToDegree(distance);
		
		distance = distance * 60 * 1.1515;
		
		return distance;
	}
	
	public static double shortestDetourDistance(Coordinate A, Coordinate B, Coordinate C, Coordinate D){
		
		double distance_A_B = Coordinate.distance(A, B);
		//double distance_A_C = Coordinate.distance(A, C);
		//double distance_B_D = Coordinate.distance(B, D);
		double distance_C_D = Coordinate.distance(C, D);
		
		// Two different routes are possible for this problem:
		// 1) A -> C -> D -> B
		// 2) C -> A -> B -> D
		// The minimum would be finding the shorter distance C -> D (as if we were taking the first route)
		// versus A -> B (as if we were taking the second route)
		if (distance_C_D == distance_A_B){
			System.out.println("Both detours are the same distance, with " + distance_C_D + " miles of detour.");
			return distance_C_D;
		}
		else if (distance_C_D < distance_A_B){
			System.out.println("Path A -> C -> D -> B is shortest, with " + distance_C_D + " miles of detour.");
			return distance_C_D;
		}
		else {
			System.out.println("Path C -> A -> B -> D is shortest, with " + distance_A_B + " miles of detour.");
			return distance_A_B;
		}		
	}
	
	/**
	 * Converts degrees to radian.
	 * @param deg
	 * @return
	 */
	public static double degreeToRadian(double deg){
		return deg * Math.PI / 180.0;
	}
	
	/**
	 * Converts radian to degrees.
	 * @param rad
	 * @return
	 */
	public static double radianToDegree(double rad){
		return rad * 180.0 / Math.PI;
	}
}

