![效果图](/photo/device-2017-07-26-102250.png)
![效果图](/photo/device-2017-07-26-102312.png)

```
  setData需要传入的六个值
  yHighValue :文字的内容
  xDayValues :有数据的天数的集合
  listSize :有数据天数的大小(状态相同的一天，或者两天或者几天)
  listStatus :有数据天数对应的状态（正常，异常，或者部分异常）
  listTag :每一天对应的状态，是在开头、中间、结尾、或者既是开头也是结尾
  daySize :传入总天数(两周还是一个月对应的14或者30)

  /**
  * 画矩形
  canvas.drawRect(rectF,mChartPaint);
  */

  /**
  * 画左右半圆
  canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
  */
  /**
  * 画左边半圆
  *canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
  *canvas.drawRect(new RectF(rectF.left + mChartHeight / 2, rectF.top, rectF.right, rectF.bottom), mChartPaint);
  * */
  /**
  * 画右边半圆
  * canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
  * canvas.drawRect(new RectF(rectF.left, rectF.top, rectF.right- mChartHeight / 2, rectF.bottom), mChartPaint);
  */

```