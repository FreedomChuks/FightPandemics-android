package com.fightpandemics.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    fun launchActivity(
        clazz: Class<*>,
        finishAll: Boolean,
        extra: Bundle?,
        requestCode: Int?
    ) {
        Intent(this, clazz).apply {
            if (finishAll) {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            extra?.run { putExtras(this) }
        }.run {
            if (requestCode == null) {
                startActivity(this)
            } else {
                startActivityForResult(this, requestCode)
            }
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, container: Int) {
        if (!isFinishing) {
            supportFragmentManager?.beginTransaction()?.apply {
                replace(container, fragment, fragment::class.java.simpleName)
                if (addToBackStack) {
                    addToBackStack(null)
                }
            }?.commitAllowingStateLoss()
        }
    }

}