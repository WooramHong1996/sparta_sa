package com.example.sparta_project.service;

import com.example.sparta_project.dto.BlogDto;
import com.example.sparta_project.dto.SendMessageDto;
import com.example.sparta_project.entity.Blog;
import com.example.sparta_project.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// Spring Framework Annotation
// 서비스 계층에서의 비즈니스 로직을 구현할 클래스에 붙여서 사용.
// @Service 가 붙은 클래스를 빈으로 자동 등록해서 다른 클래스에서 이 클래스의 기능을 사용할 수 있도록 함.
@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    // 요구사항1. 전체 게시글 목록 조회
    // (이때 RSP 로 전달해야하는 것이 List 가 아닌, DTO 로 전달해야함.)
    // @Transactional -> 다음 어노테이션이 적용된 메서드에서 수행되는 모든 작업은 하나의 트랜잭션 안에서 수행
    // 즉 한 메서드에서 수행되는 여러 작업이 실패할 경우 이전에 수행된 모든 작업이 롤백되어서 원상태로 돌아간다.
    @Transactional(readOnly = true)

    public List<BlogDto.Response> getBlogs() {
        List <Blog> blogList = blogRepository.findAllByOrderByCreatedAtAsc();
        List <BlogDto.Response> blogResponseDtoList = new ArrayList<>();
        for (Blog blog : blogList) {
            BlogDto.Response tmp = new BlogDto.Response(blog);
            blogResponseDtoList.add(tmp);
        }
        return blogResponseDtoList;
    }

    // 요구사항2. 게시글 작성
    @Transactional
    public BlogDto.Response createBlog(BlogDto.Request requestDto) {
        Blog blog = new Blog(requestDto);
        blogRepository.save(blog);
        BlogDto.Response blogResponseDto = new BlogDto.Response(blog);
        return blogResponseDto;
    }


    // 요구사항3. 선택한 게시글 조회
    @Transactional(readOnly = true)
    public BlogDto.Response getBlogs(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당글이 없습니다.")
        );
        BlogDto.Response blogResponseDto = new BlogDto.Response(blog);
        return blogResponseDto;
    }

    // 요구사항4. 선택한 게시글 수정
    @Transactional
    public BlogDto.Response updateBlog(Long id, BlogDto.Request requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new RuntimeException("아이디가 존재하지 않습니다.")
        );
        if(requestDto.getPassword().equals(blog.getPassword()))
        {
            blog.update(requestDto);
        }
        BlogDto.Response blogResponseDto = new BlogDto.Response(blog);
        return blogResponseDto;
    }

    // 요구사항5. 선택한 게시글 삭제
    @Transactional
    public SendMessageDto deleteBlog(Long id, BlogDto.Request requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        SendMessageDto sendMessageDto = new SendMessageDto();
        if(requestDto.getPassword().equals(blog.getPassword()))
        {
            blogRepository.deleteById(id);
            sendMessageDto.sendMessage("삭제완료");
            return sendMessageDto;
        }
        else{
            sendMessageDto.sendMessage("삭제실패");
            return sendMessageDto;
        }

    }

}
