package org.example.assignment.repository

import org.example.assignment.entity.Article
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByWrotedAtDesc(): Iterable<Article>
}