<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\User;
use Auth;

class UserApiController extends Controller
{
    public function register(Request $request){
      $this->validate($request, [
        'name' => 'required|min:3|max:30',
        'email' => 'required|email|unique:users',
        'password' => 'required|min:6',
      ]);

      User::create([
        'name' => $request->name,
        'email' => $request->email,
        'password' => bcrypt($request->password),
        'api_token' => bcrypt($request->email)
      ]);

      return response()->json([
        'message' => 'Regsiter success',
        'status' => true
      ]);
    }

    public function login(Request $request){
      $this->validate($request,[
        'email' => 'required|email',
        'password' => 'required|min:6'
      ]);

      $credential = [
          'email' => $request->email,
          'password' => $request->password
      ];

      if(!Auth::attempt($credential)){
        return response()->json([
          'message' => 'Login failed',
          'status' => false
        ]);
      }
      $user = User::find(Auth::user()->id);
        return response()->json([
          'message' => 'Login success',
          'status' => true,
          'data' => $user
        ]);
    }
}
