package com.ydhnwb.workshopmvpkotlin.presenters

import com.ydhnwb.workshopmvpkotlin.contracts.MainActivityContract
import com.ydhnwb.workshopmvpkotlin.models.User
import com.ydhnwb.workshopmvpkotlin.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(v : MainActivityContract.View) : MainActivityContract.Interactor {
    private var view : MainActivityContract.View? = v
    private var api = ApiClient.instance()

    override fun destroyView() { view = null }

    override fun fetchUser() {
        val request = api.allUsers()
        view?.isLoading(true)
        request.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                view?.isLoading(false)
                view?.toast("Tidak dapat terkoneksi dengan server")
                println(t.message)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    val users = response.body()
                    users?.let {
                        view?.attachToRecycler(users)
                    }
                }else{
                    view?.toast("Kesalahan saat mengambil response")
                }
                view?.isLoading(false)
            }
        })
    }
}