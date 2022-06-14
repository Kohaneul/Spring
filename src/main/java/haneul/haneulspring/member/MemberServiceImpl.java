package haneul.haneulspring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    private final MemberRepository repository;//추상화에만 의존함,
    @Autowired  //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;   //생성자 주입
    }
    //Component를 쓰게 되면 이 클래스 내에서 의존관계 주입 설정이 어렵기 때문에 자동으로 Autowired를 사용하게 된다.
    //그러면 type을 찾아서 주입하게 된다. MemberRepository -> MemoryMemberRepository

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return repository.findById(memberId);
    }


    //테스트용도
    public MemberRepository getMemberRepository(){
        return repository;
    }

}
