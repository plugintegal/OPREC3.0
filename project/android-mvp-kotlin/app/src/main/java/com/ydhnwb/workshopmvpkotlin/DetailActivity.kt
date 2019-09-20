package com.ydhnwb.workshopmvpkotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ydhnwb.workshopmvpkotlin.contracts.DetailActivityContract
import com.ydhnwb.workshopmvpkotlin.models.User
import com.ydhnwb.workshopmvpkotlin.presenters.DetailActivityPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity(), DetailActivityContract.View {
    private var presenter = DetailActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        getUser()
    }

    override fun isLoading(state: Boolean) {
        if(state){
            loading.visibility = View.VISIBLE
        }else{
            loading.visibility = View.GONE
        }
    }

    override fun bind(user: User) {
        user_fullname.text = user.name
        user_email.text = user.email
    }

    override fun toast(message: String) = Toast.makeText(this@DetailActivity, message, Toast.LENGTH_LONG).show()

    private fun getUser() = presenter.fetchUserById(intent.getIntExtra("ID", 0))

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

}
