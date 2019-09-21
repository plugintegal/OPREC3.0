<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class News extends Model
{
    protected $guarded = [];

    protected $hidden = ['user_id'];

    public function user(){
      return $this->belongsTo(User::class, 'user_id','id');
    }
}
