package nexuslink.charon.textcircleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：TextCircleImageView
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/2/1 17:12
 * 修改人：Charon
 * 修改时间：2018/2/1 17:12
 * 修改备注：
 */

public class TextCircleImageView extends android.support.v7.widget.AppCompatImageView {
    private String text;
    private List<String> colorList;
    private String backgroundColor = "#000000";
    private String textColor = "#FFFFFF";
    private Paint backgroundPaint, textPaint;
    private boolean isFirst;


    //让View兼容xml与Java
    public TextCircleImageView(Context context) {
        this(context, null);
    }

    public TextCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCircleImageView);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.TextCircleImageView_text:
                    text = ta.getString(attr);
                    break;
                case R.styleable.TextCircleImageView_backgroundColor:
                    backgroundColor = "#" + String.valueOf(Integer.toHexString(ta.getColor(attr, 0X000000)));
                    break;
                case R.styleable.TextCircleImageView_textColor:
                    textColor = "#" + String.valueOf(Integer.toHexString(ta.getColor(attr, 0Xffffff)));
                    break;
                case R.styleable.TextCircleImageView_first:
                    isFirst = ta.getBoolean(attr, false);
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
        init();
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //默认颜色组为Material Design饱和度为500的非亮色背景
        colorList = Arrays.asList("#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#009688", "#FF5722", "#795548", "#607D8B");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(widthMeasureSpec);
        int height = getDefaultSize(heightMeasureSpec);
        int radius = Math.min(width, height);
        setMeasuredDimension(radius, radius);
    }

    private int getDefaultSize(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        //准确的值
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            //最少20
            result = 20 + getPaddingTop() + getPaddingBottom();
            //最少的
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //最长3个字
        int stringNum = Math.min(getText().length(), 3);
        backgroundPaint.setColor(Color.parseColor(getBackgroundViewColor()));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth() / 2, getHeight() / 2), backgroundPaint);
        textPaint.setColor(Color.parseColor(getTextColor()));
        //根据字数多少设定字大小
        textPaint.setTextSize(Math.min(getWidth() / 2 / stringNum, getHeight() / 2 / stringNum));
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(getText(), getWidth() / 2, baseline, textPaint);
    }

    public String getText() {
        if (null == text) {
            return "N";
        }
        return text;
    }

    public void setText(String text) {
        if (isFirst) {
            this.text = String.valueOf(text.charAt(0));
        } else {
            this.text = text.substring(0, Math.min(text.length(), 3));
        }
    }

    public void setText(String text, boolean isFirst) {
        this.isFirst = isFirst;
        this.setText(text);
    }

    public boolean isFirst() {
        return isFirst;
    }

    /**
     * 设置是否只显示第一个字
     *
     * @param first
     */
    public void setFirst(boolean first) {
        isFirst = first;
    }

    /**
     * 获取背景圆颜色
     *
     * @return 背景圆颜色
     */
    public String getBackgroundViewColor() {
        return backgroundColor;
    }

    /**
     * 设置背景圆颜色
     *
     * @param backgroundColor 背景圆颜色
     */
    public void setBackgroundViewColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 获取文字颜色
     *
     * @return 文字颜色
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * 设置文字颜色
     *
     * @param textColor 文字颜色
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     * 设置默认背景颜色
     *
     * @param position 设置颜色的序号
     */
    public void setDefaultBackgroundColor(int position) {
        backgroundColor = getColorList().get(position % colorList.size());
    }

    /**
     * 获取颜色列表信息
     *
     * @return 颜色列表信息
     */
    public List<String> getColorList() {
        return colorList;
    }

    /**
     * 设置颜色列表信息
     *
     * @param colorList 颜色列表
     */
    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }


}
