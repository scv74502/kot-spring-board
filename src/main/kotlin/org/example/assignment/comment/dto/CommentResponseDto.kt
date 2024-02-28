package org.example.assignment.comment.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.comment.entity.Comment
import org.example.assignment.member.dto.MemberInfoResponseDto
import java.time.LocalDateTime

data class CommentResponseDto(
    @Schema(description = "댓글의 고유 번호", example = "2")
    val id: Long,

    @Schema(description = "상위 댓글의 고유 번호", example = "1")
    val parentId: Long?,

    @Schema(description = "댓글이 작성된 게시글의 고유 번호", example = "2")
    val articleId: Long,

    @Schema(description = "댓글 작성한 사람의 로그인 아이디", example = "test")
    val memberLoginId: String, // writer of each article

    @Schema(description = "댓글 작성된 게시글의 제목", example = "mba18 16gb 512gb 팝니다")
    val title: String,

    @Schema()
    val writtenAt: LocalDateTime
) {
    companion object{
        fun fromEntity(comment: Comment) = CommentResponseDto(
            id = comment.id!!,
            articleId = comment.article.id!!,
            memberLoginId = comment.member.loginId,
            parentId = comment.parent?.id,
            title = comment.article.title,
            writtenAt = comment.writtenAt
        )
    }
}


data class CommentInfoResponseDto (
    val id: Long,
    val content: String,
    val writer: MemberInfoResponseDto,
    val children: MutableList<CommentResponseDto> = ArrayList()
) {

}
