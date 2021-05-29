package com.ehmaugbogo.easypref.util

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar



/**
 * Assisting extension functions
 *
 * @author Ehma Ugbogo
 * @version 1.0
 * @since 29 May 2021
 */


fun Activity.showSnackBar(message: String, length: Int = Snackbar.LENGTH_INDEFINITE) {
    val view = findViewById<View>(android.R.id.content).rootView
    showSnackBar(message,view, length)
}

private fun showSnackBar(message: String, view: View, length: Int = Snackbar.LENGTH_LONG): Snackbar {
    val snackBar = Snackbar.make(view, message, length)
    if(length == Snackbar.LENGTH_INDEFINITE) snackBar.setAction("Ok") {}
    snackBar.show()
    return snackBar
}