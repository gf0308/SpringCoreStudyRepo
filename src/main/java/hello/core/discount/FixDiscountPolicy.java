package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
// 인텔리제이 상 코드에서 에러줄표시 시, F2 누르면 문제부분을 바로 띄워줌
// 열거형은 상수와 비슷한 특성을 지님: 그것은 여기저기서 가져다 사용할 때 새로 복제해서 별도값을 만들어내서 이용하는게 아닌, 맨처음만들어져있는 원본의 참조값을 가져다 쓴다는 것 -> '원본'의 참조값만을 이용 -> call by reference

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;    // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) { // 열거형이 call by reference 해당되기에, 항등연산자(==) 이용이 가능(또 이게 맞음)
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
