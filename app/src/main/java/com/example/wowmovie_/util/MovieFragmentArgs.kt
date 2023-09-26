package com.example.wowmovie_.util

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException

class MovieFragmentArgs (
    public val movieId: String = "\"1\"",
) : NavArgs {
    public fun toBundle(): Bundle {
        val result = Bundle()
        result.putString("movieId", this.movieId)
        return result
    }

    public fun toSavedStateHandle(): SavedStateHandle {
        val result = SavedStateHandle()
        result.set("movieId", this.movieId)
        return result
    }

    public companion object {
        @JvmStatic
        public fun fromBundle(bundle: Bundle): MovieFragmentArgs {
            bundle.setClassLoader(MovieFragmentArgs::class.java.classLoader)
            val __movieId : String?
            if (bundle.containsKey("movieId")) {
                __movieId = bundle.getString("movieId")
                if (__movieId == null) {
                    throw IllegalArgumentException("Argument \"movieId\" is marked as non-null but was passed a null value.")
                }
            } else {
                __movieId = "\"1\""
            }
            return MovieFragmentArgs(__movieId)
        }

        @JvmStatic
        public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): MovieFragmentArgs {
            val __movieId : String?
            if (savedStateHandle.contains("movieId")) {
                __movieId = savedStateHandle["movieId"]
                if (__movieId == null) {
                    throw IllegalArgumentException("Argument \"movieId\" is marked as non-null but was passed a null value")
                }
            } else {
                __movieId = "\"1\""
            }
            return MovieFragmentArgs(__movieId)
        }
    }
}