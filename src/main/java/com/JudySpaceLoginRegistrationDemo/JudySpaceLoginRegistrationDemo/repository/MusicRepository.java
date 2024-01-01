package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
}
