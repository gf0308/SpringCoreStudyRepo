package hello.core.member;

// 한 열거형의 '타입' 이란 것은, 그 열거형 안의 값(내용)에 해당되는 것이면 된다는 것이다.

public class Member {

    private Long id;
    private String name;
    private Grade grade;

    // alt+insert : constructor, getter/setter 등 바로 만들수있는 단축키

    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
