package com.gubang.util;

public class Pair<A , B>    {
	private A first;
	private B second;

	public Pair(A a, B b) {
		first = a;
		second = b;
	}

	public String toString() {
		return "( " + first.toString() + " , " + second.toString() + " )";
	}

	public int hashCode() {
		int result = 17;
		result = result * 31 + (first == null ? 0 : first.hashCode());
		result = result * 31 + (second == null ? 0 : second.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Pair<?, ?>){
			Pair<A, B> other = (Pair<A, B>)obj;
			if(!(first == other.first || 
					(first != null && first.equals(other.first))|| 
					(other.first != null && other.first.equals(first)))){
				return false;
			}
			
			if(!(second == other.second || 
					(second != null && second.equals(other.second))|| 
					(other.second != null && other.second.equals(second)))){
				return false;
			}

			return  true;
		}
		return false;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}
