package haneul.haneulspring.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository repository;//추상화에만 의존함,

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;   //생성자 주입
    }

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return repository.findById(memberId);
    }
}
