package hello.core.singleton;

public class SingletonService {

    // 인스턴스 미리 생성하기
    private static SingletonService instance = new SingletonService();

    // 인스턴스 새로 못만들게 하기
    private SingletonService() { }

    // 만들어둔 인스턴스 가져가 쓸 수 있게 하기
    public static SingletonService getInstance() {
        return instance;
    }
    
    public void logic() {
        System.out.println("싱글통 객체 로직 호출");
    }
}
