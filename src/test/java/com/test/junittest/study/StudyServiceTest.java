package com.test.junittest.study;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.test.junittest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
// 어노테이션 활용하려
class StudyServiceTest {

//  @Mock
//  MemberService memberService;
//
//  @Mock
//  StudyRepository studyRepository;

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

}