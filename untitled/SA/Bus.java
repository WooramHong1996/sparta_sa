package SA;

public class Bus extends publicTransport{
    public Bus() {
        passenger = 0;
        maxPassenger = 30;
        state = false;
        dayCost=0;
        speed=0;
    }
    public Bus(int cost, int num, int oil) {
        this.cost = cost;
        this.num = num;
        this.oil = oil;
    }
    void check() {
        if (!this.state)
            System.out.println("운행중인 차가 아닙니다.");
        return;
    }
    public void drive() {
        if (oil < 10) {
            System.out.println("운행할 수 없습니다.");
        } else {
            this.state = true;
            System.out.println("운행중으로 바뀝니다");
        }
        return;
    }
    public void garage() {
        this.state = false;
        System.out.println("차고지행으로 바뀝니다.");
        return;
    }
    public void ride(int passenger) {
        check();
        if (this.passenger + passenger > maxPassenger) {
            this.passenger = maxPassenger;
            System.out.println("더이상 태울 수 없습니다");
            return;
        }
        this.passenger += passenger;
        System.out.println("승객이"+this.passenger+"명 탑승해 있습니다.");
        return;
    }
    public void decelerate(int speed) {
        check();
        if (speed >= this.speed) {
            this.speed = 0;
            System.out.println("속도가 " + this.speed + "입니다.");
        }else{
            this.speed -= speed;
            System.out.println("속도가 " + this.speed + "입니다.");
        }
    }
    public void upspeed(int speed) {
        check();
        this.speed += speed;
        System.out.println("속도가 " + this.speed + "입니다.");
        return;
    }
}
