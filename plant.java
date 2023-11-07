import java.util.*;

public class plant {

    double leaf_size;
    double height;
    double reproduction_rate;
    double disease_sus;
    int lifetime;
    int offspring;

    int age; //cycles
    int last_repro; //# of cycles since last reproduction
    int half_age;


    public double calculate_trait(double t1, double t2){
        double expected_value = 0.5*t1 + 0.5*t2;
        double variance = expected_value*expected_value + (0.5*t1*t1+0.5*t2*t2);
        double standard_deviation = Math.sqrt(variance);
        Random rand = new Random();
        return (expected_value - standard_deviation) + (2*standard_deviation*rand.nextDouble());
    }


    public int calculate_trait(int t1, int t2) {
        double expected_value = 0.5 * t1 + 0.5 * t2;
        double variance = expected_value * expected_value + (0.5 * t1 * t1 + 0.5 * t2 * t2);
        double standard_deviation = Math.sqrt(variance);
        Random rand = new Random();
        return (int) ((expected_value - standard_deviation) + (2 * standard_deviation * rand.nextDouble()));
    }
}
