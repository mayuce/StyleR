package com.labters.styler.viewbinders

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.labters.styler.StyleRProvider
import com.labters.styler.viewbinders.button.ButtonBinder.Companion.setStyleR
import com.labters.styler.viewbinders.textview.TextViewBinder.Companion.setStyleR
import com.labters.styler.viewbinders.view.ViewBinder.Companion.setStyleR

internal class StyleRBinders {
    companion object {

        fun getId(view: View): String? {
            return if (view.id == View.NO_ID) null else view.resources.getResourceName(view.id)
        }

        fun setViewProperty(view: View, map: HashMap<String, String>) {
            StyleRProvider.viewSetter?.invoke(view, map)
            (view as? View)?.setStyleR(map)
            (view as? TextView)?.setStyleR(map)
            (view as? Button)?.setStyleR(map)
        }
    }
}