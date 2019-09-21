package com.ydhnwb.workshopmvpkotlin.contracts

import com.ydhnwb.workshopmvpkotlin.models.User

//sebuah perjanjian mengenai apa apa saja yang
//bisa dilakukan oleh Detail Activity
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