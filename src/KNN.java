
public class KNN {
	int kValue;
	double[][] group;
	String[] labels;
	
	public KNN(double[][] group,String[] labels,int k){
		kValue=k;
		this.group=group;
		this.labels=labels;
	}
	
	double getDistance(double[] data1,double[] data2){
		double dist=0;
		for(int i=0;i<data1.length;i++){
			dist+=Math.pow(data1[i]-data2[i],2);
		}
		return Math.sqrt(dist);
	}
	
	
	
}
