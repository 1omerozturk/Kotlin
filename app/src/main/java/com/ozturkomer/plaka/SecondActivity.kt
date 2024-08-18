package com.ozturkomer.plaka

import android.app.PendingIntent.OnFinished
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ozturkomer.plaka.databinding.ActivityMainBinding
import com.ozturkomer.plaka.databinding.ActivitySecondBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.whileSelect
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        yeniSoru(view)
        timer.start()
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

    var dogru = 0
    var yanlis = 0

    fun previewPage(view: View) {
        finish()
    }


    fun yeniSoru(view: View) {
        var soru = "Plaka kodlu şehir hanigisidir?"
        val randomSayi = Random.nextInt(1, 82);
        if (randomSayi < 10) {
            soru = "0${randomSayi} $soru"
        } else {
            soru = "$randomSayi $soru";
        }
        binding.txtSoru.text = soru

        val plakaListesi = plakalar.values.toMutableList()
        plakaListesi.remove(plakalar.get(randomSayi.toString()));
        val secilenPlakalar = plakaListesi.shuffled().take(3)
        val buttonListesi = listOf(binding.btnA, binding.btnB, binding.btnC, binding.btnD)
        val randomButtons = buttonListesi.shuffled()
        randomButtons[0].text = secilenPlakalar[0]
        randomButtons[1].text = secilenPlakalar[1]
        randomButtons[2].text = secilenPlakalar[2]
        randomButtons[3].text = plakalar.get(randomSayi.toString())
        cevap(view, randomSayi);
        temizle(view)


    }

    fun cevap(view: View, randomSayi: Int) {
        val buttonListesi = listOf(binding.btnA, binding.btnB, binding.btnC, binding.btnD)
        buttonListesi.forEach{it.isEnabled=true}
        for (button in buttonListesi) {
            button.setOnClickListener {
                buttonListesi.forEach{it.isEnabled=false}
                val cevap = button.text.toString()
                if (cevap == plakalar.get(randomSayi.toString())) {
                    dogru++;
                    button.setTextColor(Color.BLACK)
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            android.R.color.holo_green_light
                        )
                    )

                } else {
                    yanlis++;
                    button.setTextColor(Color.BLACK)
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this,
                            android.R.color.holo_red_light
                        )
                    )
                }

                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        yeniSoru(view)
                    },500
                )

            }
        }

    }

    fun temizle(view: View) {
        val buttonListesi = listOf(binding.btnA, binding.btnB, binding.btnC, binding.btnD)
        for (button in buttonListesi) {
            button.setTextColor(Color.WHITE)
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_blue_light
                )
            )
        }
    }

    val sure = 61000L
    val timer = object : CountDownTimer(sure, 1000) {
        override fun onTick(p0: Long) {
            val kalanSure = p0 / 1000
            binding.btnTime.text = "$kalanSure"
        }

        override fun onFinish() {
            binding.btnTime.text = "⏰"
            sonuc()
        }

    }



    fun sonuc() {
        timer.cancel()
        binding.txtSoru.text =
            "Soru sayısı: ${dogru + yanlis}\nDoğru sayısı: ${dogru}\nYanlış sayısı: ${yanlis}"
        binding.linearLayout.visibility = View.INVISIBLE
        binding.button4.visibility = View.INVISIBLE
    }

    fun sonuc(view: View) {
        timer.onFinish()
        timer.cancel()
        binding.txtSoru.text =
            "Soru sayısı: ${dogru + yanlis}\nDoğru sayısı: ${dogru}\nYanlış sayısı: ${yanlis}"
        binding.linearLayout.visibility = View.INVISIBLE
        binding.button4.visibility = View.INVISIBLE
    }
}