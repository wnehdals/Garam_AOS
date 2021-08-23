package com.jdm.garam.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.databinding.ActivityMainBinding
import com.jdm.garam.ui.bus.BusFragment
import com.jdm.garam.ui.home.HomeFragment
import com.jdm.garam.ui.movie.TheaterFragment
import com.jdm.garam.util.MainTabMenu
import com.jdm.garam.util.MenuChangeEventBus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ViewBindingActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {
    override val layoutId = R.layout.activity_main
    private val viewModel: MainViewModel by viewModel()
    private val menuChangeEventBus by inject<MenuChangeEventBus>()

    override fun initState() {
        super.initState()
        lifecycleScope.launch {
            menuChangeEventBus.changeMenu(MainTabMenu.HOME)
        }
    }
    override fun initView() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        setAppBarTitle("")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.menu_theater -> {
                showFragment(TheaterFragment.newInstance(), TheaterFragment.TAG)
                true
            }
            R.id.menu_bus -> {
                showFragment(BusFragment.newInstance(), BusFragment.TAG)
                true
            }
            else -> false
        }
    }
    fun goToTab(mainTabMenu: MainTabMenu) {
        binding.bottomNav.selectedItemId = mainTabMenu.menuId
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun subscribe() {
        lifecycleScope.launch {
            menuChangeEventBus.mainTabMenuFlow.collect {
                goToTab(it)
            }
        }
    }

}