package com.ozturkomer.plaka

import android.content.Intent
import android.graphics.Color
import java.io.File
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.ozturkomer.plaka.databinding.ActivityMainBinding
import java.io.FileReader
import java.util.Dictionary

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val plakalar = mapOf(
            "1" to "ADANA",
            "2" to "ADIYAMAN",
            "3" to "AFYONKARAHİSAR",
            "4" to "AĞRI",
            "5" to "AMASYA",
            "6" to "ANKARA",
            "7" to "ANTALYA",
            "8" to "ARTVİN",
            "9" to "AYDIN",
            "10" to "BALIKESİR",
            "11" to "BİLECİK",
            "12" to "BİNGÖL",
            "13" to "BİTLİS",
            "14" to "BOLU",
            "15" to "BURDUR",
            "16" to "BURSA",
            "17" to "ÇANAKKALE",
            "18" to "ÇANKIRI",
            "19" to "ÇORUM",
            "20" to "DENİZLİ",
            "21" to "DİYARBAKIR",
            "22" to "EDİRNE",
            "23" to "ELAZIĞ",
            "24" to "ERZİNCAN",
            "25" to "ERZURUM",
            "26" to "ESKİŞEHİR",
            "27" to "GAZİANTEP",
            "28" to "GİRESUN",
            "29" to "GÜMÜŞHANE",
            "30" to "HAKKARİ",
            "31" to "HATAY",
            "32" to "ISPARTA",
            "33" to "MERSİN",
            "34" to "İSTANBUL",
            "35" to "İZMİR",
            "36" to "KARS",
            "37" to "KASTAMONU",
            "38" to "KAYSERİ",
            "39" to "KIRKLARELİ",
            "40" to "KIRŞEHİR",
            "41" to "KOCAELİ",
            "42" to "KONYA",
            "43" to "KÜTAHYA",
            "44" to "MALATYA",
            "45" to "MANİSA",
            "46" to "KAHRAMANMARAŞ",
            "47" to "MARDİN",
            "48" to "MUĞLA",
            "49" to "MUŞ",
            "50" to "NEVŞEHİR",
            "51" to "NİĞDE",
            "52" to "ORDU",
            "53" to "RİZE",
            "54" to "SAKARYA",
            "55" to "SAMSUN",
            "56" to "SİİRT",
            "57" to "SİNOP",
            "58" to "SİVAS",
            "59" to "TEKİRDAĞ",
            "60" to "TOKAT",
            "61" to "TRABZON",
            "62" to "TUNCELİ",
            "63" to "ŞANLIURFA",
            "64" to "UŞAK",
            "65" to "VAN",
            "66" to "YOZGAT",
            "67" to "ZONGULDAK",
            "68" to "AKSARAY",
            "69" to "BAYBURT",
            "70" to "KARAMAN",
            "71" to "KIRIKKALE",
            "72" to "BATMAN",
            "73" to "ŞIRNAK",
            "74" to "BARTIN",
            "75" to "ARDAHAN",
            "76" to "IĞDIR",
            "77" to "YALOVA",
            "78" to "KARABüK",
            "79" to "KİLİS",
            "80" to "OSMANİYE",
            "81" to "DÜZCE"
        )
        binding.button.isVisible=false
        fun plakaBul() {
            if (binding.editTextText.text != null) {
            var sehir = binding.editTextText.text.toString()

            if (sehir != "" && sehir[0] == '0') {
                sehir = sehir.removeRange(0, 1)
            }
            if (plakalar.get(sehir) != null) {

                val fullText = "${sehir}: ${plakalar.get(sehir)}"
                val spannableString = SpannableString(fullText)
                val foregroundColorSpan = ForegroundColorSpan(Color.RED)

                spannableString.setSpan(foregroundColorSpan, 0, sehir.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                binding.textView.text = spannableString

                //binding.textView.text = "${sehir}: ${plakalar.get(sehir)}"
            } else {
                binding.textView.textSize = 25f
                binding.textView.setTextColor(Color.parseColor("#000000"))
                binding.textView.text = "Lüten 1-81 arasında bir plaka giriniz."
            }
            }
            else {
                binding.textView.textSize = 25f
                binding.textView.setTextColor(Color.parseColor("#000000"))
                binding.textView.text = "Lüten 1-81 arasında bir plaka giriniz."
            }
        }



        binding.button.setOnClickListener {
            if(binding.editTextText.text.toString()!=""){

                binding.textView.setTextColor(Color.parseColor("#000000"))
                plakaBul();
                binding.editTextText.text.clear()
            }
            }
        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textView.textSize = 25f
                binding.textView.setTextColor(Color.parseColor("#000000"))
                binding.textView.text = "Lüten 1-81 arasında bir plaka giriniz."
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.editTextText.text.toString()!=""){
                    binding.textView.textSize=30f
                    binding.textView.setTextColor(Color.parseColor("#000000"))
                    plakaBul();
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun nextPage(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
    }

    fun quit(view: View) {
        finishAffinity()
    }


}