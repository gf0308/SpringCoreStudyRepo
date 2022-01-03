package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 사실 위의 map 같은 경우에는 (실무에선 (여러군데에서 동시에 접근해서 사용할 때 발생하는)'동시성 이슈'가 있을 수 있기 때문에) 'ConCurrentHashMap' 을 써야한다, 하지만 지금은 설명을 위한 단순 예제이므로 HashMap 으로 한 것

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
