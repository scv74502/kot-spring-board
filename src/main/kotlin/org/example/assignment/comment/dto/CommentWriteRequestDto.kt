package org.example.assignment.comment.dto

import lombok.AccessLevel
import lombok.NoArgsConstructor

@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class CommentWriteRequestDto(
    val memberId: Long,
    val articleId: Long,
    val parentCommentId: Long?,
    val content: String
) {

}