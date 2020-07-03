package main

import (
    "database/sql"
    "fmt"
    "github.com/gin-gonic/gin"
    _ "github.com/go-sql-driver/mysql"
    "net/http"
)

type Fruit struct {
    Id  int `json:"id"`
    Name string `json:"name"`
}

var con *sql.DB

func main() {
    db, err := sql.Open("mysql", "root:password@/payments")
    if err != nil {
        panic("failed to open a mysql connection")
    }
    con = db
    r := gin.Default()
    r.GET("/fruits", fruits)
    fmt.Println("server up on 8080")
    r.Run()
}

func fruits(c *gin.Context) {
    fruits := getFruits()
    c.JSON(http.StatusOK, fruits)
}

func getFruits() []Fruit {
    rows, _ := con.Query("SELECT * FROM fruits")
    fruits := []Fruit{}
    for rows.Next() {
        var r Fruit
        rows.Scan(&r.Id, &r.Name)
        fruits = append(fruits, r)
    }
    return fruits
}

