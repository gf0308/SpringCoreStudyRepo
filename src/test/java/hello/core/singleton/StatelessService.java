package hello.core.singleton;

public class StatelessService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;   // 파라미터로 투입된 price (지역변수 인 price)를 그대로 반환함
    }

}
