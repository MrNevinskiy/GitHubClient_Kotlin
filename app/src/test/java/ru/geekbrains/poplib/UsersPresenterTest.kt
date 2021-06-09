package ru.geekbrains.poplib

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.poplib.mvp.presenter.UsersPresenter
import ru.terrakok.cicerone.Router
import java.util.*

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    private val scheduler = Schedulers.trampoline()

    @Mock
    lateinit var userRepo: IGithubUsersRepo

    @Mock
    lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = UsersPresenter(userRepo, router, scheduler)
    }

    @Test
    fun navigateBack_Test() {
        presenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test
    fun loadData_Test() {
        `when`(userRepo.getUsers()).thenReturn(Single.just(ArrayList()))
        presenter.loadData()
        assertNotNull(presenter.usersListPresenter.users)
        assertEquals(ArrayList<GithubUser>(), presenter.usersListPresenter.users)
        assertEquals(0, presenter.usersListPresenter.getCount())
    }
}