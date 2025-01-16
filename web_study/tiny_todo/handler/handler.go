package handler

import (
	"html/template"
	"net/http"
)

var todoList [] string

func HandleLogin(w http.ResponseWriter, r *http.Request) {
		
}

func HandleTodo(w http.ResponseWriter, r *http.Request) {
	// テンプレートファイルを読み込む
	t, _ := template.ParseFiles("../templates/todo.html")
	// todoListを引数として、テンプレートファイルを出力する
	t.Execute(w, todoList)
}

/**
 * /addへのリクエストを受けた後
 * todoをレンダリングする
 * → リロードしたときに再度/addにpostされてしまう
 * → これを防ぐために、リダイレクトでtodoに移す
 *
 * Post-Redirect-Getパターン
 */
 func HandleAdd(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	todo := r.Form.Get("todo")
	todoList = append(todoList, todo)
	http.Redirect(w, r, "/todo", 301)
}