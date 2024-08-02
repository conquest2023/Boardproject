package org.demo1.boardproject.repository;

import org.demo1.boardproject.domain.Article;
import org.demo1.boardproject.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
