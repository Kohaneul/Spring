package haneul.haneulspring.order;

import haneul.haneulspring.discount.DiscountPolicy;
import haneul.haneulspring.discount.FixDiscountPolicy;
import haneul.haneulspring.discount.RateDiscountPolicy;
import haneul.haneulspring.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;//인터페이스에만 의존하는 중
    @Autowired
    public OrderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);//최종적으로 할인가격 받음

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    public MemberRepository getMemberRepository(){
        return repository;
    }
}
