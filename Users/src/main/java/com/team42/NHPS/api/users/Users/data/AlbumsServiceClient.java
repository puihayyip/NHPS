package com.team42.NHPS.api.users.Users.data;

import com.team42.NHPS.api.users.Users.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {

    @GetMapping(value = "users/{userId}/albums")
    @Retry(name = "albums-ws")
    @CircuitBreaker(name = "albums-ws", fallbackMethod = "getAlbumsFallback")
    public List<AlbumResponseModel> getAlbums(@PathVariable String userId);

    default List<AlbumResponseModel> getAlbumsFallback(String id, Throwable exception) {
        System.out.println("Param = " + id);
        System.out.println("Exception class = " + exception.getClass().getName());
        System.out.println("Exception took place = " + exception.getMessage());
        return new ArrayList<>();
    }
}
