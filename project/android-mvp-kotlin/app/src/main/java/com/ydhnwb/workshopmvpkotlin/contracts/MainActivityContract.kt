package com.ydhnwb.workshopmvpkotlin.contracts

import com.ydhnwb.workshopmvpkotlin.models.User

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