package business;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by jin on 2016/5/17.
 */
public class UncardBindDialog extends Dialog {
    int layoutRes;//布局文件
    Context context;
    public UncardBindDialog(Context context) {
        super(context);
        this.context = context;
    }
    /**
     * 自定义布局的构造方法
     * @param context
     * @param resLayout
     */
    public UncardBindDialog(Context context, int resLayout){
        super(context);
        this.context = context;
        this.layoutRes=resLayout;
    }
    /**
     * 自定义主题及布局的构造方法
     * @param context
     * @param theme
     * @param resLayout
     */
    public UncardBindDialog(Context context, int theme, int resLayout){
        super(context, theme);
        this.context = context;
        this.layoutRes=resLayout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutRes);
    }
}
