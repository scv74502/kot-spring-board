package org.example.assignment.article.service

import javassist.NotFoundException
import org.example.assignment.article.dto.*
import org.example.assignment.article.entity.Article
import org.example.assignment.article.repository.ArticleRepository
import org.example.assignment.member.entity.Member
import org.example.assignment.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ArticleService (
    private val articleRepository: ArticleRepository,
    private val memberRepository: MemberRepository
) {
    // 게시글 고유 아이디값으로 게시글 찾아오기
    @Transactional
    fun getArticleInfo(id: Long): Article {
        return articleRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("존재하지 않는 게시물입니다")
    }


    @Transactional
    fun writeArticle(writerId:Long, requestDto: WriteRequestDto): WriteResponseDto {
        val writer:Member = memberRepository.findByIdOrNull(writerId) ?: throw IllegalArgumentException("잘못된 게시글 작성자입니다")
        println("------------------ proceeded ------------------")
        return WriteResponseDto.from(articleRepository.save(Article.from(requestDto, writer)), writer.loginId)
    }

    @Transactional
    fun updateArticle(writerId: Long, request: ArticleUpdateRequestDto):ArticleUpdateResponseDto {
        val member: Member = memberRepository.findByIdOrNull(writerId) ?: throw NotFoundException("잘못된 게시글 작성자입니다")
        val article: Article = articleRepository.findByIdOrNull(request.id) ?: throw NotFoundException("잘못된 게시글 검색 요청입니다")
        if(member != article.member) {
            throw IllegalArgumentException("사용자 번호 ${member.id}와, 게시글 작성자 번호 ${article.member.id}가 다릅니다")
        }

        article.update(request)
        articleRepository.save(article)
        return ArticleUpdateResponseDto.of(true, article)
    }

    @Transactional
    fun deleteArticle(writerId: Long, request: ArticleUpdateRequestDto):ArticleDeleteResponseDto {
        val member: Member = memberRepository.findByIdOrNull(writerId) ?: throw NotFoundException("잘못된 게시글 작성자입니다")
        val article: Article = articleRepository.findByIdOrNull(request.id) ?: throw NotFoundException("잘못된 게시글 검색 요청입니다")
        if(member != article.member) {
            throw IllegalArgumentException("사용자 번호 ${member.id}와, 게시글 작성자 번호 ${article.member.id}가 다릅니다")
        }

        article.isDeleted = true
        article.deletedDate = LocalDateTime.now()

        articleRepository.save(article)

        return ArticleDeleteResponseDto.of(true, article)
    }

}