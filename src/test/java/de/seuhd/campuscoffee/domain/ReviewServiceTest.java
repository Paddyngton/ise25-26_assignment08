package de.seuhd.campuscoffee.domain;

import de.seuhd.campuscoffee.domain.implementation.ReviewServiceImpl;
import de.seuhd.campuscoffee.domain.models.Review;
import de.seuhd.campuscoffee.domain.ports.data.ReviewDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewDataService reviewDataService;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview_Success() {
        Review review = new Review();
        when(reviewDataService.create(review)).thenReturn(review);

        Review createdReview = reviewService.create(review);

        assertNotNull(createdReview);
        verify(reviewDataService, times(1)).create(review);
    }

    @Test
    void testReadReview_Success() {
        Long reviewId = 1L;
        Review review = new Review();
        when(reviewDataService.read(reviewId)).thenReturn(Optional.of(review));

        Review result = reviewService.read(reviewId);

        assertNotNull(result);
        verify(reviewDataService, times(1)).read(reviewId);
    }

    @Test
    void testReadReview_Exception() {
        Long reviewId = 1L;
        when(reviewDataService.read(reviewId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> reviewService.read(reviewId));
        verify(reviewDataService, times(1)).read(reviewId);
    }

    @Test
    void testUpdateReview_Success() {
        Review review = new Review();
        when(reviewDataService.update(review)).thenReturn(review);

        Review updatedReview = reviewService.update(review);

        assertNotNull(updatedReview);
        verify(reviewDataService, times(1)).update(review);
    }

    @Test
    void testUpdateReview_Exception() {
        when(reviewDataService.update(null)).thenThrow(new IllegalArgumentException("Review cannot be null"));

        assertThrows(IllegalArgumentException.class, () -> reviewService.update(null));
        verify(reviewDataService, times(1)).update(null);
    }

    @Test
    void testDeleteReview_Success() {
        Long reviewId = 1L;

        reviewService.delete(reviewId);

        verify(reviewDataService, times(1)).delete(reviewId);
    }

    @Test
    void testFindAllReviews() {
        List<Review> reviews = List.of(new Review(), new Review());
        when(reviewDataService.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.findAll();

        assertEquals(2, result.size());
        verify(reviewDataService, times(1)).findAll();
    }
}