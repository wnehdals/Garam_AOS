package com.jdm.garam.ui.main

import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.databinding.ActivityMainBinding
import com.jdm.garam.ui.bus.BusFragment
import com.jdm.garam.ui.home.HomeFragment
import com.jdm.garam.ui.movie.TheaterFragment
import com.jdm.garam.ui.noti.NotiFragment
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
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var adLoader: AdLoader
    override fun initState() {
        super.initState()
        lifecycleScope.launch {
            menuChangeEventBus.changeMenu(MainTabMenu.HOME)
        }
    }
    override fun initView() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        setAppBarTitle("")
        MobileAds.initialize(this@MainActivity){}
        /* 전면광고 */
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this@MainActivity,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("main", "Ad was dismissed.")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d("main", "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d("main", "Ad showed fullscreen content.")
                mInterstitialAd = null
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)

                true
            }
            R.id.menu_theater -> {
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this)
                }
                showFragment(TheaterFragment.newInstance(), TheaterFragment.TAG)
                true
            }
            R.id.menu_bus -> {
                showFragment(BusFragment.newInstance(), BusFragment.TAG)
                true
            }
            R.id.menu_noti -> {
                showFragment(NotiFragment.newInstance(), NotiFragment.TAG)
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
            if(it is TheaterFragment) {
                it.setDispatcher()
            } else if(it is HomeFragment) {
                it.setDispatcher()
            } else if(it is BusFragment){
                it.setDispatcher()
            } else {
                (it as NotiFragment).setDispatcher()

            }
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

    override fun onStop() {
        super.onStop()
    }

}