package synthesizer;

import java.util.HashSet;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<Double>((int) (SR / frequency));
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //       Make sure that your random numbers are different from each other.
        HashSet<Double> set = new HashSet<>();
        for (int i = 0; i < buffer.capacity(); i++) {
            double number = Math.random() - 0.5;
            while (set.contains(number)) {
                number = Math.random() - 0.5;
            }
            set.add(number);
            buffer.enqueue(number);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double average = DECAY * (buffer.dequeue() + buffer.peek()) * 0.5;
        buffer.enqueue(average);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
