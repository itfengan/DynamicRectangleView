# DynamicRectangleView
A dynamic rectangle that can be used as a background in a scrollable view


# 随滑动改变的动态矩形背景 #
> 前言

可能看到这个标题有点懵逼,本屌的表达能力有限,最近工作比较清闲,朋友公司正在做到这个效果,所以就帮基友写了一个小Demo,总体来说就是以下效果.

> 朋友公司需要达到的效果

![这里写图片描述](http://img.blog.csdn.net/20171011155803164?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZmVuZ2FuaXQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


> Demo中的效果

![这里写图片描述](http://img.blog.csdn.net/20171011155905764?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZmVuZ2FuaXQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 使用过程 ##

> layout

        <com.fengan.dynamicrectangledemo.DynamicRectangleView
        android:id="@+id/drv"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        app:fengan_limit_percent="0.2"
        app:fengan_percent="0.5"
        ></com.fengan.dynamicrectangledemo.DynamicRectangleView>

> code

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.sb);
        final DynamicRectangleView dynamicRectangleView = (DynamicRectangleView) findViewById(R.id.drv);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dynamicRectangleView.setPercent((float)i/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        findViewById(R.id.btn_scrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ScrollViewActivity.class));
            }
        });
    }

> attrs.xml(有灵性的哥哥们应该猜得到分别对应的什么意思咯)

    <resources>
    <declare-styleable name="DynamicRectangleView">
        <attr name="fengan_percent" format="float"/>
        <attr name="fengan_limit_percent" format="float"/>
        <attr name="fengan_start_color" format="color"/>
        <attr name="fengan_end_color" format="color"/>
    </declare-styleable>
	</resources>

1. fengan_percent:右侧短边占左侧的百分比
2. fengan_limit_percent:当滑动到最小的百分比
3. fengan_start_color:渐变色的初始颜色(ps:朋友公司效果图是渐变色,当然也可以不需要)
4. fengan_end_color:渐变色的终止颜色

