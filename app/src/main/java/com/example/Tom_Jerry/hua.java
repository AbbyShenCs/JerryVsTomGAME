package com.example.Tom_Jerry;

import static com.example.Tom_Jerry.MusicServer.pause;
import static com.example.Tom_Jerry.MusicServer.restart;
import static com.example.Tom_Jerry.MusicServer.stop;
import static java.lang.Math.max;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;



class my{//新建一个类 里面的东西都是静态的 当全局变量用
    public static int js=0;//击杀数
    public static int w,h;//屏幕的宽高
    public static float bili;//比例，用于适应不同屏幕
    public static Vector<hj> list=new Vector<hj>();//所有飞行物的集合,添加进这个集合才能被画出来
    public static Vector<hj> drlist=new Vector<hj>();//敌人飞机的集合，添加进这个集合才能被子弹打中
    public static Vector<hj> rewardlist=new Vector<hj>();
    public static Bitmap myhj,drhj,bj,myzd,boss,reward_pic;//图片：我的灰机 敌人灰机 背景 我的子弹
    public static myhj my;//我的灰机
    public static bj b;//背景
    public static boolean ispause=true,music_on=true,game_over=false,clear=false;
    public static int bgmchoose=4,boss_number=1;
    public static int dps,level=1,drnumber=0,choose=1,nandu=1;

}

public class hua extends View{//画
    private Paint p=new Paint();//画笔
    private float x,y;//按下屏幕时的坐标
    private float myx,myy;//按下屏幕时玩家飞机的坐标
    private boolean status=true;




    public hua(Context context) {
        super(context);
        //添加事件控制玩家飞机
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                if(e.getAction()==MotionEvent.ACTION_DOWN){
                    x=e.getX();
                    y=e.getY();
                    myx=my.my.r.left;
                    myy=my.my.r.top;
                    if (
                            (x>=my.w-150 && x<=my.w-150+100)&&
                                    (y>=60 && y<=60+100)
                    ){
                        //System.out.println("点击了暂停");
                        my.ispause=false;
                        //创建暂停游戏对话框
                        GamePauseDialog gamePauseDialog = new GamePauseDialog(getContext(),hua.this);
                        //设置该对话框为一个模态框
                        gamePauseDialog.setCancelable(false);
                        //显示对话框
                        gamePauseDialog.show();
                        //注意：对话框只有在显示出来之后，才会被初始化对话框的属性
                        pause();

                    }

                    if (
                            (x>=80 && x<=80+100)&&
                                    (y>=60 && y<=60+100)
                    ){
                        if(my.music_on) pause();
                        else restart();
                        my.music_on=!my.music_on;

                    }



                }

                if(my.game_over) {
                    GameOverDialog gameOverDialog = new GameOverDialog(getContext());
                    //设置该对话框为一个模态框
                    gameOverDialog.setCancelable(false);
                    //显示对话框
                    gameOverDialog.show();
                    TextView scoreView = gameOverDialog.getGameoverScore();
                    scoreView.setText("分数："+my.js);
                    my.ispause=false;
                    my.game_over=false;
                    stop(getContext());
                    //注意：对话框只有在显示出来之后，才会被初始化对话框的属性
                }
                if(status) {
                    if (my.clear) {
                        status= false;
                        GameClearDialog gameClearDialog = new GameClearDialog(getContext());
                        //设置该对话框为一个模态框
                        gameClearDialog.setCancelable(false);
                        //显示对话框
                        gameClearDialog.show();
                        TextView scoreView = gameClearDialog.getGameclaerScore();
                        scoreView.setText("分数：" + my.js);
                        my.ispause = false;
                        stop(getContext());
                        //注意：对话框只有在显示出来之后，才会被初始化对话框的属性
                    }
                }



                float xx=myx+e.getX()-x;
                float yy=myy+e.getY()-y;
                //我的飞机不能飞出屏幕
                xx=xx<my.w-my.my.w/2?xx:my.w-my.my.w/2;
                xx=xx>-my.my.w/2?xx:-my.my.w/2;
                yy=yy<my.h-my.my.h/2?yy:my.h-my.my.h/2;
                yy=yy>-my.my.h/2?yy:-my.my.h/2;
                my.my.setX(xx);
                my.my.setY(yy);
                return true;
            }
        });






        setBackgroundColor(Color.BLACK);//设背景颜色为黑色

        switch (my.choose) {
             case 1:my.myhj = BitmapFactory.decodeResource(getResources(), R.mipmap.hj);
                  my.myzd=BitmapFactory.decodeResource(getResources(),R.mipmap.zd2);
                  break;
             case 2:my.myhj = BitmapFactory.decodeResource(getResources(), R.mipmap.hj2);
                  my.myzd=BitmapFactory.decodeResource(getResources(),R.mipmap.zd5);
                  break;
             case 3:my.myhj = BitmapFactory.decodeResource(getResources(), R.mipmap.hj3);
                  my.myzd=BitmapFactory.decodeResource(getResources(),R.mipmap.zd4);
                  break;
        }
        my.drhj=BitmapFactory.decodeResource(getResources(),R.mipmap.dr);
        my.bj=BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        my.boss=BitmapFactory.decodeResource(getResources(), R.mipmap.boss);
        my.reward_pic=BitmapFactory.decodeResource(getResources(), R.mipmap.reward_pic);

        new Thread(new re()).start();//新建一个线程 让画布自动重绘
        new Thread(new loaddr()).start();//新建一个 加载敌人的线程
    }




    @Override
    protected void onDraw(Canvas g) {//这个相当于swing的paint方法吧 用于绘制屏幕上的所有物体
        super.onDraw(g);
        g.drawBitmap(my.b.img,null,my.b.r,p);//画背景 我没有把背景添加到list里

        for(int i=0;i<my.list.size();i++){//我们把所有的飞行物都添加到了my.list这个集合里
            hj h=my.list.get(i);           //然后在这里用一个for循环画出来
            g.drawBitmap(h.img,null,h.r,p);
        }
        g.drawText("击杀："+my.js,0,my.h-60,p);
        g.drawText("敌人数："+my.drnumber,0,my.h-20,p);

        //画暂停
        if(my.music_on) {
            g.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.music_on), 80, 60, p);
        }
        else
            g.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.music_off), 80, 60, p);
        g.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.game_pause_nor),my.w-150,60,p);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//这个方法用来获取屏幕宽高的
        super.onSizeChanged(w, h, oldw, oldh);
        my.w=w;//获取宽
        my.h=h;//高

        //获取手机（应该不是手机的吧 是这控件的吧）分辨率和1920*1080的比例
        //然后飞机的宽高乘上这个分辨率就能在不同大小的屏幕正常显示了
        //为什么用1920*1080呢 因为我手机就是这个分辨率。。。
        my.bili= (float) (Math.sqrt(my.w * my.h)/ Math.sqrt(1920 * 1080));
        p.setTextSize(50*my.bili);//设置字体大小，“击杀”的大小
        p.setColor(Color.WHITE);//设为白色
        //好了 到这里游戏开始了
        my.b=new bj();//初始化背景
        my.my=new myhj();//初始化 我的灰机
    }
    private class re implements Runnable {
        @Override
        public void run() {
            //每10ms刷新一次界面
            while(true){
                try { Thread.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                if(my.ispause)postInvalidate();//刷新画布
                //就是这个东西拖了我一天
                //swing是repaint()方法刷新的
                //然后这里没有repaint方法
                //然后突然想起C#有一个invalidate()方法是刷新画布的
                //然后这线程里用invalidate()会闪退.....
                switch (my.nandu){
                    case 1:
                        if(my.js>=10){
                            my.clear=true;
               /*             Intent intent=new Intent(getContext(), ClearActivity.class);
                            getContext().startActivity(intent);
                            System.exit(0);*/
                        }
                    case 2:
                        if(my.js>=1500){
                            my.clear=true;
                          /*  Intent intent=new Intent(getContext(), ClearActivity.class);
                            getContext().startActivity(intent);
                            System.exit(0);*/
                        }
                    case 3:
                        if(my.js>=2000){
                            my.clear=true;
                        /*    Intent intent=new Intent(getContext(), ClearActivity.class);
                            getContext().startActivity(intent);
                            System.exit(0)*/;
                        }
                    case 4:
                        if(my.js>=2500){
                            my.clear=true;
                          /*  Intent intent=new Intent(getContext(), ClearActivity.class);
                            getContext().startActivity(intent);
                            System.exit(0);*/
                        }

                }

            }
        }
    }
    private class loaddr implements Runnable{
        @Override
        public void run() {
            while(true){
                //每300ms刷一个敌人
                try {Thread.sleep(max((400-my.js/4),60));} catch (InterruptedException e) {e.printStackTrace();}
               if(my.ispause) {
                   try {
                       new drhj();
                       my.drnumber++;
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
                if(my.ispause&&(my.js%50==0||my.js%50==1)&&(my.boss_number<=my.js/50)) {
                    try {
                        new boss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(my.drnumber>60)
                    my.game_over=true;
            }
        }
    }
}
class hj{//游戏内所有物体的父类
    public RectF r=new RectF();//这个是用来确定位置的
    public int hp;//生命
    public float w,h;//宽高
    public Bitmap img;//图片


    //这里的画图方法和swing的不太一样
    //设两个方法来设置x,y的坐标
    public void setX(float x){
        r.left=x;
        r.right=x+w;
    }
    public void setY(float y){
        r.top=y;
        r.bottom=y+h;
    }

    public boolean pengzhuang(hj obj,float px) {//判断碰撞 判断时忽略px个像素
        px*=my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        if (r.left+px - obj.r.left <= obj.w && obj.r.left - this.r.left+px <= this.w-px-px)
            if (r.top+px - obj.r.top <= obj.h && obj.r.top - r.top+px <= h-px-px) {
                return true;
            }

        return false;

    }
}
class bj extends hj {//背景implements  Runnable
    public bj(){
        w=my.w;
        h=my.h;//背景的高是 屏幕高的两倍
        img=my.bj;
        setX(0);
        setY(0);
       /* new Thread(this).start();*/
    }
/*    @Override
    public void run() {

        //这里控制背景一直向下移
        while(true){
            try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            if(my.ispause) {
                if (r.top + 2 <= 0) {
                    setY(r.top + 2);
                } else {
                    setY(0);
                }
            }
        }
    }*/
}

class drhj extends hj implements Runnable{//敌人灰机
    private long sd0=max(3,((long)20-my.js/50));//生成一个[10,20)的随机数 用来控制敌人速度 敌人速度是不一样的

    public drhj(){
//        w=my.w/5.4f;
//        h=my.h/9.6f;
            w = 80 * my.bili;
            h = 100 * my.bili;
            //敌人刷出来的位置
            setX((float) (Math.random() * (my.w - w)));//x是随机的
            setY(-h);//在屏幕外 刚好看不到的位置
            img = my.drhj;
            hp = 12;//生命=11
            my.list.add(this);//添加到集合里 这样才能被画出来
            my.drlist.add(this);//添加到敌人的集合 添加进这个集合子弹才打得到
            new Thread(this).start();
    }



    @Override
    public void run() {
        while(hp>0){//如果生命>0 没有死 就继续向前飞，死了还飞什么？
            try {Thread.sleep(sd0);} catch (InterruptedException e) {e.printStackTrace();}
            if(my.ispause)setY(r.top+(2+my.js/500)*my.bili);
            if(r.top>=my.h)break;//敌人飞出屏幕 跳出循环

        }
        //从集合删除
        my.list.remove(this);
        my.drnumber--;
        my.drlist.remove(this);
    }
}

class boss extends hj implements Runnable{//敌人灰机
    public boss(){
//        w=my.w/5.4f;
//        h=my.h/9.6f;
        w = 200*my.bili;
        h = 400*my.bili;
        //敌人刷出来的位置
        setX((float) (Math.random() * (my.w - w)));//x是随机的
        setY(-h);//在屏幕外 刚好看不到的位置
        img = my.boss;
        hp = 2*my.js+50;//
        my.list.add(this);//添加到集合里 这样才能被画出来
        my.drlist.add(this);//添加到敌人的集合 添加进这个集合子弹才打得到
        my.boss_number++;
        new Thread(this).start();
    }



    @Override
    public void run() {
        while(hp>0){//如果生命>0 没有死 就继续向前飞，死了还飞什么？
            try {Thread.sleep(30);} catch (InterruptedException e) {e.printStackTrace();}
            if(my.ispause){
                setY(r.top+3*my.bili);
            }
            if(r.top>=my.h)break;//敌人飞出屏幕 跳出循环
            if(hp<=0)new reward();
        }
        //从集合删除
        my.list.remove(this);
        my.drlist.remove(this);

    }
}

class reward extends hj implements Runnable{//奖励
    public reward(){
        w = 230*my.bili;
        h = 152*my.bili;
        //敌人刷出来的位置
        setX((float) (Math.random() * (my.w - w)));//x是随机的
        setY(h);//在屏幕外 刚好看不到的位置
        img = my.reward_pic;
        hp = my.js;//
        my.list.add(this);//添加到集合里 这样才能被画出来
        my.rewardlist.add(this);//添加到敌人的集合 添加进这个集合子弹才打得到
        new Thread(this).start();
    }



    @Override
    public void run() {
        while(true) {//如果生命>0 没有死 就继续向前飞，死了还飞什么？
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (my.ispause) {
                setY(r.top + 2 * my.bili);
            }
            if (r.top >= my.h) break;//敌人飞出屏幕 跳出循环
            if(pengzhuang(my.my,30)){//判断碰撞
                my.level++;
                break;
            }
        }
        //从集合删除
        my.list.remove(this);
        my.rewardlist.remove(this);
    }
}






class myhj extends hj implements Runnable{//我的灰机

    public myhj(){
        w=130*my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        h=160*my.bili;
        //设置初始位置
        setX(my.w/2-w/2);
        setY(my.h*0.7f-h/2);
        img=my.myhj;//初始化图片
        my.list.add(this);//添加到集合里 这样才能被画出来
        new Thread(this).start();//发射子弹的线程
    }

    @Override
    public void run() {
        while(true){
            //90毫秒发射一发子弹
            try {Thread.sleep(max((180-10*my.level),30));} catch (InterruptedException e) {e.printStackTrace();}
            if(my.ispause) {
                new myzd(this);

                    //这里判断有没有和集合里的敌人发生碰撞
                    for (int i = 0; i < my.drlist.size(); i++) {
                        hj h = my.drlist.get(i);
                        if (pengzhuang(h, 20)) {//判断碰撞
                            my.game_over = true;

                        }
                    }

            }



        }
    }



}



class myzd extends hj implements Runnable{//我的子弹
    private float sd0;

    public myzd(hj hj){
        w=h=60*my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        img=my.myzd;//图片
        sd0=8*my.bili+my.js/300;//速度
        switch (my.level){
            case 1:my.dps=6;break;
            case 2:my.dps=7;break;
            case 3:my.dps=8;break;
            case 4:my.dps=9;break;
            case 5:my.dps=10;break;
            case 6:my.dps=11;break;
            case 7:my.dps=12;break;
            case 8:my.dps=13;break;
            case 9:my.dps=14;break;
            default:my.dps=16;break;
        }
        //设在玩家中心的偏上一点
        setX(hj.r.left+hj.w/2-w/2);
        setY(hj.r.top-h/2);
        my.list.add(this);//添加到集合里 这样才能被画出来
        new Thread(this).start();//新建一个子弹向上移动的线程
    }

    @Override
    public void run() {
        boolean flag=false;//一个标记 用来跳出嵌套循环
        while(true){
            try {Thread.sleep(5-my.js/500);} catch (InterruptedException e) {e.printStackTrace();}
            if(my.ispause)setY(r.top-sd0);//向上移sd0个像素，sd0=6

            try {//try一下 怕出错
                //这里判断有没有和集合里的敌人发生碰撞
                for(int i=0;i<my.drlist.size();i++){
                    hj h=my.drlist.get(i);
                    if(pengzhuang(h,10)){//判断碰撞
                        h.hp-=my.dps;//敌人生命-子弹伤害
                        flag=true;//一个标记 用来跳出嵌套循环
                        if(h.hp<=0) my.js++;//击杀+1
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            if(flag || r.top+h<=0)break;//如果子弹击中过敌人 或者超出屏幕范围 跳出循环



        }
        my.list.remove(this);//从集合删除
    }
}
