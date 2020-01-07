
public class Lab4
{
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        while(Robot.frontIsClear())
        {
            completeOneBar();
        }
        completeLastBar();
    }

    public static void completeLastBar()
    //precondition: Robot is facing east in the last column
    //postcondition: Robot has darkened the last column
    {
        Robot.turnLeft();
        while(!Robot.onDark())
        {
            Robot.makeDark();
            Robot.move();
        }
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        Robot.turnLeft();
    }

    public static void completeOneBar()
    //precondition: Robot is facing east at the bottom of a column
    //postcondition: Robot has darkened the entire column, is facing east at the bottom of the next column
    {
        Robot.turnLeft();
        while(!Robot.onDark())
        {
            Robot.makeDark();
            Robot.move();
        }
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        Robot.turnLeft();
        Robot.move();
    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        while(isfrontDark())
        {
            moveOneSquare();
            if(!Robot.onDark())
            {
                Robot.move();
            }
        }
    }

    public static boolean isfrontDark()
    //precondition: Robot may be standing on a dark square (if there is a dark square in front)
    //postcondition: Robot knows if it is standing on a dark spot or not
    {
        if(Robot.onDark())
        {
            return true;
        }
        else
        {
            while(Robot.frontIsClear())
            {   
                Robot.move();
                if(Robot.onDark())
                {
                    return true;
                }
            }
            turnAround();
            while(Robot.frontIsClear())
            {
                Robot.move();
            }
            turnAround();
            return false;
        }
    }

    public static void moveOneSquare()
    //precondition: Robot is standing in left column
    //postcondition: Robot has moved a square to the right column and is ready to repeat
    {
        bottomSquare();
        darkenRightColumn();
        returnLeftColumn();
    }

    public static void returnLeftColumn()
    //precondition: Robot has added one square to the right column
    //postcondition: Robot has returned to the left column ready to move another
    {
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        turnRight();
        Robot.move();
        turnRight();
    }

    public static void bottomSquare()
    //precondition: Robot is in left column
    //postcondition: Robot has made a square light and moved into the right column
    {
        if(Robot.onDark())
        {
            Robot.makeLight();
        }
        turnRight();
        Robot.move();
        Robot.turnLeft();
    }

    public static void darkenRightColumn()
    //precondition: Robot is in right column
    //postcondition: Robot has added a dark square to the top of the column
    {
        while(Robot.onDark())
        {
            Robot.move();
        }
        Robot.makeDark();
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void connectDots()
    {
        while(canConnectDot())
        {
            ConnectOneDot();
        }
    }

    public static boolean canConnectDot()
    //precondition: Robot may be able to connect to a dot
    //postcondition: Robot knows if it can connect to a dot 
    {
        if(isDotAhead())
        {
            return true;
        }
        else
        {
            if(isDotOnLeft())
            {
                return true;
            }
            else
            {
                if(isDotOnRight())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }

    public static void ConnectOneDot()
    //precondition: Robot is standing on a dot
    //postcondition: Robot has connected to a dot ahead, to its left, or to its right, or done nothing
    {
        ifDotAhead();
        ifDotOnLeft();
        ifDotOnRight();
    }

    public static void ifDotOnRight()
    //precondition: Robot might have a dot to its right (relative to original direction)
    //postcondition: Robot has either connected the dot ahead or done nothing
    {
        if(isDotOnRight())
        {
            Robot.move();
            Robot.makeDark();
            Robot.move();
        }
    }

    public static void ifDotAhead()
    //precondition: Robot might have a dot ahead
    //postcondition: Robot has either connected the dot ahead or done nothing
    {
        if(isDotAhead())
        {
            Robot.move();
            Robot.makeDark();
            Robot.move();
        }
    }

    public static void ifDotOnLeft()
    //precondition: Robot might have a dot to its left (relative to original direction)
    //postcondition: Robot has either connected the dot to its left or done nothing
    {
        if(isDotOnLeft())
        {
            Robot.move();
            Robot.makeDark();
            Robot.move();
        }
    }

    public static boolean isDotOnRight()
    //precondition: Robot may have an unconnected dot to its right (relative to original direction)
    //postcondition: Robot knows if there is an unconnected dot to its right
    {
        turnAround();
        Robot.move();
        if(Robot.onDark())
        {
            turnAround();
            Robot.move();
            return false;
        }
        else
        {
            Robot.move();
            if(Robot.onDark())
            {
                returnPosition();
                return true;
            }
            returnPosition();
            return false;
        }
    }

    public static boolean isDotOnLeft()
    //precondition: Robot may have an unconnected dot to its left (relative to original direction)
    //postcondition: Robot knows if there is an unconnected dot to its left
    {
        Robot.turnLeft();
        Robot.move();
        if(Robot.onDark())
        {
            turnAround();
            Robot.move();
            return false;
        }
        else
        {
            Robot.move();
            if(Robot.onDark())
            {
                returnPosition();
                return true;
            }
            returnPosition();
            return false;
        }
    }

    public static boolean isDotAhead()
    //precondition: Robot may have an unconnected dot ahead of it
    //postcondition: Robot knows if there is an unconncted dot ahead of it
    {
        Robot.move();
        if(Robot.onDark())
        {
            turnAround();
            Robot.move();
            return false;
        }
        else
        {
            Robot.move();
            if(Robot.onDark())
            {
                returnPosition();
                return true;
            }
            returnPosition();
            return false;
        }
    }

    public static void returnPosition()
    //precondition: Robot has moved 2 squares forward in its original direction
    //postcondition: Robot has returned to the previous position in the previous direction
    {
        turnAround();
        Robot.move();
        Robot.move();
        turnAround();
    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
}
