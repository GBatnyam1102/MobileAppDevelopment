import android.Manifest
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.flashcardapp.R

class ReminderWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        // Trigger a notification
        sendReminderNotification()
        return Result.success()
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun sendReminderNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "flashcard_channel")
            .setContentTitle("Үгээ Цээжлэх цаг боллоо")
            .setContentText("Хамтдаа үгээ цээжилцгээе!")
//            .setSmallIcon(R.drawable.ic_notification) // Replace with your app's notification icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(0, notification)
    }
}
