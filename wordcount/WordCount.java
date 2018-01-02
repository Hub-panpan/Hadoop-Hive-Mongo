package com.panpan.uitl;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {

    public static class WordCountMap extends
            Mapper<LongWritable, Text, Text, IntWritable> {

        private final IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            StringTokenizer token = new StringTokenizer(line);
            while (token.hasMoreTokens()) {
                word.set(token.nextToken());
                System.out.println("正在分词："+word);
                int i = Integer.parseInt(word.toString());
                if(i>0&&i<11){
                    word.set("0---10分：");

                }
                if(i>10&&i<21){
                    word.set("10---20分：");

                }
                if(i>20&&i<31){
                    word.set("20---30分：");

                }
                if(i>30&&i<41){
                    word.set("30---40分：");

                }
                if(i>40&&i<51){
                    word.set("40---50分：");

                }
                if(i>50&&i<61){
                    word.set("50---60分：");

                }




                context.write(word, one);
            }


        }

    }

    public static class WordCountReduce extends
            Reducer<Text, IntWritable, Text, IntWritable> {







        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

/**
 * 1.连接HDFS系统 (configuration)
 * 2.创建执行任务(Job)
 * 3.指定该任务的input文件地址（path）
 * 4.指定该任务的map以及map输出参数配置类型
 * 5.指定该任务的reduce以及reduce输出类型
 * 6.指定非人为的output文件地址（path）
 * 7.执行任务并获得结果
 * */

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = new Job(conf);
        job.setJarByClass(WordCount.class);
        job.setJobName("wordcount");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        Path patn1=new Path("input/b.txt");
        Path patn2=new Path("output");

        FileInputFormat.addInputPath(job, patn1);
        FileOutputFormat.setOutputPath(job, patn2);

        job.waitForCompletion(true);
    }
}

