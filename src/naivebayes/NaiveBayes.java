package naivebayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class NaiveBayes {
      HashMap<String,Double> labels;
      ArrayList<HashMap<String,Double>> features; //key=feature name+label
      
      public NaiveBayes(){
             labels= new HashMap<String,Double>();
             features= new ArrayList<HashMap<String,Double>>();
      }
      
      public void readData(String[][] inputData,String[] inputLabels ){
             int n= inputData[0].length ;
             int m= inputData.length ;
             //先验概率
             for( int i=0; i< m; i++){
                  String key= inputLabels[ i];
                   if( labels.containsKey( key)){
                         double prob= labels.get( key);
                         labels.put( key, prob+1.0/ m);
                  } else{
                         labels.put( key, 1.0/ m);
                  }
            }
             //条件概率
             for( int i=0; i< m; i++){
                   for( int j=0; j< n; j++){
                        String key= inputData[i][j]+"," +inputLabels [i ];
                         if( features.get( j).containsKey( key)){
                               double prob= features.get( j).get( key);
                               features.get( j).put( key, prob+1.0/ n);
                        } else{
                               features.get( j).put( key, 1.0/ n);
                        }
                  }
            }
      }
      
      public String prediction(String[] newdata){
            Iterator<String> it= labels.keySet().iterator();
            HashMap<String,Double> output= new HashMap<String,Double>();
             while( it.hasNext()){
                  String label= it.next();
                   double postProb= labels.get( label);
                   try{
                         for( int i=0; i< newdata. length; i++){
                              String key= newdata[ i]+ ","+ label;
                               if(! features.contains( key)){
                                     throw new KeyNotFoundException();
                              }
                               postProb*= features.get( i).get( key);
                        }
                         output.put( label, postProb);
                  } catch(Exception e){
                        System. err.println( "Uncorrect input");
                  }
                  
            }
            Iterator<String> itr= output.keySet().iterator();
             double maxProb=Double. MIN_VALUE;
            String result= "";
             while( itr.hasNext()){
                  String label= itr.next();
                   double prob= output.get( label);
                   if( prob> maxProb){
                         maxProb= prob;
                         result= label;
                  }
            }
             return result;
      }
}
