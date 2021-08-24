package com.jdm.garam.util

import androidx.annotation.IdRes
import com.jdm.garam.R

enum class MainTabMenu(@IdRes val menuId: Int) {
    HOME(R.id.menu_home), THEATER(R.id.menu_theater), BUS(R.id.menu_bus), HISTORY_CLEAR(0)
}