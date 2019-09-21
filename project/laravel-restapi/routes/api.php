<?php

use Illuminate\Http\Request;

Route::post('register','API\UserApiController@register'); // route untuk melakukan register
Route::post('login','API\UserApiController@login'); // route untuk melakukan login
Route::get('news','API\NewsApiController@index'); // route get tanpa token
// Route::get('news','API\NewsApiController@index')->middleware('auth:api'); route get dengan token
Route::post('news','API\NewsApiController@store')->middleware('auth:api'); // route post dengan token
Route::put('news/{id}','API\NewsApiController@update')->middleware('auth:api'); // route put dengan token
Route::get('news/{id}','API\NewsApiController@show')->middleware('auth:api'); // route get dengan token
Route::delete('news/{id}','API\NewsApiController@destroy')->middleware('auth:api'); // route delete dengan token
