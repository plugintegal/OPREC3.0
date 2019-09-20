package com.ydhnwb.workshopmvpkotlin.contracts

import com.ydhnwb.workshopmvpkotlin.models.User

interface DetailActivityContract {
    interface View {
        fun isLoading(state : Boolean)
        fun bind(user : User)
        fun toast(message : String)
    }

    interface Interactor {
        fun fetchUserById(user_id : Int)
        fun destroy()
    }
}