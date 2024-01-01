package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "music")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;
    @Column(name = "song_name")
    private String songName;
    @Column(name = "artist_name")
    private String artistName;
    @Column(name = "spotify_embeded_link")
    private String spotifyEmbededLink;
    @Column(name = "quote")
    private String quote;
    @Column(name = "music_art")
    private String musicArt;
    @Column(name = "create_date")
    @CreatedDate
    private Timestamp createDate;
    @ManyToMany(mappedBy = "songsBelongedTo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MusicPlayList> playListBelongedTo;
}
