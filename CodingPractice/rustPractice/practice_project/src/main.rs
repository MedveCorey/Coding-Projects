fn main(){
    let num_terms = 1000;
    println!("Fibonnaci sequence up to {} terms", num_terms);
    for i in 0..num_terms{
        println!("Term {}: {}", i, fibonnaci(i));
    }
}