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


private lateinit var binding: MemoEditBinding
class MemoEditActivity : ComponentActivity() {

    lateinit var editText: EditText
    var fileName: String? = null
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo_edit)

        editText = findViewById(R.id.editText)

        val btnSave = findViewById<ImageButton>(R.id.btnSave)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btn
        // 🔥 전달받은 파일명
        fileName = intent.getStringExtra("fileName")

        // ✅ 기존 메모 불러오기
        if (fileName != null) {
            val text = openFileInput(fileName).bufferedReader().use { it.readText() }
            editText.setText(text)
        }

        // ✅ 저장
        btnSave.setOnClickListener {

            val text = editText.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(this, "내용 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                // 신규 or 수정 분기
                val finalFileName = fileName ?: generateFileName()

                openFileOutput(finalFileName, MODE_PRIVATE).use {
                    it.write(text.toByteArray())
                }

                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
                finish()

            } catch (e: Exception) {
                Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show()
            }
        }

        // 뒤로가기
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun generateFileName(): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "memo_$timeStamp.txt"
    }
}

