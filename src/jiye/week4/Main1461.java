import java.util.*;
import java.io.*;
class Main1461 {//도서관
    static int M;
    static int max;

    public static int func(PriorityQueue<Integer> map)
    {
        int sum=0;
        while(map.size()!=0)
        {
            if(map.size()<M)
            {//남은거
                int a=map.poll();//맨앞에꺼
                sum+=a*2;
                break;
            }

            int a=map.poll();//맨앞에꺼
            // if(a!=max)
                sum+=a*2;
            for(int m=0;m<M-1;m++)
            {//m-1개 뽑는다
                map.poll();
            }
        }
        
        return sum;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> map1 = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> map2 = new PriorityQueue<Integer>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
        {
            int a=Integer.parseInt(st.nextToken());
            if(a<0)
                map1.add(a*-1);//음수
            else
                map2.add(a);//양수
        }


        //음수 양수 따로 pq에
        //책을 가지러 다시 돌아와야 하기 때문에(거리*2) 가장 먼곳에서 끝나도록
        //
        //map1 음수  39  37  29  28  6
        //map2 양수  11  2
        //가장 먼곳 39에서 끝나도록 
        //책 M권 들고갈 수 있으니깐 37 -> 39->끝 
        //현재 pq에서 가장 큰값*2  M-1개는 그냥 빼줌

        

        int sum=0;
        if(map1.size()==0)//양수만 있다
        {
            max = map2.poll();
            for(int m=0;m<M-1;m++)
            {//m-1개 뽑는다
                map2.poll();
            }

            sum+=func(map2);
        }
        else if(map2.size()==0)//음수만 있다
        {
            max = map1.poll();
            for(int m=0;m<M-1;m++)
            {//m-1개 뽑는다
                map1.poll();
            }

            sum+=func(map1);
        }
        else
        {
            if(map1.peek()>map2.peek())
            {
                max = map1.poll();
                for(int m=0;m<M-1;m++)
                {//m-1개 뽑는다
                    map1.poll();
                }
            }
            else
            {
                max = map2.poll();
                for(int m=0;m<M-1;m++)
                {//m-1개 뽑는다
                    map2.poll();
                }
            }
            
            
            sum+=func(map1);
            sum+=func(map2);
        }
        
        sum+=max;//제일먼곳에서 끝난다
        System.out.println(sum);
    }
}
