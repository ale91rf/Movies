package com.app.movies.ui.presenter;

import com.app.movies.domain.interactor.GetMoviesByQueryInteractor;
import com.app.movies.domain.model.Movie;
import com.app.movies.domain.model.MoviesData;
import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.view.SearchMoviesView;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchMoviesPresenterTest {

    @Mock
    private GetMoviesByQueryInteractor getMoviesByQueryInteractor;

    @Mock
    private MoviesViewModelMapper moviesViewModelMapper;

    @Mock
    private SearchMoviesView view;

    @Captor
    ArgumentCaptor<SingleObserver<MoviesData>> captor;

    @Mock
    Throwable throwable;

    @Mock
    private SearchMoviesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchMoviesPresenter(moviesViewModelMapper, getMoviesByQueryInteractor);
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
    public void shouldShowAEmptyListWhenSizeOfQueryIsZero() throws Exception{
        //when
        presenter.getMoviesByQuery("");

        //then
        verify(view).setUpMovies(eq(new ArrayList<>()));
    }

    @Test
    public void shouldShowMoviesWhenRequestByQueryIsSuccessfulTest() throws Exception {
        //given
        List<Movie> movies = new ArrayList<>();
        MoviesData moviesData = new MoviesData(1, 1234, 4, movies);
        List<MovieViewModel> movieViewModels = new ArrayList<>();
        when(moviesViewModelMapper.transformMovies(movies)).thenReturn(movieViewModels);

        //when
        presenter.getMoviesByQuery("split");

        //then
        verify(view).showProgress();
        verify(getMoviesByQueryInteractor).getMoviesByQuery(eq("1"), eq("split"), captor.capture());
        captor.getValue().onSuccess(moviesData);
        verify(view).hideProgress();
        verify(view).setUpMovies(movieViewModels);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void shouldShowErrorWhenThereIsAnErrorGettingMoviesTest() throws Exception {
        //when
        presenter.getMoviesByQuery("split");

        //then
        verify(view).showProgress();
        verify(getMoviesByQueryInteractor).getMoviesByQuery(eq("1"), eq("split"), captor.capture());
        captor.getValue().onError(throwable);
        verify(view).hideProgress();
        verify(view).showError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void shouldAddMoreMovies() throws Exception {
        //given
        List<Movie> movies = new ArrayList<>();
        MoviesData moviesData = new MoviesData(1, 1234, 4, movies);
        List<MovieViewModel> movieViewModels = new ArrayList<>();
        when(moviesViewModelMapper.transformMovies(movies)).thenReturn(movieViewModels);
        presenter.getMoviesByQuery("split");
        verify(getMoviesByQueryInteractor).getMoviesByQuery(eq("1"), eq("split"), captor.capture());
        captor.getValue().onSuccess(moviesData);
        verify(view).setUpMovies(movieViewModels);

        //when
        presenter.onGetMoreMovies();

        //then
        verify(view, times(2)).showProgress();
        verify(getMoviesByQueryInteractor).getMoviesByQuery(eq("2"), eq("split"), captor.capture());
        captor.getValue().onSuccess(moviesData);
        verify(view, times(2)).hideProgress();
        verify(view).addMovies(movieViewModels);
    }
}