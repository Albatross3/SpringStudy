package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository // container가 등록할 수 있게 됨
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L; // 0,1,2 key 값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    // ofNullable -> 있으면 Optional 객체 , 없으면 비어있는 Optional 객체 반환환
   @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // stream 사용법 + 람다 사용법
    // store.values() -> store 의 값들(Member 들)
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        // 끝까지 돌렸는데 없으면 Optional에 null이 포함되서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
