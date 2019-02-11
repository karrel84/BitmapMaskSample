package karrel.kr.co.bitmapmasksample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * Created by Rell on 2019. 2. 8..
 */
class MaskView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var angle: Float = 0.0f


    override fun onDraw(canvas: Canvas) {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val tmpCanvas = Canvas(bitmap)

        val paint = Paint()

        val progress = BitmapFactory.decodeResource(context.resources, R.drawable.progress)

        val left = (width - progress.width) / 2f
        val top = (height - progress.height) / 2f
        tmpCanvas.drawBitmap(progress, left, top, paint) // 프로그레스 이미지 그림
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN) // 트랜스퍼모드 적용

        tmpCanvas.drawBitmap(bitmapSector(), 0f, 0f, paint)  // 부채골 이미지 그림

        paint.xfermode = null // 트랜스퍼 모드 해제
        canvas.drawBitmap(bitmap, 0f, 0f, paint) // canvas에 draw bitmap
    }

    private fun bitmapSector(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        val paint = Paint()
        paint.isAntiAlias = true

        val oval = RectF(0f, 0f, width.toFloat(), height.toFloat())
        paint.color = Color.RED
        canvas.drawArc(oval, 270f, angle, true, paint)

        return bitmap
    }

    fun updateAngle(angle: Float) {
        this.angle = angle
        invalidate()
    }
}


