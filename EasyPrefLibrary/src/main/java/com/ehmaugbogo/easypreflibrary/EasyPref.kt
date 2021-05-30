package com.ehmaugbogo.easypreflibrary

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.concurrent.atomic.AtomicBoolean



/**
 * Singleton class to Create EasyPref Object
 *
 * @author Ehma Ugbogo
 * @version 1.0
 * @since 29 May 2021
 */


object EasyPref {
    private lateinit var pref: SharedPreferences
    private val editor get() = pref.edit()
    private var initialized = AtomicBoolean(false)


    /**
     * Should be called first and just once in your Application or in MainActivity class [activity.application].
     * It serves as the feature's init setup
     *
     * @param application
     */
    @JvmStatic fun initialize(application: Application) = initPref(application)


    private fun initPref(application: Application) {
        pref = application.applicationContext.getSharedPreferences(
            "${application.packageName}_SharePref",
            Context.MODE_PRIVATE
        )
        initialized.set(true)
    }


    // Setters


    /**
     * Use to save [Int] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putInt(key: String, value: Int) {
        assertInitialized()
        editor.putInt(key, value).apply()
    }

    /**
     * Use to save [Long] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putLong(key: String, value: Long) {
        assertInitialized()
        editor.putLong(key, value).apply()
    }

    /**
     * Use to save [Float] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putFloat(key: String, value: Float) {
        assertInitialized()
        editor.putFloat(key, value).apply()
    }

    /**
     * Use to save [String] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putString(key: String, value: String) {
        assertInitialized()
        editor.putString(key, value).apply()
    }

    /**
     * Use to save [Boolean] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putBoolean(key: String, value: Boolean) {
        assertInitialized()
        editor.putBoolean(key, value).apply()
    }

    /**
     * Use to save Set<String> values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun putStringSet(key: String, data: MutableSet<String>) {
        assertInitialized()
        editor.putStringSet(key, data).apply()
    }


    // Getters

    /**
     * Use to get stored [Int] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getInt(key: String): Int {
        assertInitialized()
        return pref.getInt(key, 0)
    }

    /**
     * Use to get stored [Long] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getLong(key: String, value: Long): Long {
        assertInitialized()
        return pref.getLong(key, 0)
    }

    /**
     * Use to get stored [Float] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getFloat(key: String): Float {
        assertInitialized()
        return pref.getFloat(key, 0f)
    }

    /**
     * Use to get stored [String] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getString(key: String, defValue: String? = null): String? {
        assertInitialized()
        return pref.getString(key, defValue)
    }

    /**
     * Use to get stored [Boolean] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getBoolean(key: String): Boolean {
        assertInitialized()
        return pref.getBoolean(key, false)
    }

    /**
     * Use to get stored Set<String> values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun getStringSet(key: String): MutableSet<String>? {
        assertInitialized()
        return pref.getStringSet(key, mutableSetOf())
    }

    /**
     * Use to save [Any] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun <T> putObject(key: String, obj: T) {
        val json = Gson().toJson(obj)
        putString(key, json)
    }

    /**
     * Use to get stored [Any] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic fun <T> getObject(key: String, classOfObj: Class<T>): T? {
        val jsonString = getString(key)
        if (jsonString.isNullOrEmpty()) return null
        return Gson().fromJson(jsonString, classOfObj)
    }

    /**
     * Asertation method [assertInitialized] to ensure class was initialized before used
     *
     */
    private fun assertInitialized() {
        if (!initialized.get())
            throw EasyPrefException("Please initialize EasyPref in onCreate() of your Application class or in MainActivity")
    }


    /**
     * Exception extending Class to reflect EasyPref Exception
     *
     * @author Ehma Ugbogo
     * @version 1.0
     * @since 29 May 2021
     */
    internal class EasyPrefException(msg: String) : Exception(msg)

}
