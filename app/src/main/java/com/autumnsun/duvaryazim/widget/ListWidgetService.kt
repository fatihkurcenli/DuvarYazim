package com.autumnsun.duvaryazim.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.autumnsun.duvaryazim.R
import com.autumnsun.duvaryazim.data.local.entity.WallStreet
import com.autumnsun.duvaryazim.data.remote.DataFileGetLocal
import com.autumnsun.duvaryazim.utils.getDate
import com.bumptech.glide.Glide

/*
 Created by Fatih Kurcenli on 12/10/2021
*/

class ListWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent): RemoteViewsFactory {
        return ListWidgetServiceFactory(this.applicationContext)
    }
}

class ListWidgetServiceFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {
    private lateinit var widgetItems: MutableList<WallStreet>

    override fun onCreate() {
        widgetItems = DataFileGetLocal.localData.toMutableList()
    }

    override fun onDataSetChanged() {}

    override fun onDestroy() {
        widgetItems.clear()
    }

    override fun getCount(): Int = widgetItems.size

    override fun getViewAt(p0: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.widget_list_item).apply {
            widgetItems[0].imageUrl?.let {
                val imageBitmap = Glide.with(context.applicationContext)
                    .asBitmap()
                    .load(widgetItems[p0].imageUrl)
                    .submit(85, 65)
                    .get()
                setImageViewBitmap(R.id.widget_image, imageBitmap)
            }
            setTextViewText(R.id.widget_title, widgetItems[p0].wallStreet)
            setTextViewText(R.id.widget_writer, widgetItems[p0].writer)
            setTextViewText(R.id.widget_create_time, getDate(widgetItems[p0].timestamp))
            val args = Bundle()
            args.putString("unicId", widgetItems[p0].id)
            val fillInIntent = Intent()
            fillInIntent.putExtras(args)
            setOnClickFillInIntent(R.id.widget_list_item_linear_layout, fillInIntent)
        }
    }

    override fun getLoadingView(): RemoteViews {
        return RemoteViews(context.packageName, R.layout.wall_street_widget)
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}
