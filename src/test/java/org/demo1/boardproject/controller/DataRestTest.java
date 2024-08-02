package org.demo1.boardproject.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@DisplayName("Data REST-TEST")
@AutoConfigureMockMvc
@Transactional // 롤백 상태로 트랜잭션으로 묶이게 됨
@SpringBootTest  //통합테스트
public class DataRestTest {


    private  final MockMvc mvc;

    private DataRestTest(@Autowired MockMvc mvc){

        this.mvc=mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회 ")
    @Test
    void test() throws  Exception{


          mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.valueOf("application/hal+json")));



    }
    @DisplayName("[api] 게시글 단건 조회 ")
    @Test
    void testBy() throws  Exception{


        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.valueOf("application/hal+json")));



    }
    @DisplayName("[api] 게시글 ->댓글 리스트 조회 ")
    @Test
    void testCommentAll() throws  Exception{


        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.valueOf("application/hal+json")));



    }
    @DisplayName("[api] 댓글 리스트 조회 ")
    @Test
    void testComment() throws  Exception{


        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.valueOf("application/hal+json")));



    }


    @DisplayName("[api] 댓글 단건 조회 ")
    @Test
    void testCommentId() throws  Exception{


        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.valueOf("application/hal+json")));



    }
}
