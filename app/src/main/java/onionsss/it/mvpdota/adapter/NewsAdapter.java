package onionsss.it.mvpdota.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.mvpdota.R;
import onionsss.it.mvpdota.bean.NewsList;
import onionsss.it.mvpdota.listener.OnFooterListener;
import onionsss.it.mvpdota.utils.LogUtil;
import onionsss.it.mvpdota.view.MyHeadViewPager;

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
    public NewsAdapter(FragmentActivity activity) {
        mContext = activity;
        list = new ArrayList<>();
        banList = new ArrayList<>();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD){
            return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_vp,parent,false));
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
            List<String> banlist = new ArrayList<>();
            banlist.add(banList.get(0).getBackground());
            banlist.add(banList.get(1).getBackground());
            banlist.add(banList.get(2).getBackground());
            if(holder.mHome_head_vp == null){
                LogUtil.ShowLog("null");
            }
            holder.mHome_head_vp.setPages(() -> new NetworkImageHolderView(),banlist)
                    .setPageIndicator(new int[]{R.drawable.head_vp_shape, R.drawable.head_vp_shape_wh})
                    .setCanLoop(true);
            holder.mHome_head_vp.startTurning(2500);
        }else if(type == TYPE_CONTENT){
            NewsList.NewsEntity newsEntity = list.get(position);

            holder.mDesc.setText(newsEntity.getDescription());
            holder.mTv_time.setText(""+newsEntity.getTime());
            holder.mTv_title.setText(""+newsEntity.getTitle());
            Glide.with(mContext).load(newsEntity.getBackground()).into(holder.mIv_background);
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
        list.clear();
        list.addAll(0,data.getNews());
        banList.addAll(0,data.getBanner());
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
        private MyHeadViewPager mHome_head_vp;
        private TextView mTv_title;
        private TextView mDesc;
        private TextView mTv_time;

        public NewsViewHolder(View itemView) {
            super(itemView);
                mHome_head_vp = (MyHeadViewPager) itemView.findViewById(R.id.home_head_vp);

                mIv_background = (ImageView) itemView.findViewById(R.id.iv_background);
            mTv_title = (TextView) itemView.findViewById(R.id.tv_title);
            mDesc = (TextView) itemView.findViewById(R.id.tv_description);
            mTv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
    public class NetworkImageHolderView implements Holder<String> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(mContext).load(data).placeholder(Color.TRANSPARENT)
                    .error(R.mipmap.ic_book_default)
                    .centerCrop()
                    .into(imageView);
        }
    }

    public OnFooterListener OnFooterListener;
    public void setOnFootListener(OnFooterListener onFooterListener){
        OnFooterListener = onFooterListener;
    }
}
