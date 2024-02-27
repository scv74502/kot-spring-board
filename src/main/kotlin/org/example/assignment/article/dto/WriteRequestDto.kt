package org.example.assignment.article.dto

import io.swagger.annotations.ApiModelProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.example.assignment.member.entity.Member
import javax.persistence.Lob

data class WriteRequestDto (
    @Schema(description = "게시글의 제목", example="맥북 에어 팝니다")
    @ApiModelProperty(value = "게시글의 제목")
    val title: String,

    @Schema(description = "작성한 게시글의 내용", example="2018년 인텔맥 팝니다 .... etc")
    @ApiModelProperty(value = "게시글 내용 이야기")
    @Lob
    val content: String
)


data class ArticleUpdateRequestDto (
    @Schema(description = "업데이트 할 게시글의 아이디", example="1")
    @ApiModelProperty(value = "업데이트 할 게시글 아이디")
    val id: Long,

    @Schema(description = "게시글의 제목", example="맥북 에어 팝니다")
    @ApiModelProperty(value = "게시글의 제목")
    val title: String,

    @Schema(description = "작성한 게시글의 내용", example="2018년 인텔맥 팝니다 .... etc")
    @ApiModelProperty(value = "게시글 내용")
    @Lob
    val content: String
)