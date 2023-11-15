package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

//주문 서비스 구현체
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; //인터페이스만 의존하도록 변경

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원이 있는 지 확인
        int discountPrice = discountPolicy.discount(member, itemPrice); // SRP(단일 책임 원칙) 매우 잘 치킴 - 할인만 신경 쓰면 됨
        //Grade만 넘길 지 Member를 넘길지는 프로젝트 목저에 따라 바뀜
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
