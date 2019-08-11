package com.example.bilibili;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends Activity {

    static boolean isPlay = true;
    MediaPlayer mediaPlayer;
    private ImageButton ibtn_game;
    private ImageButton ibtn_music;
    private ImageButton ibtn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ibtn_game = findViewById(R.id.imagebtn_game);
        ibtn_music = findViewById(R.id.imagebtn_music);
        ibtn_about = findViewById(R.id.imagebtn_about);
        PlayMusic();

        ibtn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,SelectActivity.class));
                finish();
            }
        });

        ibtn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlay == true){
                    if (mediaPlayer != null){
                        mediaPlayer.stop();
                        isPlay = false;
                    }
                }else if(isPlay == false){
                    PlayMusic();
                    isPlay = true;
                }else {
                    Toast.makeText(MenuActivity.this,"播放失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibtn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,AboutActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //写PlayMusic方法
    private void PlayMusic() {
            mediaPlayer = MediaPlayer.create(MenuActivity.this,R.raw.number_music);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
    }
//写onStop方法，跳出选择数字界面
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null){//mediaPlayer不为空，停止音乐
            mediaPlayer.stop();
        }
    }
//设置mediaPlayer为空，释放资源，停止音乐
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
//重新播放音乐
    @Override
    protected void onRestart() {
        super.onRestart();
        if (isPlay == true){
            PlayMusic();
        }
    }
}
