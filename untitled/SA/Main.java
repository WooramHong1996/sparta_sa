package SA;

public class Main {
    public static void main(String[] args) {

        Bus bus101 = new Bus(3000, 101, 11);

        bus101.ride(5);
        bus101.drive();
        bus101.upspeed(10);
        bus101.ride(10);
        bus101.decelerate(5);
        bus101.garage();
    }
}
