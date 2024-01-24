package com.study.rest_api_study2.controller;


import com.study.rest_api_study2.domain.Member;
import com.study.rest_api_study2.dto.MemberDto;
import com.study.rest_api_study2.exception.UserNotFoundException;
import com.study.rest_api_study2.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//rest api
//spring batch  +@scheduler
//security


//주문 정보
//상품 - >  1)일반 상품 , 2)예약 상품... ----> 배치
//마이페이지
//로그인
//상품 조회 화면.

@Slf4j
@RestController
//@RequiredArgsConstructor : 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다
//새로운 필드를 추가할 때 다시 생성자를 만들어서 관리해야하는 번거로움을 없애준다. (@Autowired를 사용하지 않고 의존성 주입)
public class MemberController {

    //의존성 주입
    //생성자 주입 방식 적용 (spring 4.3.xxx 이후로 부터는 가장 권장되는 방식 && @Autowired 생략가능)
    private final MemberRepository memberRepository;
    //하나의 DI 주입 과정.
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //해당 ID 정보 기준으로 MEMBER 정보 조회
    @GetMapping("/member/{id}")
    public ResponseEntity userFindById(@PathVariable("id") int id){

        //id 기반 member 정보 조회.
        Optional<Member> user =  memberRepository.findById(id);
        //user.orElseThrow(() -> new UserNotFoundException("해당 ID : [" + id + "] 에 대한 정보가 없음. ID 값 확인 필요"));
        if(!user.isPresent()){
            throw new UserNotFoundException("해당 ID : [" + id + "] 에 대한 정보가 없음. ID 값 확인 필요");
        }

        //spring boot 제공하는 HateOas 설정 sample.
        //해당 response 정보에 연관된 링크 정보 연동 해줄 수 있음.
        /*
        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).jpaUserFindAll());
        entityModel.add(linTo.withRel("all-users"));  // http://localhost:8088/users 링크 값 연동 해줌*/
        //return ResponseEntity.ok(user);

        //ResposnseEntity 반환 객체 설정 방법 3가지
        //HttpHeaders header = new HttpHeaders();
        //header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 형식 1)
        return ResponseEntity
                //type : int
                .status(200)
                .headers(new HttpHeaders())
                .body(user);
        // 형식 2)
        //return ResponseEntity.ok(user);
        // 형식 3)
        //return new ResponseEntity<>(body, header, HttpStatus.OK);
    }

   /*
    @GetMapping("/list")
    public ResponseEntity<MemberDto> listMembers(@Validated @RequestBody Member member){
        ResponseEntity<MemberDto> mem = new ResponseEntity<MemberDto>(member,null,"200");
        return mem;
    }*/

    @PostMapping("/save")
    public ResponseEntity saveMembers(@RequestBody Member member){
        log.info("SAVE MEMBER");
        log.info("MEMBER INFO : [" + member.toString() + " ] **");
        memberRepository.save(member);
        return ResponseEntity.ok(member);
    }
}
