package ru.geekbrains.poplib

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.presenter.UserPresenter
import ru.terrakok.cicerone.Router
import java.util.ArrayList

class UserPresenterTest {

    private lateinit var presenter: UserPresenter

    private val scheduler = Schedulers.trampoline()

    @Mock
    lateinit var router: Router

    @Mock
    lateinit var repositoriesRepo: IGithubRepositoriesRepo

    @Mock
    lateinit var user: GithubUser

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UserPresenter(repositoriesRepo, router, scheduler, user)
    }

    @Test
    fun navigateBack_Test() {
        presenter.backPressed()
        Mockito.verify(router, Mockito.times(1)).exit()
    }

    @Test
    fun loadData_Test() {
        `when`(repositoriesRepo.getRepositories(user)).thenReturn(Single.just(ArrayList()))
        presenter.loadData()
        Assert.assertNotNull(presenter.repositoriesListPresenter.repositories)
        Assert.assertEquals(ArrayList<GithubRepository>(),presenter.repositoriesListPresenter.repositories)
        Assert.assertEquals(0,presenter.repositoriesListPresenter.getCount())
    }

}