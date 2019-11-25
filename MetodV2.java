package Lab5;


public class MetodV2 {

    public static double A = 0.6;
    public static double B = 0.3;
    public static double x0 = 0.5;
    public static double xn = 1.5;
    public static double a0 = 1.5;
    public static double a1 = -1;
    public static double b0 = 2;
    public static double b1 = 0;
    public static double h;
    public static double[] x;
    public static double[] y;
    public static double[] k;
    public static double[] l;
    public static int N;


    public static void raznosti(){
        h = 0.1;
        function_();
    }

    public static void function_() {
        N = (int) ((xn-x0)/h);
        x = new double[N+1];
        y = new double[N+1];
        k = new double[N+1];
        l = new double[N+1];
        double v,yn;
        v = (Math.pow(h, 2) * q(x0+h) - 2) * a1 + h * (2 - h * p(x0+h)) * a0;
        k[0] = (Math.pow(h, 2) * f(x0+h) * a1 + h * (2 - h * p(x0+h)) * A) / v;
        l[0] = (2 * a1) / v;
        yn = (2*h*B + (k(N-1,xn-h) - k(N,xn)/l(N,xn))*b1)  /  (2*h*b0 + (l(N-1,xn-h))*b1);
        y[N] = yn;
        double xi = xn;
        x[N] = xi;
        for (int i = N-1; i > 0;i--){
            xi = xi-h;
            x[i] = xi;
            y[i] = k(i,xi) - l (i,xi)*y[i+1];
        }
    }


    public static double k(int n, double x){
        if (n == 1){
            k[n] = ((2*Math.pow(h,2)*f(x) - (2 - h*p(x))*k[0]))    /     (2*Math.pow(h,2)*q(x) - 4 - (2-h*p(x))*l[0]);
            return k[n];
        }else {
            if (k[n] == 0){
            k[n] = ((2*Math.pow(h,2)*f(x) - (2 - h*p(x))*k(n-1,x)))    /     (2*Math.pow(h,2)*q(x) - 4 - (2-h*p(x))*l(n,x-h));
            return k[n];}
            else return k[n];
        }

    }

    public static double l(int n, double x){
        if (n == 1){
            l[n] = (2 + h*p(x))    /     (2*Math.pow(h,2)*q(x) - 4 - (2-h*p(x))*l[0]);
            return l[n];
        }else {
            if (l[n] == 0) {
                l[n] = (2 + h * p(x)) / (2 * Math.pow(h, 2) * q(x) - 4 - (2 - h * p(x)) * l(n-1, x - h));
                return l[n];
            }else return l[n];
        }
    }


    public static double p(double x){
        return -(1/4);
    }
    public static double q(double x){
        return 2/x;
    }
    public static double f(double x){
        return x/2;
    }
}
