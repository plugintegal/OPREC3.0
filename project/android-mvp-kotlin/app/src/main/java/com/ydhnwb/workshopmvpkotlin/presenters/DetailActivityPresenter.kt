package com.ydhnwb.workshopmvpkotlin.presenters

import com.ydhnwb.workshopmvpkotlin.contracts.DetailActivityContract
import com.ydhnwb.workshopmvpkotlin.models.User
import com.ydhnwb.workshopmvpkotlin.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityPresenter(var view : DetailActivityContract.View?) : DetailActivityContract.Interactor {
    private var api = ApiClient.instance()

    override fun fetchUserById(user_id: Int) {
        view?.isLoading(true)
        api.findUser(user_id).enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                view?.isLoading(false)
                view?.toast(t.message.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    val user = response.body()
                    user?.let {
                        view?.bind(it)
                    }
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() { view = null }
}