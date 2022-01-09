package hello.core.singleton;

/** [싱글톤 객체 구현 원리] 
 * 자바가 이 SingletonService을 스캔하면서
 * "private static final SingletonService instance = new SingletonService();" 대로
 * SingletonService객체 인스턴스를 만들고이 인스턴스에 static final 이 적용되며
 * 그 상태로 메모리에 저장되어짐
 * -> 
 * 그러고 나서 SingletonService 클래스의 생성자는 private 화 해서 외부에서 new SingletonService()로 객체인스턴스생성을 못하게 막음
 * -> 
 * 이 상태에서 SingletonService 인스턴스를 얻으려면 getInstance() 메서드 하나 밖에 없음
 * 근데 이걸로 얻는 인스턴스는 최초의 그 SingletonService 객체임
 * => 이 SingletonService의 객체를 얻는건 맨처음의 '1st SingletonService 인스턴스' 만 가능하며, 
 * 객체를 요청하는 곳마다 이것만(참조값) 얻게 됨
 * => 결과적으로 객체를 딱 1번만 생성하며 (2번이상 생성은 못하게되고) 동일한 객체만 공유해서 쓰게 됨
 * ==> 이것이 '싱글톤' 방식 객체다.
 * */

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); 

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
