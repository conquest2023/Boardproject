package org.demo1.boardproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest
class ArticleControllerTest {



    private  final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 -정상 호출")
    public  void given_whenRequest_then() throws  Exception{

        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(view().name("articleComments"))
                .andExpect(model().attributeExists("articles"));



    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판)상세 페이지 -정상 호출")
    public  void given_whenRequestId_then() throws  Exception{

        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"));

    }
    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 검색 페이지 -정상 호출")
    public  void given_whenRequestSearch_then() throws  Exception{

        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));


    }
    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 해시태그 페이지 -정상 호출")
    public  void given_whenRequestHashtag_then() throws  Exception{

        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search-hashtag"));


    }



}