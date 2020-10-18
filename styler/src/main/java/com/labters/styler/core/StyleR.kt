package com.labters.styler.core

import android.view.View
import android.view.ViewGroup
import com.labters.styler.StyleRProvider
import com.labters.styler.viewbinders.StyleRBinders
import com.labters.styler.viewbinders.StyleRBinders.Companion.getId
import java.util.*

class StyleR {

    companion object {
        private const val KEY_VIEW_ID = "id"
        private const val KEY_NO_ID = "/_"
        private const val KEY_ID_DELIMITER = '/'

        fun applyStyle(rootView: View, styleName: String) {
            visitViewTree(rootView, styleName)
        }

        private fun visitViewTree(rootView: View, styleName: String) {
            checkViewProperties(rootView, styleName)
            (rootView as? ViewGroup)?.let {
                for (i in 0 until it.childCount) {
                    visitViewTree(it.getChildAt(i), styleName)
                }
            }
        }

        private fun getStyle(styleName: String): List<HashMap<String, String>>? =
            StyleRProvider.getViewStyle(styleName)

        private fun getViewProperties(id: String, styleName: String): HashMap<String, String>? {
            var viewMap: HashMap<String, String>? = null
            getStyle(styleName)?.forEach { map ->
                map.keys.find { key -> key == KEY_VIEW_ID && map[key] == id }?.let {
                    viewMap = map
                    return@forEach
                }
            }
            return viewMap
        }

        private fun checkViewProperties(view: View, styleName: String) {
            val viewId = getId(view) ?: KEY_NO_ID
            getViewProperties(
                viewId.substring(viewId.indexOfLast { it == KEY_ID_DELIMITER } + 1, viewId.length),
                styleName
            )?.let {
                setView(view, it)
            }
        }

        private fun setView(view: View, properties: HashMap<String, String>) {
            StyleRBinders.setViewProperty(view, properties)
        }
    }
}