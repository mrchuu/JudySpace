package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Set;

@Table(name = "movie_playlist")
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MoviePlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "playlist_name")
    private String playListName;
    @Column(name = "create_date")
    @CreatedDate
    private Instant createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;
    @ManyToMany(mappedBy = "playListOfMovie", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("playListOfMovie")
    private Set<Blog> moviesOfPlaylist;
}
