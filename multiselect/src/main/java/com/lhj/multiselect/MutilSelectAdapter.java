package com.lhj.multiselect;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdq on 2018/5/7.
 */

public class MutilSelectAdapter extends RecyclerView.Adapter {
    private MutiSelectAdapterBuilder mutiSelectAdapterBuilder;
    private List<Boolean> list;
    private boolean isSelect;
    private boolean itemSelect;

    public MutilSelectAdapter(MutiSelectAdapterBuilder mutiSelectAdapterBuilder){
        this.mutiSelectAdapterBuilder = mutiSelectAdapterBuilder;
        addSelectList(itemSelect);
    }

    private void addSelectList(boolean isSelect){
        this.list = new ArrayList<>();
        for(int i=0;i<mutiSelectAdapterBuilder.adapter.getItemCount();i++){
            list.add(isSelect);
        }
    }

    public void setSelect(boolean select) {
        isSelect = select;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mutiSelectAdapterBuilder.context).inflate(R.layout.multi_select,parent,false);
        RecyclerView.ViewHolder viewHolder = mutiSelectAdapterBuilder.adapter.onCreateViewHolder(parent,viewType);
        MutiSelectHolder mutiSelectHolder = new MutiSelectHolder(view,viewHolder);
        return mutiSelectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MutiSelectHolder mutiSelectHolder = (MutiSelectHolder) holder;
        addView(mutiSelectHolder);
        mutiSelectAdapterBuilder.adapter.onBindViewHolder(mutiSelectHolder.viewHolder,position);
        if(mutiSelectAdapterBuilder.style == MutiSelectAdapterBuilder.Style.Center){
            mutiSelectHolder.multi_linear.setBackgroundColor(
                list.get(position)?mutiSelectAdapterBuilder.backGround : -1);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mutiSelectHolder.multi_linear.setBackground(mutiSelectHolder.viewHolder.itemView.getBackground());
            }
            mutiSelectHolder.multi_iv.setSelectRes(mutiSelectAdapterBuilder.selectResId);
            mutiSelectHolder.multi_iv.setNoSelectRes(mutiSelectAdapterBuilder.noSelectResId);
            mutiSelectHolder.multi_iv.setChecked(list.get(position));
        }
        mutiSelectHolder.multi_linear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!isSelect){
                    setSelect(true);
                }else{
                    setSelect(false);
                }
                return true;
            }
        });
        mutiSelectHolder.multi_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelect){
                    if(mutiSelectAdapterBuilder.style == MutiSelectAdapterBuilder.Style.Center){
                        int color = ((ColorDrawable)mutiSelectHolder.multi_linear.getBackground()).getColor();
                        int selectColor = mutiSelectAdapterBuilder.backGround;
                        if(color!=selectColor){
                            mutiSelectHolder.multi_linear.setBackgroundColor(selectColor);
                            list.set(position,true);
                        }else{
                            mutiSelectHolder.multi_linear.setBackgroundColor(-1);
                            list.set(position,false);
                        }
                    }else{
                        mutiSelectHolder.multi_iv.toggle();
                        list.set(position,mutiSelectHolder.multi_iv.isChecked());
                    }
                }else{
                    mutiSelectAdapterBuilder.onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mutiSelectAdapterBuilder.adapter.getItemCount();
    }

    private void addView(MutiSelectHolder mutiSelectHolder){
        if(isSelect){
            if(mutiSelectAdapterBuilder.style== MutiSelectAdapterBuilder.Style.Left){
                mutiSelectHolder.multi_linear.setOrientation(LinearLayout.HORIZONTAL);
                View view1 = mutiSelectHolder.multi_linear.getChildAt(0);
                setLayoutForSelect(view1,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                View view2 = mutiSelectHolder.multi_linear.getChildAt(1);
                setLayoutForItem(view2,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                selectViewAnim(mutiSelectHolder.mult1_before_rl);
            }else if(mutiSelectAdapterBuilder.style== MutiSelectAdapterBuilder.Style.Right){
                mutiSelectHolder.multi_linear.setOrientation(LinearLayout.HORIZONTAL);
                View view1 = mutiSelectHolder.multi_linear.getChildAt(0);
                View view2 = mutiSelectHolder.multi_linear.getChildAt(1);
                if(view1.getId()==(mutiSelectHolder.mult1_before_rl.getId())){
                    mutiSelectHolder.multi_linear.removeAllViews();
                    mutiSelectHolder.multi_linear.addView(view2);
                    mutiSelectHolder.multi_linear.addView(view1);
                    setLayoutForItem(view2,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    setLayoutForSelect(view1,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                }
                selectViewAnim(mutiSelectHolder.mult1_before_rl);
            }else if(mutiSelectAdapterBuilder.style== MutiSelectAdapterBuilder.Style.Top){
                mutiSelectHolder.multi_linear.setOrientation(LinearLayout.VERTICAL);
                View view1 = mutiSelectHolder.multi_linear.getChildAt(0);
                setLayoutForSelect(view1,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                View view2 = mutiSelectHolder.multi_linear.getChildAt(1);
                setLayoutForItem(view2,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                selectViewAnim(mutiSelectHolder.mult1_before_rl);
            }else if(mutiSelectAdapterBuilder.style== MutiSelectAdapterBuilder.Style.Bottom){
                mutiSelectHolder.multi_linear.setOrientation(LinearLayout.VERTICAL);
                View view1 = mutiSelectHolder.multi_linear.getChildAt(0);
                View view2 = mutiSelectHolder.multi_linear.getChildAt(1);
                if(view1.getId()==(mutiSelectHolder.mult1_before_rl.getId())){
                    mutiSelectHolder.multi_linear.removeAllViews();
                    mutiSelectHolder.multi_linear.addView(view2);
                    mutiSelectHolder.multi_linear.addView(view1);
                    setLayoutForItem(view2,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    setLayoutForSelect(view1,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                selectViewAnim(mutiSelectHolder.mult1_before_rl);
            }else if(mutiSelectAdapterBuilder.style== MutiSelectAdapterBuilder.Style.Center){
                mutiSelectHolder.mult1_before_rl.setVisibility(View.GONE);
            }
        }else{
            mutiSelectHolder.mult1_before_rl.setVisibility(View.GONE);
        }
    }

    private void setLayoutForSelect(View view,int width,int height){
        view.setLayoutParams(new LinearLayout.LayoutParams(width,
                height, 0f));
    }

    private void setLayoutForItem(View view,int width,int height){
        view.setLayoutParams(new LinearLayout.LayoutParams(width,
                height, 1f));
    }

    private void selectViewAnim(final View view){
        view.setVisibility(View.VISIBLE);
    }

    /**
     *  全选
     */
    public void setFutureGenerations(){
        if(isSelect){
            itemSelect = true;
            addSelectList(itemSelect);
            notifyDataSetChanged();
        }
    }

    /**
     *  反选
     */
    public void clearAllGenerations(){
        if(isSelect){
            itemSelect = false;
            addSelectList(itemSelect);
            notifyDataSetChanged();
        }
    }

    /**
     *  更新传入的Adapter.
     */
    public void notiAdapter(RecyclerView.Adapter adapter){
        mutiSelectAdapterBuilder.adapter = adapter;
        notifyDataSetChanged();
    }

    /**
     *  获取多选item的position集合
     * @return
     */
    public List<Integer> getSelectListForPosition(){
        List<Integer> listS = new ArrayList<>();
        if (list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                if(list.get(i)){listS.add(i);}
            }
        }
        return listS;
    }

    class MutiSelectHolder extends RecyclerView.ViewHolder{
        LinearLayout multi_linear;
        SelectImageView multi_iv;
        RelativeLayout mult1_before_rl;
        RecyclerView.ViewHolder viewHolder;

        public MutiSelectHolder(View itemView,RecyclerView.ViewHolder viewHolder){
            super(itemView);
            this.viewHolder = viewHolder;
            initView(itemView);
        }

        public void initView(View itemView){
            multi_linear = itemView.findViewById(R.id.multi_linear);
            multi_iv = itemView.findViewById(R.id.multi_iv);
            mult1_before_rl = itemView.findViewById(R.id.mult1_before_rl);
            multi_linear.addView(viewHolder.itemView);
        }
    }

    public static class MutiSelectAdapterBuilder{
        RecyclerView.Adapter adapter;
        Context context;
        Style style = Style.Left;
        int selectResId;
        int noSelectResId;
        int backGround = Color.parseColor("#D0D0D0");
        OnItemClickMultiListener onItemClickListener;

        public MutiSelectAdapterBuilder setContext(Context context){
            this.context = context;
            return this;
        }

        public MutiSelectAdapterBuilder setAdapter(RecyclerView.Adapter adapter){
            this.adapter = adapter;
            return this;
        }

        public MutiSelectAdapterBuilder setStyle(Style style){
            this.style = style;
            return this;
        }

        public MutiSelectAdapterBuilder setMutiSelectDrawable(int selectResId,int noSelectResId){
            this.selectResId = selectResId;
            this.noSelectResId = noSelectResId;
            return this;
        }

        public MutiSelectAdapterBuilder setMutiSelectDrawable(int color){
            this.backGround = color;
            return this;
        }

        public MutiSelectAdapterBuilder addOnItemClickListerns(OnItemClickMultiListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public MutilSelectAdapter build(){
            return new MutilSelectAdapter(this);
        }

        public enum Style{
            Left,
            Right,
            Top,
            Bottom,
            Center
        }

        public interface OnItemClickMultiListener{
            void onItemClick(View view,int position);
        }

    }

}
