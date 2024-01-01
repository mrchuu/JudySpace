package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "music_playlist")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MusicPlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer musicPlaylistId;
    @Column(name = "playlist_name")
    private String playListName;
    @Column(name = "playlist_cover")
    private String playListCover;
    @Column(name = "create_date")
    @CreatedDate
    private Timestamp createDate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "music_playlist_link",
            joinColumns = @JoinColumn(name = "music_playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @JsonIgnoreProperties("playListBelongedTo")
    private List<Music> songsBelongedTo;
}
