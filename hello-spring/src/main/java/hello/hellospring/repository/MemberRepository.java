package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // 못 찾으면 null 반환인데 Optional 을 감싸서 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
