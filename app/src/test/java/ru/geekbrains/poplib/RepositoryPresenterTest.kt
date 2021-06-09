package ru.geekbrains.poplib

import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.presenter.RepositoryPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenterTest {

    private lateinit var presenter: RepositoryPresenter

    @Mock
    lateinit var repo : GithubRepository

    @Mock
    lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = RepositoryPresenter(repo, router)
    }

    @Test
    fun navigateBack_Test() {
        presenter.backPressed()
        Mockito.verify(router, Mockito.times(1)).exit()
    }


}
