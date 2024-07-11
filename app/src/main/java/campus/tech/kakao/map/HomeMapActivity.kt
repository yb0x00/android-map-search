package campus.tech.kakao.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.MapLifeCycleCallback
import java.lang.Exception
import java.security.MessageDigest

class HomeMapActivity : AppCompatActivity() {
    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_map)

        val key = getString(R.string.kakao_api_key)
        KakaoMapSdk.init(this, key)

        mapView = findViewById(R.id.mapView)
        mapView.start(object: MapLifeCycleCallback(){
            override fun onMapDestroy() {
            }

            override fun onMapError(p0: Exception?) {
            }

        }, object: KakaoMapReadyCallback(){
            override fun onMapReady(p0: KakaoMap) {
            }

        })
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }
}