package com.app.movies.ui.presenter;

import com.app.movies.domain.interactor.GetPopularMoviesInteractor;
import com.app.movies.domain.model.Movie;
import com.app.movies.domain.model.MoviesData;
import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.view.PopularMoviesView;
import com.app.movies.ui.viewModel.MovieViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


public class PopularMoviesPresenterTest {

    @Mock
    private GetPopularMoviesInteractor getPopularMoviesInteractor;

    @Mock
    private MoviesViewModelMapper moviesViewModelMapper;

    @Mock
    private PopularMoviesView view;

    @Captor
    ArgumentCaptor<SingleObserver<MoviesData>> captor;

    @Mock
    private PopularMoviesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PopularMoviesPresenter(getPopularMoviesInteractor, moviesViewModelMapper);
        presenter.setView(view);
    }

    @Test
    public void viewShouldBeAttachedTest() throws Exception {
        Assert.assertTrue(presenter.isViewAttached());
    }

    @Test
    public void viewShouldNotBeAttachedTest() throws Exception{
        presenter.onDestroy();
        Assert.assertTrue(!presenter.isViewAttached());
    }

    @Test
    public void shouldShowMoviesWhenIsSuccessfulTest() throws Exception {
        //given
        List<Movie> movies = new ArrayList<>();
        MoviesData moviesData = new MoviesData(1, 1234, 4, movies);
        List<MovieViewModel> movieViewModels = new ArrayList<>();
        when(moviesViewModelMapper.transformMovies(movies)).thenReturn(movieViewModels);

        //when
        presenter.start();

        //then
        verify(view).showProgress();
        verify(getPopularMoviesInteractor).getPopularMovies(eq("1"), captor.capture());
        captor.getValue().onSuccess(moviesData);
        verify(view).hideProgress();
        verify(view).setUpMovies(movieViewModels);
        verifyNoMoreInteractions(view);
    }
}
