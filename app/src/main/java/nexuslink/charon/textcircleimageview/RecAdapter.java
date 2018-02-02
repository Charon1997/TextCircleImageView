package nexuslink.charon.textcircleimageview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：TextCircleImageView
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/2/2 15:17
 * 修改人：Charon
 * 修改时间：2018/2/2 15:17
 * 修改备注：
 */

public class RecAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<String> list;
    private List<String>colorList = Arrays.asList("#FF4336","#E01E63","#9227B0","#F73AB7","#315FB5","#F09688","#0F5722","#725548","#F07D8B");
    RecAdapter(Context mContext,List<String> list) {
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textView.setText(list.get(position));
        ((ViewHolder)holder).imageView.setText(String.valueOf(list.get(position)));
        ((ViewHolder)holder).imageView.setDefaultBackgroundColor(position);

    }

     class  ViewHolder extends RecyclerView.ViewHolder {
        private TextCircleImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (TextCircleImageView) itemView.findViewById(R.id.item_img);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            //imageView.setColorList(colorList);
            imageView.setFirst(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
