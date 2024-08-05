package org.demo1.boardproject.repository;


import org.demo1.boardproject.config.JpaConfig;
import org.demo1.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import  static  org.assertj.core.api.Assertions.*;


@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
public class ArticleTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    public ArticleTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void given_when_then(){

        List<Article> articles= articleRepository.findAll();

        //Then

        assertThat(articles)
                .isNotNull()
                 ;
    }

    @DisplayName("insert test")
    @Test
    void given_when_then_Insert(){

        long previousCount=articleRepository.count();


        Article savedArticle= articleRepository.save(Article.of("new article","new content","#spring"));

        List<Article> articles= articleRepository.findAll();

        //Then

        assertThat(articleRepository.count())
                .isEqualTo(previousCount+1);
    }
    @DisplayName("update test")
    @Test
    void given_when_then_Update(){

        Article article=articleRepository.findById(1L).orElseThrow();
        String updateHashtag="#springboot";
        article.setHashtag(updateHashtag);


        Article savedArticle= articleRepository.saveAndFlush(article);


        //Then

        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updateHashtag);
    }

    @DisplayName("delete test")
    @Test
    void given_when_then_Delete(){

        Article article=articleRepository.findById(1L).orElseThrow();
        long previousArticleCount=articleRepository.count();
        long previousArticleCommentCount=articleCommentRepository.count();
        int deletedCommentSize=article.getArticleComments().size();




        articleRepository.delete(article);


        //Then

        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentSize);
    }


}
