package com.ehmaugbogo.easypreflibrary

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.DoubleToLongFunction
import java.util.function.LongToDoubleFunction


/**
 * Singleton class to Create EasyPref Object
 *
 * @author Ehma Ugbogo
 * @version 1.0
 * @since 29 May 2021
 */


class PrefObjectHolder<T>(
    val value: T? = null
)


object EasyPref {
    private lateinit var pref: SharedPreferences
    private val editor get() = pref.edit()
    private var initialized = AtomicBoolean(false)
    private val observers = mutableMapOf<String, MutableLiveData<out Any>>()


    /**
     * Should be called first and just once in your Application or in MainActivity class [activity.application].
     * It serves as the feature's init setup
     *
     * @param application
     */
    @JvmStatic
    fun initialize(application: Application) = initPref(application)


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
     *  @see android.content.SharedPreferences.Editor#putInt(String, Int)
     */
    @JvmStatic
    fun putInt(key: String, value: Int) {
        assertInitialized()
        editor.putInt(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save [Int] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *
     */
    @JvmStatic
    fun putDouble(key: String, value: Double) {
        assertInitialized()
        editor.putLong(key, value.toLong()).apply()
        observe(key, value)
    }

    /**
     * Use to save [Long] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    @JvmStatic
    fun putLong(key: String, value: Long) {
        assertInitialized()
        editor.putLong(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save [Float] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putFloat(String, Float)
     */
    @JvmStatic
    fun putFloat(key: String, value: Float) {
        assertInitialized()
        editor.putFloat(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save [String] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putString(String, String)
     */
    @JvmStatic
    fun putString(key: String, value: String) {
        assertInitialized()
        editor.putString(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save [Boolean] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putBoolean(String, Boolean)
     */
    @JvmStatic
    fun putBoolean(key: String, value: Boolean) {
        assertInitialized()
        editor.putBoolean(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save Set<String> values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putStringSet(String, MutableSet<String>)
     */
    @JvmStatic
    fun putStringSet(key: String, value: MutableSet<String>) {
        assertInitialized()
        editor.putStringSet(key, value).apply()
        observe(key, value)
    }

    /**
     * Use to save [Any] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    @JvmStatic
    fun <T> putObject(key: String, obj: T) {
        val json = Gson().toJson(obj)
        editor.putString(key, json).apply()
        observe(key, PrefObjectHolder(obj))
    }

    private inline fun <reified T> observe(key: String, value: T) {
        if (keyIsObservable(key)) {
            val oldValueOrNullAtFirst = observers[key]?.value

            oldValueOrNullAtFirst?.let { OldValue->
                if (!isSameInstance<T>(OldValue)) throwTypeUnmatchedException()
            }

            observers[key]?.value = value
        }
    }

    private fun keyIsObservable(key: String) = observers[key] != null



    // Getters

    /**
     * Use to get stored [Int] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#getInt(String, Int)
     */
    @JvmStatic
    fun getInt(key: String): Int {
        assertInitialized()
        return pref.getInt(key, 0)
    }

    /**
     * Use to get stored [Double] values to SharedPreferences
     *
     *  @param key
     *  @param value
     */
    @JvmStatic
    fun getDouble(key: String): Double {
        assertInitialized()
        return getLong(key).toDouble()
    }

    /**
     * Use to get stored [Long] values to SharedPreferences
     *
     *  @param key
     *  @see android.content.SharedPreferences.Editor#getLong(String, long)
     */
    @JvmStatic
    fun getLong(key: String): Long {
        assertInitialized()
        return pref.getLong(key, 0)
    }

    /**
     * Use to get stored [Float] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#getFloat(String, Float)
     */
    @JvmStatic
    fun getFloat(key: String): Float {
        assertInitialized()
        return pref.getFloat(key, 0f)
    }

    /**
     * Use to get stored [String] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    @JvmStatic
    fun getString(key: String, defValue: String? = null): String? {
        assertInitialized()
        return pref.getString(key, defValue)
    }

    /**
     * Use to get stored [Boolean] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    @JvmStatic
    fun getBoolean(key: String): Boolean {
        assertInitialized()
        return pref.getBoolean(key, false)
    }

    /**
     * Use to get stored Set<String> values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    @JvmStatic
    fun getStringSet(key: String): MutableSet<String>? {
        assertInitialized()
        return pref.getStringSet(key, mutableSetOf())
    }

    /**
     * Use to get stored [Any] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *
     */
    @JvmStatic
    fun <T> getObject(key: String, classOfObj: Class<T>): T? {
        val jsonString = getString(key)
        if (jsonString.isNullOrEmpty()) return null
        return Gson().fromJson(jsonString, classOfObj)
    }



    // OBSERVERS

    /*Singular Exchangeable observers*/

    /**
     * Use to get stored [Any] values to SharedPreferences
     *
     *  @param key
     *  @param value
     *  ClassCastException
     *
     */
    @JvmStatic
    fun observeInt(key: String): LiveData<Int> = MutableLiveData<Int>().also {
        it.value = getInt(key)
        observers[key] = it
    }

    @JvmStatic
    fun observeDouble(key: String): LiveData<Double> = MutableLiveData<Double>().also {
        it.value = getLong(key).toDouble()
        observers[key] = it
    }

    @JvmStatic
    fun observeLong(key: String): LiveData<Long> = MutableLiveData<Long>().also {
        it.value = getLong(key)
        observers[key] = it
    }

    @JvmStatic
    fun observeFloat(key: String): LiveData<Float> = MutableLiveData<Float>().also {
        it.value = getFloat(key)
        observers[key] = it
    }

    @JvmStatic
    fun observeString(key: String): LiveData<String> = MutableLiveData<String>().also {
        val value = getString(key)
        if(value != null) it.value = value

        observers[key] = it
    }

    @JvmStatic
    fun observeBoolean(key: String): LiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = getBoolean(key)
        observers[key] = it
    }

    @JvmStatic
    fun observeStringSet(key: String): LiveData<MutableSet<String>> = MutableLiveData<MutableSet<String>>().also {
        val value = getStringSet(key)
        it.value = value

        observers[key] = it
    }

    @SuppressWarnings("Unchecked Casts")
    @JvmStatic
    fun <T> observeObject(key: String, classOfObj: Class<T>): LiveData<PrefObjectHolder<T>> =
        MutableLiveData<PrefObjectHolder<T>>().also {

            if(keyIsObservable(key)){ // (On subsequent observation) Ensuring old stored Type is same with incoming type
                val oldObjType = observers[key]?.value
                val isSameType = isSameInstance<PrefObjectHolder<T>>(oldObjType)
                if (!isSameType) throwTypeUnmatchedException()
            }

            val value = getObject(key, classOfObj)
            it.value = PrefObjectHolder(value)

            observers[key] = it
        }


    /**
     * Check if incoming value is same type as the previously stored value
     * @param obj
     */
    private inline fun <reified T> isSameInstance(obj: Any?) = obj is T



    /**
     * Asertation method [assertInitialized] to ensure class was initialized before used
     *
     */
    private fun assertInitialized() {
        if (!initialized.get()) throwInitializationException()
    }

    private fun throwInitializationException() {
        throw EasyPrefException("Please initialize EasyPref in onCreate() of your Application class or in MainActivity")
    }

    private fun throwTypeUnmatchedException() {
        val e = EasyPrefException("Key mismatch. (1) Ensure you're not using same key to store different types")
        //throw e
        println(e)
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
