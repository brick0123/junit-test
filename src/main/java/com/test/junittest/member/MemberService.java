package com.test.junittest.member;

import com.test.junittest.domain.Member;
import com.test.junittest.domain.Study;
import java.util.Optional;

public interface MemberService {

  void validate(Long memberId) throws InvalidMemberException;

  Optional<Member> findById(Long memberId) throws MemberNotFoundException;

  void notify(Study saveStudy);

  void notify(Member member);
}
