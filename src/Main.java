import java.util.Scanner;  // Import Scanner class to handle user input

/**
 *This program lets the user input integers to begin calculation
 *The user inputs radius, height, numerator and denominator,
 *Then the program applies mathematical methods for calculation
 *These are called from the main class
 *@author danmas-4
 */
public class Main {
    // Creation of scanner object to read user input
    private static Scanner userInputScanner = new Scanner(System.in);

    // Constant value to signal when user wants to quit
    static final int QUIT = -1;

    /**
     * This method should be used only for unit testing on CodeGrade.
     * Do not change or remove this method!
     * Swaps userInputScanner with a custom scanner object bound to a test input stream
     * @param inputScanner - test scanner object
     * Relaces userInputScanner with custom input scanner for testing
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }

    public static void main(final String[] args) {
        // Variables for user inputs
        int radius = 0;
        int height = 0;
        int numerator = 0;
        int denominator = 0;

        // Print the header of the program for area and volume
        System.out.println("----------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("----------------------------------");

        // While loop runs until user enters "q" for area and volume
        while (true) {

            // Get the radius from the user
            radius = input();
            // If user enters 'q', exit the loop
            if (radius == QUIT) {
                break;
            }

            // Get the height from the user
            height = input();
            // If user enters 'q', exit the loop
            if (height == QUIT) {
                break;
            }

            // Print the radius and height -> display the area and volume
            // Prints cirlce and conea area as well as cone volume
            System.out.println("r = " + radius + ", h = " + height);
            System.out.printf("Circle area: %.2f %n", area(radius));
            System.out.printf("Cone area: %.2f %n", area(radius, height));
            System.out.printf("Cone volume: %.2f %n", volume(radius, height));
        }

        // Print the header for fractions
        System.out.println("----------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("----------------------------------");

        // While loop that runs until user enters "q" for fraction calculation
        while (true) {

            // Get the numerator from the user
            numerator = input();
            // If user enters 'q', exit the loop
            if (numerator == QUIT) {
                break;
            }

            // Get the denominator from the user
            denominator = input();
            // If user enters 'q', exit the loop
            if (denominator == QUIT) {
                break;
            }

            // Print the fraction and display the result
            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(fraction(numerator, denominator));
        }
    }

    /**
     * Method to read a valid integer input from the user or 'q' to quit.
     * Repeats until a valid integer is entered or 'q' is found.
     * Negative values are converted to positive.
     *@return The absolue value of a valid integer if chooses to press 'q'
     */
    public static int input() {
        // Loop until valid input is entered
        while (userInputScanner.hasNext()) {
            if (userInputScanner.hasNextInt()) { // Check if the input is an integer
                int val = userInputScanner.nextInt();  // Read the integer
                return Math.abs(val);  // Return absolute value of integer
            } else {
                String token = userInputScanner.next(); // Read the next input as string
                if (token.equalsIgnoreCase("q")) {  // Check if the input is 'q'
                    return QUIT;  // Return quit value
                }
                // Ignore all other invalid input (no action taken)
            }
        }
        return QUIT;  // Fallback (not expected)
    }

    /**
     *@return Calculates area of a circle given the radius.
     * Formula: π * r^2
     *@param radius is the radius of a circle
     */
    public static double area(final int radius) {
        return Math.PI * Math.pow(radius, 2); // Return area of circle using formula
    }

    /**
     *@return Calculates lateral surface area of a cone.
     * First uses Pythagoras to calculate slant height.
     * Then: π * r * s
     *@param radius Used to calculate slant height
     *@param height The height needed for calculation
     */
    public static double area(final int radius, final int height) {
        double slantHeight = pythagoras(radius, height); // Calculate slant height using Pythagoras
        return Math.PI * radius * slantHeight; // Return lateral surface area of cone
    }

    /**
     *@return Calculates hypotenuse (slant height) using Pythagorean theorem.
     * Formula: c = sqrt(a^2 + b^2)
     *@param sideA - One side of the hypotenuse
     *@param sideB - The other side of hypotenuse
     */
    public static double pythagoras(final int sideA, final int sideB) {
        return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)); // Return slant height (hypotenuse)
    }

    /**
     *@return Calculates volume of a cone.
     * Formula: (1/3) * π * r^2 * h
     *@param radius of cone needed for calc
     *@param height of cone needed for calc
     */
    public static double volume(final int radius, final int height) {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height; // Return volume of cone
    }

    /**
     *@return Converts numerator and denominator into mixed fraction.
     * Returns int array of [integerPart, numerator, denominator]
     * Returns null if denominator is 0. Returns {0, 0, 0} if numerator is 0.
     * Reduces fraction using GCD.
     *@param numerator for division
     *@param denominator for division
     */
    public static int[] fraction(final int numerator, final int denominator) {
        if (denominator == 0) {  // If denominator=0, return null
            return null;
        }
        if (numerator == 0) {  // If numerator is 0, return {0, 0, 0}
            return new int[] {0, 0, 0};
        }

        int[] result = new int[3]; // Array to hold the numerator, and denominator
        result[0] = numerator / denominator; // Integer part of the fraction
        result[1] = numerator % denominator; // Remainder becomes the numerator of fraction
        result[2] = denominator;  // Denominator remains the same

        if (result[1] != 0) {  // If there's a remainder, reduce the fraction
            int gcdVal = gcd(result[1], result[2]); // Find the GCD value
            result[1] = result[1] / gcdVal; // Simplify numerator
            result[2] = result[2] / gcdVal; // Simplify denominator
        }

        return result;  // Return the mixed fraction as an array
    }

    /**
     *@return Calculates Greatest Common Divisor using Euclid's algorithm.
     *@param a - Integer for calculation
     *@param b - Integer for calculation
     */
    public static int gcd(final int a, final int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);

        if (x < y) {  // Swap if a < b
            int temp = x;
            x = y;
            y = temp;
        }

        while (y != 0) {  // For finding the GCD
            int remainder = x % y;  // Find the remainder
            x = y;  // Set x to y
            y = remainder;  // Set y to remainder
        }
        return x;  // Return the GCD
    }

    /**
     * Prints the fraction array in the correct format:
     * - If null -> "Error"
     * - If {0, 0, 0} -> "0"
     * - If integer and fraction -> "int num/den"
     * - If just fraction -> "num/den"
     * - If just integer -> "int"
     *@param parts represents {0, 0, 0} for the mixed fraction
     *{0, 0, 0}-{integer par, numerator, denominator}
     */
    public static void printFraction(final int[] parts) {
        if (parts == null) {  // If the array is null, print "Error"
            System.out.println("Error");
        } else if (parts[0] == 0 && parts[1] == 0 && parts[2] == 0) { // If {0, 0, 0}, print "0"
            System.out.println("0");
        } else if (parts[1] == 0) { // If there's no fraction, print just the integer part
            System.out.println(parts[0]);
        } else if (parts[0] == 0) { // If there's no integer part, print just the fraction
            System.out.println(parts[1] + "/" + parts[2]);
        } else { // If both integer and fraction are present, print them together
            System.out.println(parts[0] + " " + parts[1] + "/" + parts[2]);
        }
    }
}
