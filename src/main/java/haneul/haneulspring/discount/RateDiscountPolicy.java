package haneul.haneulspring.discount;

import haneul.haneulspring.member.Grade;
import haneul.haneulspring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
        //return 할인 대상 금액
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()==Grade.VIP) {
            return price*discountPercent/100;
        } else {
            return 0;
        }
    }
}
