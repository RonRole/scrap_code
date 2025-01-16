// ToDoアプリケーション(MPA)
package main

import (
	"log"
	"net/http"
	"tiny_todo/handler"
)

var todoList [] string

func main() {
	todoList = append(todoList, "顔を洗う", "朝食を食べる", "歯を磨く")

	// /static → 静的ファイルの返却
	http.Handle("/static/",
		http.StripPrefix("/static/", http.FileServer(http.Dir("static"))))

	// /todo → templates/todo.htmlの表示
	http.HandleFunc("/todo", handler.HandleTodo)

	// /add → handleadd
	http.HandleFunc("/add", handler.HandleAdd)

	err := http.ListenAndServe(":8080", nil)

	if err != nil {
		log.Fatal("failed to start : ", err)
	}
}