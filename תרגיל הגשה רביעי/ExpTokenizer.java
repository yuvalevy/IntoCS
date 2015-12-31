import java.util.Enumeration;
/*
 * if direction is true, we will tokenize from left tu right,
 * if direction is false, then we will tokenize from right to left.
 */
public class ExpTokenizer extends Object implements Enumeration<Object>  {
	//fields
	private String [] result;
	private boolean direction;
	private int index;
	
	//constructor
	public ExpTokenizer(String exp) {
		this.result = exp.split(" ");
		this.direction = true;
		this.index = 0;
	}
	
	public ExpTokenizer(String exp,boolean direction) {
		result = exp.split(" ");
		this.direction = direction;
		if(!this.direction)
			this.index=result.length-1;
		else 
			this.index = 0;
	}
	
	
	public Object nextElement() {
		CalcToken resultToken = null;
		String token =  nextToken();
		if (token.equals("+"))
			resultToken =  new AddOp();
		else if (token.equals("*"))
			resultToken =  new MultiplyOp();

		// Fill the rest of the token cases by yourself	

		else 
			resultToken = new ValueToken(Double.parseDouble(token));			
		
		return resultToken;	
	}

	@Override
	public boolean hasMoreElements() {
		if(this.direction)
			return (this.index != this.result.length);
		else
			return (this.index >= 0);
	}
	public String nextToken() {
		String ret;
		if(this.direction){
			ret= this.result[this.index];
			this.index++;
		}
			
		else{
			ret= this.result[this.index];
			this.index--;
		}
		return ret;
	}

	public boolean hasMoreTokens() {
		return hasMoreElements();
	}

	public int countTokens() {
		if(this.direction)
			return (this.result.length - this.index);
		else
			return (this.index+1);
	}
	

}