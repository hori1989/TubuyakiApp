package com.example.user.tubuyakiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // つぶやき表示用のリストビュー
    private ListView tubuyakiLV;

    // つぶやき入力欄
    private EditText commentEtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Tubuyaki t = new Tubuyaki(1, "つぶやきテスト");
//        t.save(); // テーブルにデータ保存
//
//        // テーブルからすべてのデータを取得
//        List<Tubuyaki> list = Tubuyaki.listAll(Tubuyaki.class);
//        for (Tubuyaki tubuyaki : list) {
//            Log.d("データベーステスト", tubuyaki.comment);
//        }

        // レイアウトより、つぶやくボタンの情報を取得
        Button commitBtn = (Button) findViewById(R.id.main_commit_btn);
        commitBtn.setOnClickListener(this);

        Button clearBtn = (Button) findViewById(R.id.main_clear_btn);
        clearBtn.setOnClickListener(this);


        // レイアウトより、リスチビューの情報を取得
        tubuyakiLV = (ListView) findViewById(R.id.main_tubuyaki_lv);

        // リストビューの内容を更新する
        updateListView();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.main_clear_btn:
                commentEtx = (EditText) findViewById(R.id.main_comment_etx);
                commentEtx.setText("");
                break;

            case R.id.main_commit_btn:
                commentEtx = (EditText) findViewById(R.id.main_comment_etx);
                Tubuyaki tubuyaki = new Tubuyaki();
                tubuyaki.id = 1;
                tubuyaki.comment = commentEtx.getText().toString();
                tubuyaki.save(); // テーブルの保存

                updateListView();
                commentEtx.setText(""); // 入力欄を空にする
                break;
        }
    }

    // リストビューの内容を更新する
    private void updateListView() {

        // テーブルからすべてのデータを取得
        List<Tubuyaki> list = Tubuyaki.listAll(Tubuyaki.class);
        // 昇順(ASC): 小さい順
        // 降順(DESC): 大きい順
        list = Tubuyaki.listAll(Tubuyaki.class, "ID DESC");

        // リストビューにダータをセット
        // adapter: 特定のデータをひとまとめにしてビューに渡す時に利用する
        AdapterListTubuyaki adapter =
                new AdapterListTubuyaki(this, R.layout.list_tsubuyaki, list);
        tubuyakiLV.setAdapter(adapter);
    }

//    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        // 長押しされたリストビューの項目を取得
//        ListView list = (ListView) adapterView;
//
//        // 項目の情報を取得
//        final Tubuyaki selectedItem = (Tubuyaki) list.getItemAtPosition(i);
//
//        // 警告ダイアログを出す
//        new AlertDialog.Builder(this)
//                .setTitle("警告")
//                .setMessage("削除してもよろしいですか？")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // OK button pressed
//                        // テーブルから該当するIDの項目を削除
//                        Tubuyaki tubuyaki = Tubuyaki.findById(Tubuyaki.class, selectedItem.getId());
//                        tubuyaki.delete();
//
//                        // 削除を反映させるためリストビュー更新
//                        updateListView();
//                    }
//                })
//                .setNegativeButton("Cancel", null)
//                .show();
//
//        return false;
//    }
}
