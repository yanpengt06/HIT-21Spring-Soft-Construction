/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i = 1; i <= 4;i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	int n = sides;
    	return (n-2)*(double)180/sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	return 360/(int)(180 - angle);
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	double angle = TurtleSoup.calculateRegularPolygonAngle(sides);
    	for(int i = 1; i <= sides; i++)
    	{
    		turtle.forward(sideLength);
    		turtle.turn(180-angle);
    	}
    	
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, double currentX, double currentY,
    		double targetX, double targetY) {
    	double angle = Math.atan2(targetX - currentX, targetY - currentY) * 180 / Math.PI; 
    	double turnAngle = angle - currentBearing;
        if (turnAngle < 0)
            turnAngle += 360;
        return  turnAngle;

    } 
    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	List<Double> bearings = new ArrayList<>();
    	int x = 0,y = 0,x1 = 0,y1 = 0;
    	double currentBearing = 0.0,angle = 0;
    	for(int i = 0;i < xCoords.size()-1;i++)
    	{
    		x = xCoords.get(i);
    		y = yCoords.get(i);
    		x1 = xCoords.get(i+1);
    		y1 = yCoords.get(i+1);
    		angle = calculateBearingToPoint(currentBearing,x,y,x1,y1);
    		bearings.add(angle);
    		currentBearing+=angle;
    		if(currentBearing>=360)
    			currentBearing-=360;
    	}
    	return bearings;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {                	
    	LinkedHashSet<Point> convexHull = new LinkedHashSet<Point>(); 
    	if(points.size() <= 2) {
        	return points;
        }
    	else {
    		Point xmin = new Point(Double.MAX_VALUE, Double.MAX_VALUE); 
    		for(Point item : points) {
    			if (item.x() < xmin.x() || (item.x() == xmin.x() && item.y() < xmin.y()))
    				xmin = item;
    		}
    		Point nowPoint = xmin, tempPoint = xmin;
    		double nowAngle = 0, minAngle = 360, tempAngle = 0;
    		double distance;
    		double maxdistance = -1;
    		do{
    			convexHull.add(nowPoint);
    			for(Point item : points) {
    				if ((!convexHull.contains(item) || item == xmin)  ) {
    					tempAngle = calculateBearingToPoint(nowAngle, nowPoint.x(), nowPoint.y(), item.x(), item.y());
    					distance = (item.x() - nowPoint.x())*(item.x() - nowPoint.x()) + (item.y() - nowPoint.y())*(item.y() - nowPoint.y());
    					if(tempAngle < minAngle || ((tempAngle == minAngle) && (distance > maxdistance))) {
    						minAngle = tempAngle;
    						tempPoint = item;
    						maxdistance = distance;
    					}
    				}	
    			}
    			nowAngle += minAngle;
    			if(nowAngle >= 360)
    				nowAngle -= 360;
    			minAngle = 360;
    			nowPoint = tempPoint;
    		}while(nowPoint != xmin);
    		return convexHull;
    	}

    }
    
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	turtle.turn(18);
    	for(int i = 1;i <= 5 ;i++)
    	{
    		switch(i)
    		{
    			case 1:turtle.color(PenColor.RED); 
    					break;
    			case 2:
    					turtle.color(PenColor.BLUE);
    					break;
    			case 3:
    					turtle.color(PenColor.GREEN);
    					break;
    			case 4:
    					turtle.color(PenColor.ORANGE);
    					break;
    			case 5:
    					turtle.color(PenColor.CYAN);
    					break;
    			default:
    			
    		}
    		turtle.forward(100);
    		turtle.turn(180-36);
    	}
    	
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        drawSquare(turtle, 40);
//        TurtleSoup.drawRegularPolygon(turtle, 5, 40);
        // draw the window
        turtle.draw();
        TurtleSoup.drawPersonalArt(turtle);
    }

}
