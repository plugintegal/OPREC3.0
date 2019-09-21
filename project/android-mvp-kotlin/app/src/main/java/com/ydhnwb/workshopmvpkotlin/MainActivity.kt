package com.ydhnwb.workshopmvpkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydhnwb.workshopmvpkotlin.adapters.UserAdapter
import com.ydhnwb.workshopmvpkotlin.contracts.MainActivityContract
import com.ydhnwb.workshopmvpkotlin.models.User
import com.ydhnwb.workshopmvpkotlin.presenters.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    private var presenter = MainActivityPresenter(this)

	//attach to recycler akan dieksekusi dari presenter
    override fun attachToRecycler(users: List<User>) {
        rv_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserAdapter(users, this@MainActivity)
        }
    }

    override fun toast(message: String) = Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()

	//if state diisi true, maka loading bar akan muncul. dan sebaliknya
    override fun isLoading(state: Boolean) {
        if (state){
            progress_bar.visibility = View.VISIBLE
        }else{
            progress_bar.visibility = View.GONE
        }
    }

    private fun fetchUser() = presenter.fetchUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchUser()
    }
	
	//saat onDestroy ini dieksekusi (user keluar dari aplikasi atau hal lain)
	//maka view yang ada di presenter akan di set NULL
	//jika sebuah view NULL, terusan ke UI tidak akan dilakukan
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }
}
