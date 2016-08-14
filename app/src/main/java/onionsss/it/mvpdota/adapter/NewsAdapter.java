package onionsss.it.mvpdota.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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
import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.listener.OnFooterListener;
import onionsss.it.mvpdota.listener.OnItemClickListener;

/**
 * 作者：张琦 on 2016/8/14 11:42
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    private static final int TYPE_HEAD = 101;
    private static final int TYPE_FOOT = 102;
    private static final int TYPE_CONTENT = 103;
    private int counts = 1;
    private Context mContext;
    private List<NewsList.NewsEntity> list;
    private List<NewsList.NewsEntity> banList;
    private View headView;
    public NewsAdapter(FragmentActivity activity, View headView) {
        mContext = activity;
        list = new ArrayList<>();
        banList = new ArrayList<>();
        this.headView = headView;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD){
            return new NewsViewHolder(headView);
        }else if(viewType == TYPE_CONTENT){
            return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false));
        }else{
            return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.footview,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type == TYPE_HEAD){

        }else if(type == TYPE_CONTENT){
            NewsList.NewsEntity newsEntity = list.get(position);
            holder.mDesc.setText(newsEntity.getDescription());
            holder.mTv_time.setText(""+newsEntity.getTime());
            holder.mTv_title.setText(""+newsEntity.getTitle());
            Glide.with(mContext).load(newsEntity.getBackground()).into(holder.mIv_background);
            holder.itemView.setOnClickListener(v -> {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(newsEntity);
                }
            });

        }else{
            if(OnFooterListener != null){
                OnFooterListener.startListener();
            }
        }
    }

    public void flush(NewsList data, boolean isFlush){
        list.clear();
        list.addAll(data.getNews());
        banList.addAll(data.getBanner());
        notifyDataSetChanged();
    }
    public void refresh(NewsList data) {
        list.addAll(data.getNews());
        banList.addAll(data.getBanner());
        notifyDataSetChanged();
    }

    public void loadMore(NewsList data) {
        list.addAll(data.getNews());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEAD;
        }else if(position+counts == getItemCount()){
            return TYPE_FOOT;
        }else{
            return TYPE_CONTENT;
        }
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        private ImageView mIv_background;
        private TextView mTv_title;
        private TextView mDesc;
        private TextView mTv_time;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mIv_background = (ImageView) itemView.findViewById(R.id.iv_background);
            mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
            mDesc = (TextView) itemView.findViewById(R.id.tv_description);
            mTv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public OnFooterListener OnFooterListener;
    public void setOnFootListener(OnFooterListener onFooterListener){
        OnFooterListener = onFooterListener;
    }

    public OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
