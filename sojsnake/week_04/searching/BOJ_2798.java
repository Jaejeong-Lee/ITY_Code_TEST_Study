import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] card = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            card[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        int max = 0;
        for(int i=0; i<n-2; i++){
            for(int j=i+1; j<n-1; j++){
                for(int k=j+1; k<n; k++){
                    sum = card[i] + card[j] + card[k];
                    if(sum > max && sum <= m)
                        max = sum;
                }
            }
        }
        System.out.println(max);
    }
}
