<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\News;
use App\User;
use Auth;

class NewsApiController extends Controller
{
    // Menampilkan semua berita
    public function index(){
      $results = [];
      $news = News::all();
      foreach ($news as $n) {
        $results[] = [
          'id' => $n->id,
          'title' => $n->title,
          'description' => $n->description,
          'published by' => $n->user->name,
          'published' => $n->created_at->diffForHumans()
        ];
      }
      return response()->json([
        'message' => 'success',
        'status' => true,
        'data' => $results
      ]);
    }

    // Menambahkan berita
    public function store(Request $request){
      $this->validate($request, [
        'title' => 'required|min:5',
        'description' => 'required|min:5'
      ]);
      $user = User::find(Auth::user()->id);
      $news = News::create([
        'title' => $request->title,
        'description' => $request->description,
        'user_id' => $user->id
      ]);

      return response()->json([
        'message' => 'success',
        'status' => true,
        'data' => $news
      ]);
    }

    // Merubah Berita
    public function update(Request $request, $id){
      $this->validate($request, [
        'title' => 'required|min:5',
        'description' => 'required|min:5'
      ]);
      $news = News::find($id);

      // Pengecekan apakah berita per id yang dituju ada atau tidak
      if($news == null){
        return response()->json([
          'message' => 'failed',
          'status' => false
        ]);
      }

      // Pengecekan user yang akan merubah Berita
      // hanya user yang telah membuat berita yang dapat merubah berita
      if($news->user_id != Auth::user()->id){
        return response()->json([
          'message' => 'failed',
          'status' => false
        ]);
      }

      $news->update([
        'title' => $request->title,
        'description' => $request->description
      ]);

      return response()->json([
        'message' => 'success',
        'status' => true,
        'data' => $news
      ]);
    }

    // Menampilkan berita
    public function show($id){
      $news = News::find($id);
      // Pengecekan apakah berita per id yang dituju ada atau tidak
      if($news == null){
        return response()->json([
          'message' => 'failed',
          'status' => false
        ]);
      }
      return response()->json([
        'message' => 'success',
        'status' => true,
        'data' => [
          'id' => $news->id,
          'title' => $news->title,
          'description' => $news->description,
          'published by' => $news->user->name,
          'published' => $news->created_at->diffForHumans()
        ]
      ]);
    }

    // menghapus berita
    public function destroy($id){
      $news = News::find($id);
      // Pengecekan apakah berita per id yang dituju ada atau tidak
      if($news == null){
        return response()->json([
          'message' => 'failed',
          'status' => false
        ]);
      }
      $news->delete();
      return response()->json([
        'message' => 'success',
        'status' => true
      ]);
    }
}
