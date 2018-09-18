# MutiSelectAdapter

最近刚刚发现原来自己以前很多项目都是有用不同类型的多选列表，然后还每一次都是自己写xml与model来实现（就是重复造轮子），<br>
所以汇总了这些不同样式的多选功能，封装了MutilSelectAdapter，实现5种多选的方式。如下图：<br>

5种多选方式<br>
 
## 使用
```
mutilSelectAdapter =new MutilSelectAdapter.MutiSelectAdapterBuilder()
.setContext(this)//设置上下文对象
.setStyle(MutilSelectAdapter.MutiSelectAdapterBuilder.Style.Center)//设置多选的样式
.setAdapter(new StringAdapter(this,getList()))//设置你自己的Adapter
// .setMutiSelectDrawable(int color)//设置选中的背景颜色
// .setMutiSelectDrawable(int imageResIDSelect,int imageResIDNoSelect)//设置选中与非选中的图片
.addOnItemClickListerns(new MutilSelectAdapter.MutiSelectAdapterBuilder.OnItemClickMultiListener() {//在非多选状态下的item点击监听
@Override
            public void onItemClick(View view,int position) {
Toast.makeText(MainActivity.this, getList().get(position), Toast.LENGTH_SHORT).show();
}
})
.build();
就是那么简单，一行代码就能实现多选功能。
