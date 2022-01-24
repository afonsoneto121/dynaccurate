package com.br.dynaccurate.dynaccuratesolution.repository;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    @Query(" {'nicknameUser' : ?0, '$and' :[ {'dateTime': {$gte: ?2}}, {'dateTime':{$lte: ?1}} ] }")
    Page<Event> findEventByUser(String nicknameUser, LocalDateTime toDate, LocalDateTime fromDate, Pageable pageable);
}
