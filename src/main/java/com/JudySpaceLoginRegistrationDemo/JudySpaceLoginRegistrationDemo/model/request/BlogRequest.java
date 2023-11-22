package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogCategory;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogTag;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
public class BlogRequest {
    @NotNull(groups = {UpdateRequest.class}, message = "Cần chỉ ra id của Blog Khi thực hiện update")
    @Null(groups = {AddRequest.class}, message = "Không cần chỉ ra id của blog khi thực hiện thêm mới")
    private Integer blogId;
    private String title;
    private String blogThumbnail;
    private String caption;
    private boolean isDeleted;
    private BlogCategory blogCategory;
    private BlogTag blogTag;
    private Set<ParagraphRequest> paragraphs;
    private String youtubeLink;

    public interface AddRequest{

    }
    public interface UpdateRequest{

    }
}
