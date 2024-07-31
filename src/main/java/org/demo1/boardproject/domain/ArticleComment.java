package org.demo1.boardproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Setter
    @ManyToOne(optional = false)
    private  Article article;  //기사
    @Setter @Column(nullable = false, length = 500)
    private  String title; //제목
    private  String content;


   

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(nullable = false,length = 100)// 누가 만들었는지 정보를 알아야함
    private  String createdBy; //생성자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(nullable = false,length = 100)
    private  String modifiedBy;


    protected ArticleComment() {}  //NoArgument 로 롬복으로 대체 라능

    private ArticleComment(Article article,String content){
        this.article=article;
        this.content=content;
    }
    public static ArticleComment of(Article article,String content){
      return new ArticleComment(article,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id !=null && id.equals( that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
