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
        while(Robot.onDark())
        {
            moveSquare();
            returnLeftColumn();
        }
    }

    public static void returnLeftColumn()
    //precondition: 
    //postcondition: 
    {
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        turnRight();
        Robot.move();
        turnRight();
        Robot.makeLight();
        Robot.move();
    }

    public static void moveSquare()
    //precondition: 
    //postcondition: 
    {
        if(Robot.onDark())
        {
            turnRight();
            Robot.move();
            Robot.turnLeft();
            while(Robot.onDark())
            {
                Robot.move();
            }
            Robot.makeDark();
        }
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
        //insert instructions below

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
