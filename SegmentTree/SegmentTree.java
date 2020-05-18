import java.util.function.BiFunction;


public class SegmentTree<T> {

private Function<T,T> func;
private T[] inputArray;
private T[] segmentArray;
private SegmentTree tree;
private int size;

public void SegmentTree() {
 
 init();
}

public void SegmentTree(T [] inputArray, BiFunction<T,T,R> func){
this.inputArray = inputArray;
this.func = func;
this.size = this.inputArray.length;
init();
}


private void init(){
  segmentArray = new segmentArray[4*(this.inputArray.length)];
}


public SegmentTree build() {

 buildTree(this.inputArray,0,this.size-1,0);

}

private void buildTree(T[] array, int low, int high, int pos) {
	if(low==high) {
		this.segmentArray[pos]=array[low];
		return;
	}
	int mid= low+((high-low)/2);
	buildTree(array,0,mid,2*pos+1);
	buildTree(array,mid+1,high,2*pos+2);
	this.segmentArray[pos]=this.func.apply(this.segmentArray[2*pos+1],this.segmentArray[2*pos+1]);
}


public void update(int index, T new_value){


}


public T findValue() {
  return findValue(0,this.size-1); 
}


public T findValue(int queryStartRange, int queryEndRange) {
  if(queryStartRange<0 || queryEndRange>this.size-1) {
	  return identity;
  }
  return findQueryRangeValue(queryStartRange,queryEndRange,0,0,this.size-1);
}

private T findQueryRangeValue(int queryStartRange, int queryEndRange, int pos, int segmentTreeRangeStart, segmentTreeRangeEnd) {
	if((queryStartRange <= segmentTreeRangeStart) && (segmentTreeRangeEnd <= queryEndRange)) {
		return this.segmentArray[pos];
	}
	
	if((segmentTreeRangeEnd < queryStartRange) || (segmentTreeRangeStart > queryEndRange)) {
		return identity; 
	}
	
	int mid =  segmentTreeRangeStart+((segmentTreeRangeEnd-segmentTreeRangeStart)/2);
	int left = findQueryRangeValue(queryStartRange,queryEndRange,pos*2+1,segmentTreeRangeStart,mid);
	int right = findQueryRangeValue(queryStartRange,queryEndRange,pos*2+1,mid+1,segmentTreeRangeEnd);
	return this.func.apply(left,right);
}


}