package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotNull(groups = {UpdateRequest.class})
    @Null(groups = {AddChildrenRequest.class, AddRootRequest.class}, message = "Id không cần được định ra khi thêm mới")
    private Integer commentId;
    @NotBlank(groups = {AddChildrenRequest.class, AddRootRequest.class}, message = "nội dung không được để trống khi tạo mới")
    private String content;
    private boolean isRoot;
    @NotNull(groups = {AddRootRequest.class}, message = "id bài đăng không được trống")
    private Blog blogRepliedTo;
    @NotNull(groups = {AddChildrenRequest.class}, message = "id comment không được để trống")
    private Comment parentComment;
    private Instant updateDate;
    public interface AddRootRequest{

    }
    public interface AddChildrenRequest{

    }
    public interface UpdateRequest{

    }
}
