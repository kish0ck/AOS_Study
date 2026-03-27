package kshong.myapplication.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kshong.myapplication.memo.databinding.MemoEditBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.jvm.java

/*
*
* 1. 안드로이드 데이터 저장 4가지
1-1. SharedPreferences
특징 : 로그인 여부, 다크모드 설정, 마지막 메모 내용, 자동 로그인, Key-Value 형태 저장, 가장 많이 쓰는 초보자용 저장 방식
장점 : 사용 매우 쉬움, 속도 빠름, 설정 저장 최적
단점 : 대용량데이터X, 리스크/복잡 구조 X


1-2. Internal Storag(앱 내부 파일 저장)
특징 : 메모 파일저장, 로그 파일, 캐시데이터, 앱만 접근 가능한 비밀 저장소, 앱 삭제 → 데이터 같이 삭제됨
장점: 보안 좋음, 권한 필요 없음, 파일 형태 저장 가능
단점 : 사용자 직접 접근 불가, 파일 관리 직접 해야 함


1-3. External Storage(외부 저장소)
특징 : 핸드폰 저장공간 / Downloads 같은 곳, 사용자도 파일 볼 수 있음, 사진, PDF, 백업 파일, 공유 메모
장점 : 사용자 접근 가능, 파일 공유 쉬움
단점 : 권한 필요, Android 버전별 정책 까다로움, 삭제 위험성 있음


1-4. SQLite / Room DB	구조화 데이터
특징 : 실무 최강 저장 방식, 데이터베이스 개념, 메모 여러 개, 게시판, 채팅, 할일 목록
장점 : SQLite : 직접 SQL 작성, Room :Google 공식 DB 라이브러리(요새는 Room만 사용),대용량 OK, 검색 빠름, 구조화 데이터 최고
단점 : 초기 세팅 조금 어려움
*
* */
private lateinit var binding: MemoEditBinding
class MainActivity : ComponentActivity() {

    lateinit var listView: ListView
    lateinit var btnAdd: Button

    lateinit var files: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo_list)

        listView = findViewById(R.id.listViewMemo)
        btnAdd = findViewById(R.id.btnAddMemo)

        // ✅ 메모 추가 버튼
        btnAdd.setOnClickListener {
            val intent = Intent(this, MemoEditActivity::class.java)
            startActivity(intent)
        }

        // ✅ 리스트 클릭
        listView.setOnItemClickListener { _, _, position, _ ->
            val fileName = files[position]

            val intent = Intent(this, MemoEditActivity::class.java)
            intent.putExtra("fileName", fileName) // 🔥 파일명 전달
            startActivity(intent)
        }

        btnDelete.setOnClickListener {

        }
    }

    override fun onResume() {
        super.onResume()
        loadMemoList()
    }

    private fun loadMemoList() {
        files = fileList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, files)
        listView.adapter = adapter
    }
}

