package com.h5190059.zenpirlanta.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.databinding.ActivitySplashBinding
import com.h5190059.zenpirlanta.ui.login.LoginActivity
import com.h5190059.zenpirlanta.util.*
import com.h5190059.zenpirlanta.util.AlertUtil.AlertDialogSecilen



class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timer()
    }

    private fun timer() {
        var timerMillisInFuture = Constants.TIMER_MILLIS_IN_FUTURE
        val timerInterval = Constants.TIMER_INTERVAL
        val countDownTimer: CountDownTimer = object : CountDownTimer(timerMillisInFuture.toLong(), timerInterval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                networkController()
            }
        }
        countDownTimer.start()
    }

    private fun networkController() {
        if (NetworkUtil.internetVarMi(applicationContext)) {
            secondActivity()
        } else {
            internetAlert()
        }
    }

    private fun internetAlert() {
        AlertUtil.alertDialogGoster(this@SplashActivity, R.style.AlertDialogTheme, resources.getDrawable(R.drawable.interneticon), resources.getString(R.string.alertTitle), resources.getString(R.string.alertMessage), resources.getString(R.string.alertNegativeButon), resources.getString(R.string.alertPozitiveButon),AlertDialogSecilen.INTERNET)
    }

    private fun secondActivity() {
        val listeIntent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(listeIntent)
        finish()
    }




}