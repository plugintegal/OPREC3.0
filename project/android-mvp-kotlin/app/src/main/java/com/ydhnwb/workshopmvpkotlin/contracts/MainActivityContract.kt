package com.ydhnwb.workshopmvpkotlin.contracts

import com.ydhnwb.workshopmvpkotlin.models.User


//perjanjian apa apa saja yang dapat dilakukan
//oleh Main Activity
interface MainActivityContract {
    interface View {
        fun attachToRecycler(users : List<User>)
        fun toast(message : String)
        fun isLoading(state : Boolean)
    }

    interface Interactor {
        fun fetchUser()
        fun destroyView()
    }
}