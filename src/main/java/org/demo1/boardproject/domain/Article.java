package org.demo1.boardproject.domain;

import java.time.LocalDateTime;

public class Article {

    private  Long id;

    private  String title; //제목
    private  String hashtag;

    private String content; //내용
    private LocalDateTime createdAt;

    private  String createdBy; //생성자
    private LocalDateTime modifiedAt;

    private  String modifiedBy;

}
