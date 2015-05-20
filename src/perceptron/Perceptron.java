package perceptron;
public class Perceptron {
	double[] w;
	double b;
	int[] label;
	double[][] features;
	int m;//Number of data
	int n;//Number of features
	static final double learningRate=0.5;
	
	public Perceptron(double[][]features,int[] label){
		this.features=features;
		this.label=label;
		m=label.length;
		n=features[0].length;
		w=new double[n];
		b=0;
	}
	
	double linearValue(double[] feature){
		double result=0;
		for(int i=0;i<feature.length;i++){
			result+=feature[i]*w[i];
		}
		result+=b;
		return result;
	}
	
	int sign(double value){
		if(value>=0)
			return 1;
		else
			return -1;
	}

	public void learning(){
		boolean stop=false;
		while(stop==false){
			stop=true;
			for(int i=0;i<m;i++){
				if(label[i]*linearValue(features[i]) <= 0){
					for(int j=0;j<n;j++){
						w[j]=w[j]+label[i]*features[i][j]*learningRate;
					}
					b=b+label[i]*learningRate;
					stop=false;
				}
			}
		}
	}
	
	public int[] prediction(double[][] prdFeatures){
		int[] prdLabel=new int[prdFeatures.length];
		for(int i=0;i<prdFeatures.length;i++){
			prdLabel[i]=sign(linearValue(prdFeatures[i]));
		}
		return prdLabel;
	}
}
