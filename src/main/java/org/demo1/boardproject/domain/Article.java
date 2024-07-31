package org.demo1.boardproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql 은 IDENTITY 방식으로 Autoincrement 를 함
    private  Long id;

    //원하는 속성을 바꿀수 있게 하도록 Setter를 설정
    @Setter @Column(nullable = false)
    private  String title; //제목
    @Setter @Column(nullable = false ,length=10000 )
    private String content; //내용
    @Setter
    private  String hashtag;


    @OrderBy("id")
    @OneToMany( mappedBy = "article",cascade = CascadeType.ALL) //article 을 매핑해줌
    private  final Set<ArticleComment> articleComments=new LinkedHashSet<>();


    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy @Column(nullable = false,length = 100)// 누가 만들었는지 정보를 알아야함
    private  String createdBy; //생성자
    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @LastModifiedBy @Column(nullable = false,length = 100)
    private  String modifiedBy;

    //hibernate를 구현 하기 위해서 생성자를 해야함
    protected  Article(){

    }


    private   Article (String title, String content, String hashtag){
        this.title=title;
        this.content=content;
        this.hashtag=hashtag;
    }

    //도메인 정보를 오픈 하고 싶으면 생성자 원하는 부분만 사용한다
    public static Article of(String title, String content, String hashtag){
       return  new Article(title,content,hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id); //아이디가 결국 다르면 다른 값을 부여하겠다는 뜻   데이터의 영속화
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
