package com.example.sparta_project.dto;

import com.example.sparta_project.entity.Blog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
public class BlogDto {
    @Getter
    public static class Request {
        private String author;
        private String contents;
        private String title;
        private String password;

        public Request(String author, String contents, String title, String password) {
            this.author = author;
            this.contents = contents;
            this.title = title;
            this.password = password;
        }
    }

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

    public static class SendMessage{
        private String message;
        public void sendMessage(String message)
        {
            this.message = message;
        }
    }
}
