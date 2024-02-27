package org.example.assignment.article.repository

import org.example.assignment.article.entity.Article
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByWrittenAtDesc(): Iterable<Article>
}