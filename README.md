# BitmapMaskSample

위의 이미지로 마스킹 처리해서 프로그레스를 만들일이 있었습니다.



일단 리소스에서 해당 이미지를 가져와서 bitmap으로 만들어줍니다.

```val progress = BitmapFactory.decodeResource(context.resources, R.drawable.progress```


그리고 마스킹 처리할 부채꼴 모양의 도형을 비트맵으로 그려줍니다.

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


첫번째 원형 이미지를 tmpCanvas에 그리고나서 Paint에 DST_IN 트랜스퍼 모드를 적용한후에 뒤에 부채꼴 모양의 도형을 그려줍니다.

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


출처: https://karrel.tistory.com/82 [카렐님의 프로그래밍]
