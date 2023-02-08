package com.example.sparta_project.dto;

import com.example.sparta_project.entity.Blog;
import lombok.*;

import java.time.LocalDateTime;

//이너 dto로 수정(하면서 안에 코드들 다 수정했어요 ㅠㅠ)
public class BlogDto {

    @Getter
    public static class Request{
        private String author;
        private String contents;
        private String title;
        private String password;

    }

    @Getter
    public static class Response{
        private Long id;
        private String title;
        private String contents;
        private String author;
        private LocalDateTime createdat;
        private LocalDateTime modifiedat;

        public Response(Blog blog) {
            this.id = blog.getId();
            this.title = blog.getTitle();
            this.contents = blog.getContents();
            this.author = blog.getAuthor();
            this.createdat = blog.getCreatedAt();
            this.modifiedat = blog.getModifiedAt();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SendMessage{
        private String message;
    }
}
