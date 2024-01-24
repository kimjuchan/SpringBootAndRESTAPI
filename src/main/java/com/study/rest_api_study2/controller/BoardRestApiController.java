package com.study.rest_api_study2.controller;


import com.study.rest_api_study2.domain.Board;
import com.study.rest_api_study2.domain.Member;
import com.study.rest_api_study2.dto.BoardDto;
import com.study.rest_api_study2.exception.BoardNotFoundException;
import com.study.rest_api_study2.exception.UserNotFoundException;
import com.study.rest_api_study2.repository.BoardRepository;
import com.study.rest_api_study2.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardRestApiController {

    //의존성 주입
    //생성자 주입 방식 적용 (spring 4.3.xxx 이후로 부터는 가장 권장되는 방식 && @Autowired 생략가능)
    private final BoardRepository boardRepository;
    //하나의 DI 주입 과정.
    public BoardRestApiController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //해당 ID 정보 기준으로 MEMBER 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity boardFindById(@PathVariable("id") int id){

        //id 기반 board 정보 조회.
        Optional<Board> board =  boardRepository.findById(id);
        if(!board.isPresent()){
            throw new BoardNotFoundException("해당 ID : [" + id + "] 에 대한 정보가 없음. ID 값 확인 필요");
        }

        // 형식 1)
        return ResponseEntity
                //type : int
                .status(200)
                .headers(new HttpHeaders())
                .body(board);
        // 형식 2)
        //return ResponseEntity.ok(user);
        // 형식 3)
        //return new ResponseEntity<>(body, header, HttpStatus.OK);
    }

    //param 데이터 받을때
    //중간에 데이터 가공처리 시 dto
    //
    @PostMapping("/save")
    public ResponseEntity saveBoard(@RequestBody Board board){
        log.info("SAVE Board");
        log.info("Board INFO : [" + board.toString() + " ] **");
        Optional<Board> boardInfo = Optional.of(board);
        if(!boardInfo.isPresent()) {
            throw new BoardNotFoundException("해당 ID BOARD 정보 없음");
        }else{
            boardRepository.save(board);
        }
        return ResponseEntity.ok(board);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateBoard(@PathVariable("id") int id, @RequestBody Board board){
        log.info("UPDATE Board");

        BoardDto boardDto
        Optional<Board> boardInfo = Optional.of(board);
        if(!boardInfo.isPresent()) {
            throw new BoardNotFoundException("해당 ID BOARD 정보 없음");
        }else{
            boardRepository.save(board);
        }
        return ResponseEntity.ok(board);
    }



}
