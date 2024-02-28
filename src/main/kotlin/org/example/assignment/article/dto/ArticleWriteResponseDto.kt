package org.example.assignment.article.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.article.entity.Article
import java.time.LocalDateTime
import javax.persistence.Lob


data class ArticleWriteResponseDto(
    @Schema(description = "게시글의 고유 번호", example = "1")
    val id: Long,

    @Schema(description = "게시글의 제목", example = "mba18 16gb 512gb 팝니다")
    val title: String,

    @Schema()
    val writtenAt: LocalDateTime,

    @Schema()
    val memberLoginId: String // writer of each article
) {
    companion object {
        fun from(article: Article, loginId: String) = ArticleWriteResponseDto(
            id = article.id!!,
            title = article.title,
            writtenAt = article.writtenAt,
            memberLoginId = loginId
        )
    }
}

data class ArticleUpdateResponseDto(
    @Schema(description = "게시글의 정보수정 성공 여부", example = "true")
    var result: Boolean,

    @Schema(description = "게시글의 고유 번호", example = "1")
    val id: Long,

    @Schema(description = "수정된 게시글의 제목", example = "가격내림)mba18 16gb 512gb 팝니다")
    val title: String?,

    @Lob
    @Schema(description = "게시글의 정보수정 성공 여부", example = "true")
    val content: String?,

    @Schema(description = "게시글이 수정된 일시", example = "2024-01-01 13:00:00")
    var updatedAt: LocalDateTime
) {
    companion object {
        fun of(result: Boolean, article: Article) = ArticleUpdateResponseDto(
            result = result,
            id = article.id!!,
            title = article.title,
            content = article.content,
            updatedAt = LocalDateTime.now()
        )
    }
}

data class ArticleDeleteResponseDto(
    @Schema(description = "게시글의 삭제 성공 여부", example = "true")
    val result: Boolean,

    @Schema(description = "삭제된 게시글 고유번호", example = "1")
    val id: Long,

    @Schema(description = "삭제된 게시글의 제목", example = "2018 mba 팝니다")
    val title: String
) {
    companion object {
        fun of(result: Boolean, article: Article) = ArticleDeleteResponseDto(
            result = result,
            id = article.id!!,
            title = article.title
        )
    }
}
