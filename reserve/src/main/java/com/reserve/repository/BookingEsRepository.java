package com.reserve.repository;

import com.reserve.model.BookingEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookingEsRepository extends ElasticsearchRepository<BookingEsEntity, String> {
}
