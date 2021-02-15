package com.test.junittest.study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.junittest.domain.Member;
import com.test.junittest.domain.Study;
import com.test.junittest.member.MemberService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
// 어노테이션 활용하려
class StudyServiceTest {

  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  void creatStudyService(
      @Mock MemberService memberService,
      @Mock StudyRepository studyRepository
  ) {
//    MemberService memberService = mock(MemberService.class);
//    StudyRepository studyRepository = mock(StudyRepository.class);

    StudyService studyService = new StudyService(memberService, studyRepository);

    assertNotNull(studyService);
  }

  @Test
  void stubbing() {
    Member member = new Member();
    member.setId(1L);
    member.setName("jinho");

    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(memberService.findById(2L)).thenThrow(new IllegalArgumentException());
    Optional<Member> findMember = memberService.findById(1L);

    assertThrows(
        IllegalArgumentException.class, () ->
            memberService.findById(2L)
    );
    assertThat(findMember.get().getName()).isEqualTo("jinho");
  }

  @Test
  void stubbing2() {
    Member member1 = new Member();
    member1.setName("jinho");

    Member member2 = new Member();
    member2.setName("woodcock");

    when(memberService.findById(any()))
        .thenReturn(Optional.of(member1))
        .thenReturn(Optional.of(member2));

    Member findMember1 = memberService.findById(1L).orElseGet(Member::new);
    Member findMember2 = memberService.findById(1L).orElseGet(Member::new);

    assertAll(
        () -> assertThat(findMember1.getName()).isEqualTo("jinho"),
        () -> assertThat(findMember2.getName()).isEqualTo("woodcock")
    );
  }

  @Test
  void test() {
    Study study = new Study(10, "test");
    Member member = new Member();
    member.setId(1L);
    member.setName("jinho");

    when(studyRepository.save(study)).thenReturn(study);
    when(memberService.findById(1L)).thenReturn(Optional.of(member));

    Study saveStudy = studyRepository.save(study);
    Member findMember = memberService.findById(1L).orElseThrow(EntityNotFoundException::new);

    assertThat(saveStudy.getName()).isEqualTo("test");
    assertThat(findMember.getName()).isEqualTo("jinho");
  }

  @Test
  void invokeTest() {
    StudyService studyService = new StudyService(memberService, studyRepository);
    Study study = new Study(10, "test");
    Member member = new Member();
    member.setId(1L);
    member.setName("jinho");

    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(studyRepository.save(study)).thenReturn(study);

    // bdd 스타일
    given(memberService.findById(1L)).willReturn(Optional.of(member));
    given(studyRepository.save(study)).willReturn(study);

    studyService.createNewStudy(1L, study);

    assertThat(study.getOwner()).isEqualTo(member);

    verify(memberService, times(1)).notify(study);
    verify(memberService, times(1)).notify(member);

    verify(memberService, never()).validate(any());

    then(memberService).should(times(1)).notify(study);

    InOrder inOrder = inOrder(memberService);
    inOrder.verify(memberService).notify(study);
    inOrder.verify(memberService).notify(member);
  }
}