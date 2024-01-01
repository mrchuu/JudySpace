package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MusicDTO {
    private Integer songId;
    private String songName;
    private String artistName;
    private String spotifyEmbededLink;
    private String quote;
    private Timestamp createDate;
}
