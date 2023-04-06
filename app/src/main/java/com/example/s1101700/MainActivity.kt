package com.example.s1101700

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() ,OnGestureListener{

    lateinit var txv: TextView
    lateinit var gDetector: GestureDetector
    var count:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#eeee00"))
        txv.setBackgroundColor(Color.BLUE)
        Typeface.createFromAsset(assets,
            "font/HanyiSentyFingerPainting.ttf")
        txv.getBackground().setAlpha(50)  //0~255透明度值
        gDetector = GestureDetector(this,this)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (txv.text == "手勢辨別"){
            txv.text = "自我介紹"
        }
        else{
            txv.text = "手勢辨別"
        }
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(e: MotionEvent): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        txv.text = "按下後無後續動作"


    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float,distanceY: Float

    ): Boolean {
        if (distanceY >= 0){
            txv.text = "向上拖曳"
        }
        else{
            txv.text = "向下拖曳"
        }


        return true
    }

    override fun onLongPress(e: MotionEvent) {
        txv.text = "長按"

    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float,velocityY: Float): Boolean {
        if (e1.x <= e2.x){
            txv.text = "往右快滑"
            count++
            if(count>5){count=0}
        }
        else{
            txv.text = "往左快滑"
            count--
            if(count<0){count=5}
        }
        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<LinearLayout>(R.id.bg).setBackgroundResource(res)

        return true
    }

}