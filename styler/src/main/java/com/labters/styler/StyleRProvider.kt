/*
 * *
 *  * Created by Ali YUCE on 10/18/20 9:16 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/18/20 8:35 PM
 *  * GitHub: https://github.com/mayuce
 *
 */

package com.labters.styler

import android.view.View
import java.util.*

open class StyleRProvider {

    companion object {
        private const val KEY_PARENT_DELIMITER = "."

        internal var viewSetter: ((view: View, map: HashMap<String, String>) -> Unit)? = null

        private var viewStyleRProvider: HashMap<String, List<HashMap<String, String>>>? = null

        private var colorProvider: ((colorKey: String?) -> Int?)? = null

        private var stylesProvider: HashMap<String, HashMap<String, String>>? = null

        /**
         * Initializes the StyleR
         * @param viewStyleRProvider: View Styles Map
         * @param colorProvider: Colors Map
         * @param stylesProvider: Styles Map
         * @param viewSetter: View Binding provider, create your view binders and provide it
         */
        fun initialize(
            viewStyleRProvider: HashMap<String, List<HashMap<String, String>>>?,
            colorProvider: ((colorKey: String?) -> Int?)?,
            stylesProvider: HashMap<String, HashMap<String, String>>?,
            viewSetter: ((view: View, map: HashMap<String, String>) -> Unit)? = null
        ) {
            viewStyleRProvider?.let {
                this@Companion.viewStyleRProvider = it
            }
            colorProvider?.let {
                this@Companion.colorProvider = it
            }
            stylesProvider?.let {
                this@Companion.stylesProvider = it
            }
            viewSetter?.let {
                this@Companion.viewSetter = it
            }
        }

        /**
         * Destruct the StyleR
         */
        fun destructor() {
            this@Companion.viewStyleRProvider = null
            this@Companion.colorProvider = null
            this@Companion.stylesProvider = null
            this@Companion.viewSetter = null
        }

        /**
         * Provides the view style from viewStyleRProvider map with given key
         * @param key: View Style Key
         */
        fun getViewStyle(key: String?) = if (key?.contains(KEY_PARENT_DELIMITER) == true) {
            val tokenizer = StringTokenizer(key, KEY_PARENT_DELIMITER)
            var style = tokenizer.nextToken()
            val mergedStyle = getViewKeyStyle(style) as? ArrayList
            while (tokenizer.hasMoreTokens()) {
                style += ".${tokenizer.nextToken()}"
                getViewKeyStyle(style)?.let {
                    mergedStyle?.addAll(it)
                }
            }
            mergedStyle
        } else {
            getViewKeyStyle(key)
        }

        /**
         * Provides the color from colorProvider map with given key
         * @param key: Color Key
         */
        fun getColor(key: String?) = colorProvider?.invoke(key)

        /**
         * Provides the style from stylesProvider map with given key
         * @param key: Style Key
         */
        fun getStyle(key: String?): HashMap<String, String>? =
            if (key?.contains(KEY_PARENT_DELIMITER) == true) {
                val tokenizer = StringTokenizer(key, KEY_PARENT_DELIMITER)
                var style = tokenizer.nextToken()
                val mergedStyle = getStyleKey(style)
                while (tokenizer.hasMoreTokens()) {
                    style += ".${tokenizer.nextToken()}"
                    getStyleKey(style)?.let {
                        mergedStyle?.putAll(it)
                    }
                }
                mergedStyle
            } else {
                getStyleKey(key)
            }

        private fun getViewKeyStyle(key: String?) =
            (viewStyleRProvider?.get(key) as? ArrayList)?.clone() as? List<HashMap<String, String>>?

        private fun getStyleKey(key: String?) =
            stylesProvider?.get(key)?.clone() as? HashMap<String, String>?
    }

}