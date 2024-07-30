package finance;

import java.util.Scanner;

public class Forecating 
{
	public static double calculateFutureValue(double initialValue, double growthRate, int years) 
	{
        if (years == 0) 
        {
            return initialValue;
        }
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) 
    {
    	Scanner sc=new Scanner(System.in);
    	double growthRate = 0.05;
    	
    	System.out.print("Enter Initial Investment: ");
        double initialInvestment = sc.nextDouble();  
        sc.nextLine();
        
        System.out.print("Enter No.of Years: ");
        int numberOfYears = sc.nextInt();
        sc.nextLine();

        double futureValue = calculateFutureValue(initialInvestment, growthRate, numberOfYears);
        System.out.printf("Future Value: %.2f", futureValue);
        
        sc.close();
    }
}


