package onionsss.it.mvpdota.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.bean.StrategyList;

/**
 * 作者：张琦 on 2016/8/15 16:31
 */
public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.MyStrategyViewHolder>{
    private List<StrategyList.StrategyEntity> list;
    private final int TYPE_CONTENT = 101;
    private final int TYPE_FOOT = 102;
    private Context mContext;
    public StrategyAdapter(Context context){
        list = new ArrayList<>();
        mContext = context;
    }
    @Override
    public StrategyAdapter.MyStrategyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOT){
            return new MyStrategyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footview,parent,false));
        }else if(viewType == TYPE_CONTENT){
            return new MyStrategyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_strategies,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyStrategyViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_FOOT){

        }else if(getItemViewType(position) == TYPE_CONTENT){
            StrategyList.StrategyEntity strategyEntity = list.get(position);

            Glide.with(mContext).load(strategyEntity.getBackground()).into(holder.mIv_background);
            holder.mTv_title.setText(strategyEntity.getTitle());
            holder.mTv_description.setText(strategyEntity.getDescription());
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount()){
            return TYPE_FOOT;
        }else{
            return TYPE_CONTENT;
        }

    }

    public void flush(StrategyList bean) {
        list.clear();
        list.addAll(bean.getStrategies());
        notifyDataSetChanged();
    }

    static class MyStrategyViewHolder extends RecyclerView.ViewHolder{

        private ImageView mIv_background;
        private TextView mTv_title;
        private TextView mTv_description;

        public MyStrategyViewHolder(View itemView) {
            super(itemView);
            mIv_background = (ImageView) itemView.findViewById(R.id.iv_background);
            mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
            mTv_description = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}
