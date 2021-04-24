fun main(){
    val visitor = ModelVisitor()
    val movie = Movie("Harry Potter",1)
    val movieList = MovieList(10, movie, "Yes")
    val movieJson = visitor.parse(movieList)
    print(movieJson)
}