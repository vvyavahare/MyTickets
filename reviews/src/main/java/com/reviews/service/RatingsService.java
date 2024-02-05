package com.reviews.service;


import com.reviews.grpc.rating.RatingDto;
import com.reviews.grpc.rating.RatingSearchRequest;
import com.reviews.grpc.rating.RatingSearchResponse;
import com.reviews.grpc.rating.RatingServiceGrpc;
import com.reviews.repository.ReviewsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class RatingsService extends RatingServiceGrpc.RatingServiceImplBase {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public void getRatings(RatingSearchRequest request, StreamObserver<RatingSearchResponse> responseObserver) {
        List<RatingDto> ratingDtos = reviewsRepository.getRatingByStarOrderByRatingDesc(request.getStar().toString())
                .stream()
                .map(rating -> RatingDto.newBuilder().setRating(rating.getRating())
                        .setContent(rating.getContent())
                        .setFlightName(rating.getFlightName())
//                        .setStar(rating.getStar())
                        .build()).collect(Collectors.toList());

        responseObserver.onNext(RatingSearchResponse.newBuilder().addAllRating(ratingDtos).build());
        responseObserver.onCompleted();
    }
}
