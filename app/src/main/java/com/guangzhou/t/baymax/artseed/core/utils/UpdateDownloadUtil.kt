package com.guangzhou.t.baymax.artseed.core.utils


import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.bean.VersionBean
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.model.FileDownloadStatus
import com.liulishuo.filedownloader.util.FileDownloadUtils
import java.io.File


/**
 * apk更新
 * author：T-Baymax on 2018/8/4.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class UpdateDownloadUtil {
    private var context: Context
        get() = this.context
        set(value) = TODO()

    private lateinit var mNotification: NotificationCompat.Builder
    private lateinit var mNotificationManager: NotificationManager
    private lateinit var mRemoteViews: RemoteViews
    private lateinit var  savePath:String

    constructor(context: Context)

    /**
     * 现在APk
     */
    fun updateApk(version: VersionBean) {
        savePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "sdistrict/${version.title}"
        createNotification()
        /*val fileUtils = FileUtils(context)

        val f = fileUtils.redFile("")*/
        FileDownloader.getImpl().create(version.url)
                .setPath(savePath)
                .setListener(object : FileDownloadListener() {
                    override fun warn(task: BaseDownloadTask?) {

                    }

                    override fun completed(task: BaseDownloadTask?) {
                        updateFinishDownloaded()
                    }

                    override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                        updateDownloading(FileDownloadStatus.pending.toInt(), soFarBytes.toLong(), totalBytes.toLong())
                    }

                    override fun error(task: BaseDownloadTask?, e: Throwable?) {

                        mRemoteViews.setTextViewText(R.id.tv_progress, "下载出错")
                        //  mRemoteViews.setProgressBar(R.id.pb_progress, 100, progress, false)
                        mNotification.setContent(mRemoteViews)
                        mNotificationManager.notify(15, mNotification.build())
                    }

                    override fun connected(task: BaseDownloadTask?, etag: String?, isContinue: Boolean, soFarBytes: Int, totalBytes: Int) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes)
                        updateDownloading(FileDownloadStatus.progress.toInt(), soFarBytes.toLong(), totalBytes.toLong())
                    }

                    override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                        updateDownloading(FileDownloadStatus.progress.toInt(), soFarBytes.toLong(), totalBytes.toLong())
                    }

                    override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                    }
                }).start()
    }

    /**
     * 下载中
     */
    private fun updateDownloading(status: Int, sofar: Long, total: Long) {
        val percent = sofar / total.toFloat()
        val progress = (percent * 100).toInt()
        mRemoteViews.setTextViewText(R.id.tv_progress, "$progress%")
        mRemoteViews.setProgressBar(R.id.pb_progress, 100, progress, false)
        mNotification.setContent(mRemoteViews)
        mNotificationManager.notify(15, mNotification.build())
    }

    /**
     * 下载完成
     */
    private fun updateFinishDownloaded() {
        val install = Intent(Intent.ACTION_VIEW)
        if (Build.VERSION.SDK_INT >= 24) {//判断版本是否在7.0以上

            val apkUri = FileProvider.getUriForFile(context, "com.gz.bjike.busines.sdistrict", File(savePath))//在AndroidManifest中的android:authorities值

            install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive")
            context.startActivity(install)
        } else {
            //  Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.parse(savePath), "application/vnd.android.package-archive")
            install.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(install)
        }
    }

    /**
     * 消息栏显示进度
     */
    private fun createNotification() {
        mNotification = NotificationCompat.Builder(context, "")
        mNotification.setSmallIcon(R.drawable.logo).setContentText(context.getString(R.string.app_name) + "正在下载")
        mRemoteViews = RemoteViews(context.packageName, R.layout.view_download_notification)
        mRemoteViews.setTextViewText(R.id.tv_name, context.getString(R.string.app_name) + "正在下载")
        mRemoteViews.setTextViewText(R.id.tv_progress, "0%")
        mRemoteViews.setProgressBar(R.id.pb_progress, 100, 0, false)
        mNotification.setContent(this.mRemoteViews)
        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(15, this.mNotification.build())
    }
}